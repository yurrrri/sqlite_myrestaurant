package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RestaurantDBManager {
    RestaurantDBHelper restaurantDBHelper = null;
    Cursor cursor = null;

    public RestaurantDBManager(Context context) {
        restaurantDBHelper = new RestaurantDBHelper(context);
    }

    public ArrayList<RestaurantData> getAllRestaurant() {
        ArrayList restaurantList = new ArrayList();
        SQLiteDatabase db = restaurantDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + restaurantDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(restaurantDBHelper.COL_ID));
            float rating = cursor.getFloat(cursor.getColumnIndex(restaurantDBHelper.COL_RATING));
            String name = cursor.getString(cursor.getColumnIndex(restaurantDBHelper.COL_NAME));
            String menu = cursor.getString(cursor.getColumnIndex(restaurantDBHelper.COL_MENU));
            String review = cursor.getString(cursor.getColumnIndex(restaurantDBHelper.COL_REVIEW));

            restaurantList.add ( new RestaurantData (id, rating, name, menu, review) );
        }

        cursor.close();
        restaurantDBHelper.close();
        return restaurantList;
    }

    public boolean addNewRestaurant(RestaurantData newRestaurant) {
        SQLiteDatabase db = restaurantDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(restaurantDBHelper.COL_RATING, newRestaurant.getRating());
        value.put(restaurantDBHelper.COL_NAME, newRestaurant.getName());
        value.put(restaurantDBHelper.COL_MENU, newRestaurant.getMenu());
        value.put(restaurantDBHelper.COL_REVIEW, newRestaurant.getReview());

        long count = db.insert(restaurantDBHelper.TABLE_NAME, null, value);
        restaurantDBHelper.close();
        if (count > 0) return true;
        return false;
    }

    public boolean updateRestaurant(RestaurantData restaurantData) {
        SQLiteDatabase sqLiteDatabase = restaurantDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(restaurantDBHelper.COL_RATING, restaurantData.getRating());
        row.put(restaurantDBHelper.COL_NAME, restaurantData.getName());
        row.put(restaurantDBHelper.COL_MENU, restaurantData.getMenu());
        row.put(restaurantDBHelper.COL_REVIEW, restaurantData.getReview());
        String whereClause = restaurantDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(restaurantData.get_id()) };
        int result = sqLiteDatabase.update(restaurantDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        restaurantDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    public boolean removeRestaurant(long id) {
        SQLiteDatabase sqLiteDatabase = restaurantDBHelper.getWritableDatabase();
        String whereClause = restaurantDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(restaurantDBHelper.TABLE_NAME, whereClause,whereArgs);
        restaurantDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    public String searchRestaurant(String key){

        SQLiteDatabase sqLiteDatabase = restaurantDBHelper.getReadableDatabase();

        String selection = "name=?";
        String[] selectArgs = new String[]{key};
        Cursor cursor =
                sqLiteDatabase.query(restaurantDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        String result = "";
        while (cursor.moveToNext()){
            result+=cursor.getLong(cursor.getColumnIndex(restaurantDBHelper.COL_ID))+". 맛집 이름: ";
            result+=cursor.getString(cursor.getColumnIndex(restaurantDBHelper.COL_NAME))+"/ 메뉴 이름: ";
            result+=cursor.getString(cursor.getColumnIndex(restaurantDBHelper.COL_MENU))+"/ 평점: ";
            result+=cursor.getFloat(cursor.getColumnIndex(restaurantDBHelper.COL_RATING))+"\n";
        }

        cursor.close();

        return result;
    }

    public void close() {
        if (restaurantDBHelper != null) restaurantDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
