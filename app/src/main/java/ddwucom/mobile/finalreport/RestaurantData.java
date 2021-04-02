package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class RestaurantData implements Serializable {
    private long _id;
    private float rating;
    private String name;
    private String menu;
    private String review;

    public RestaurantData(float rating, String name, String menu, String review){
        this.rating = rating;
        this.name = name;
        this.menu = menu;
        this.review = review;
    }

    public RestaurantData(long _id, float rating, String name, String menu, String review) {
        this._id = _id;
        this.rating = rating;
        this.name = name;
        this.menu = menu;
        this.review = review;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}