package model.domain;

import java.util.Calendar;
import java.util.Date;

public class Review {
    private int reviewId;
    private int placeId;
    private int userId;
    private double rating;
    private Date reviewDate;
    private String comment; // Optional

    public Review() { }

    public Review(int reviewId, int placeId, int userId, double rating, int reviewYear, int reviewMonth, int reviewDay) {
        this.reviewId = reviewId;
        this.placeId = placeId;
        this.userId = userId;
        this.rating = rating;
        this.reviewDate = createDate(reviewYear, reviewMonth, reviewDay);
    }

    public Review(int reviewId, int placeId, int userId, double rating, int reviewYear, 
                  int reviewMonth, int reviewDay, String comment) {
        this(reviewId, placeId, userId, rating, reviewYear, reviewMonth, reviewDay);
        this.comment = comment;
    }
    
    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); 
        return calendar.getTime();
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public Date getReviewDate() {
        return reviewDate;
    }
    
    public void setReviewDate(int year, int month, int day) {
        this.reviewDate = createDate(year, month, day);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void update(Review updatedReview) {
        this.placeId = updatedReview.placeId;
        this.userId = updatedReview.userId;
        this.rating = updatedReview.rating;
        this.reviewDate = updatedReview.reviewDate;
        this.comment = updatedReview.comment;
    }
}