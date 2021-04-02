package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "meals.db";
    public final static String TABLE_NAME = "meal_table";

    public final static String COL_ID = "_id";
    public final static String COL_RATING = "rating";
    public final static String COL_NAME = "name";
    public final static String COL_MENU = "menu";
    public final static String COL_REVIEW = "review";

    public RestaurantDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_RATING + " TEXT, " + COL_NAME + " TEXT, " + COL_MENU + " TEXT, " + COL_REVIEW + " TEXT)";
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, 5, '방이곱창', '소곱창모듬', '양이 많고 맛있다');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 4, '여수 디디치킨', '커플세트', '양이 많고 순두부찌개가 나오는 치킨집');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 4.5, '전미원', '삼겹살', '정원에서 먹는 삼겹살 분위기가 다했다');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 4.5, '연안식당', '꼬막비빔밥', '양이 정말 많고 맛있다');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 3.5, '버거앤파스타', '로제파스타&햄버거', '앞에 바다가 있는 버거파스타집');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
