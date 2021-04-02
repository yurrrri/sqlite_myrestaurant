package ddwucom.mobile.finalreport;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    RestaurantDBManager restaurantDBManager;
    TextView tvSearch;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tvSearch = findViewById(R.id.tvSearch);
        edtSearch = findViewById(R.id.edtSearch);
        restaurantDBManager = new RestaurantDBManager(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnSearch:
                if (edtSearch.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "검색어를 입력하지 않았습니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                String result = restaurantDBManager.searchRestaurant(edtSearch.getText().toString());
                tvSearch.setText(result);
                break;
            case R.id.btnCancel:
                Toast.makeText(getApplicationContext(), "검색 취소", Toast.LENGTH_LONG).show();
                finish();
        }
    }
}