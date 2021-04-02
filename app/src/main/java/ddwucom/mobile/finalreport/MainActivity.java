//과제명: 맛집 기록 앱
//분반: 01분반
//학번: 20161048 성명: 이유리
//제출일: 2020년 7월 2일

package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ADD_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    ResAdapter resAdapter;
    ArrayList<RestaurantData> restaurantDataList = null;
    RestaurantDBManager restaurantDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        restaurantDataList = new ArrayList();
        resAdapter = new ResAdapter(this, R.layout.restaurant_layout, restaurantDataList);
        listView.setAdapter(resAdapter);
        restaurantDBManager = new RestaurantDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RestaurantData restaurantData = restaurantDataList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("restaurant", restaurantData);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("맛집 삭제")
                        .setMessage(restaurantDataList.get(pos).getName()+"를(을) 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (restaurantDBManager.removeRestaurant(restaurantDataList.get(pos).get_id())) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    restaurantDataList.clear();
                                    restaurantDataList.addAll(restaurantDBManager.getAllRestaurant());
                                    resAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cencel, null)
                        .show();
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        restaurantDataList.clear();
        restaurantDataList.addAll(restaurantDBManager.getAllRestaurant());
        resAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opAdd:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_CODE);
                break;
            case R.id.opExit:
                AlertDialog.Builder builder_exit = new AlertDialog.Builder(MainActivity.this);
                builder_exit.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setNegativeButton(R.string.dialog_cencel, null)
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
                break;
            case R.id.opInfo:
                Intent infointent = new Intent(this, InfoActivity.class);
                startActivity(infointent);
                break;
            case R.id.opSearch:
                Intent searchintent = new Intent(this, SearchActivity.class);
                startActivity(searchintent);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String food = data.getStringExtra("restaurant");
                    Toast.makeText(this, food + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "맛집 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "맛집 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "맛집 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}