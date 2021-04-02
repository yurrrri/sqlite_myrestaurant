package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    RestaurantData restaurantData;

    EditText edtName;
    EditText edtMenu;
    EditText edtRating;
    EditText edtReview;
    ImageView imageView;

    RestaurantDBManager restaurantDBManager;

    int image_source = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        restaurantData = (RestaurantData) getIntent().getSerializableExtra("restaurant");

        if (restaurantData.get_id()==1){
            image_source = R.mipmap.bangigobchang;
        } else if (restaurantData.get_id()==2){
            image_source = R.mipmap.ddchicken;
        } else if (restaurantData.get_id()==3){
            image_source = R.mipmap.garden;
        } else if (restaurantData.get_id()==4){
            image_source = R.mipmap.ggomak;
        } else if (restaurantData.get_id()==5){
            image_source = R.mipmap.hamburger;
        } else{
            image_source = R.mipmap.ic_launcher;
        }

        edtName = findViewById(R.id.edtName);
        edtMenu = findViewById(R.id.edtMenu);
        edtRating = findViewById(R.id.edtRating);
        edtReview = findViewById(R.id.edtReview);
        imageView = findViewById(R.id.imageView);

        edtName.setText(restaurantData.getName());
        edtMenu.setText(restaurantData.getMenu());
        edtRating.setText(String.valueOf(restaurantData.getRating()));
        edtReview.setText(restaurantData.getReview());
        imageView.setImageResource(image_source);

        restaurantDBManager = new RestaurantDBManager(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnUpdate:
                if (edtRating.getText().toString().length()==0 || edtName.getText().toString().length()==0  || edtMenu.getText().toString().length()==0  || edtReview.getText().toString().length()==0 ){
                    Toast.makeText(getApplicationContext(), "입력하지 않은 항목이 있습니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                restaurantData.setName(edtName.getText().toString());
                restaurantData.setMenu(edtMenu.getText().toString());
                restaurantData.setRating(Float.parseFloat(edtRating.getText().toString()));
                restaurantData.setReview(edtReview.getText().toString());

                if (restaurantDBManager.updateRestaurant(restaurantData)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("restaurant", restaurantData);
                    setResult(RESULT_OK, resultIntent);
                } else {
                    setResult(RESULT_CANCELED);
                }

                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
