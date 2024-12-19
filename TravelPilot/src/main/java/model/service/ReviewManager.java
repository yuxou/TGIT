package model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.dao.ReviewDAO;
import model.domain.Review;

public class ReviewManager {
    private static final ReviewManager instance = new ReviewManager(new ReviewDAO());
    private ReviewDAO reviewDAO;

    // 생성자
    public ReviewManager(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }
    
    public static ReviewManager getInstance() {
        return instance;
    }
    
    /**
     * 새로운 리뷰 추가
     * @param review
     */
    public void addReview(Review review) {
        try {
            reviewDAO.addReview(review);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add review");
        }
    }

    /**
     * 리뷰 수정
     * @param updatedReview
     */
    public void editReview(Review updatedReview) {
        try {
            reviewDAO.editReview(updatedReview);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to edit review");
        }
    }

    /**
     * 리뷰 삭제
     * @param reviewId
     */
    public void deleteReview(int reviewId) {
        try {
            reviewDAO.deleteReview(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete review");
        }
    }

    /**
     * 특정 리뷰 조회
     * @param reviewId
     * @return 리뷰 객체 (Optional)
     */
    public Optional<Review> viewReview(int reviewId) {
        try {
            return reviewDAO.viewReview(reviewId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to view review");
        }
    }

    /**
     * 날짜별 리뷰 정렬
     * @return 정렬된 리뷰 리스트
     */
    public List<Review> sortReviewByDate() {
        try {
            return reviewDAO.sortReviewByDate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to sort reviews by date");
        }
    }

    /**
     * 평점별 리뷰 정렬
     * @return 정렬된 리뷰 리스트
     */
    public List<Review> sortReviewByRating() {
        try {
            return reviewDAO.sortReviewByRating();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to sort reviews by rating");
        }
    }

    /**
     * 모든 리뷰 조회
     * @return 모든 리뷰 리스트
     */
    public List<Review> getAllReviews() {
        try {
            return reviewDAO.getAllReviews();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all reviews");
        }
    }
}