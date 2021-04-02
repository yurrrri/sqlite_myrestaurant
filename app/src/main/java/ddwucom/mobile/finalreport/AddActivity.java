package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtMenu;
    EditText edtRating;
    EditText edtReview;

    RestaurantDBManager restaurantDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtName = findViewById(R.id.edtName);
        edtMenu = findViewById(R.id.edtMenu);
        edtRating = findViewById(R.id.edtRating);
        edtReview = findViewById(R.id.edtReview);

        restaurantDBManager = new RestaurantDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if (edtRating.getText().toString().length()==0 || edtName.getText().toString().length()==0  || edtMenu.getText().toString().length()==0  || edtReview.getText().toString().length()==0 ){
                    Toast.makeText(getApplicationContext(), "입력하지 않은 항목이 있습니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean result = restaurantDBManager.addNewRestaurant(
                        new RestaurantData(Float.parseFloat(edtRating.getText().toString()),edtName.getText().toString(), edtMenu.getText().toString(), edtReview.getText().toString()));

                if (result) {    // 정상수행
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("restaurant", edtName.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {        // 정상수행 X
                    Toast.makeText(this, "새로운 맛집 추가 실패!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:   // 취소에 따른 처리
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
