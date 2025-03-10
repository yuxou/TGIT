package model.dao;

import java.sql.*;
import java.util.*;
import model.domain.Review;

public class ReviewDAO {
    private JDBCUtil jdbcUtil = null; // JDBCUtil 객체 생성하여 데이터베이스 연결을 관리

    public ReviewDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 초기화
    }

    /**
     * 리뷰 추가
     * @param review
     * @throws Exception
     */
    public void addReview(Review review) throws Exception {
        String sql = "INSERT INTO reviews (reviewId, placeId, userId, rating, reviewDate, comment) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[] { 
            review.getReviewId(), 
            review.getPlaceId(), 
            review.getUserId(),
            review.getRating(),
            new java.sql.Date(review.getReviewDate().getTime()), 
            review.getComment() 
        };

        jdbcUtil.setSqlAndParameters(sql, param);
        try {
            jdbcUtil.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // 리소스 반환
        }
    }

    /**
     * 리뷰 수정
     * @param updatedReview
     * @throws Exception 
     */
    public void editReview(Review updatedReview) throws Exception {
        String sql = "UPDATE reviews SET placeId = ?, userId = ?, rating = ?, reviewDate = ?, comment = ? WHERE reviewId = ?";
        Object[] param = new Object[] { 
            updatedReview.getPlaceId(), 
            updatedReview.getUserId(),
            updatedReview.getRating(),
            new java.sql.Date(updatedReview.getReviewDate().getTime()),
            updatedReview.getComment(),
            updatedReview.getReviewId() 
        };

        // JDBCUtil을 통해 SQL 문과 파라미터 설정 후 실행
        jdbcUtil.setSqlAndParameters(sql, param);
        try {
            jdbcUtil.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // 리소스 반환
        }
    }

    /**
     * 리뷰 삭제
     * @param reviewId
     * @throws Exception 
     */
    public void deleteReview(int reviewId) throws Exception {
        String sql = "DELETE FROM reviews WHERE reviewId = ?";
        Object[] param = new Object[] { reviewId };
        
        // JDBCUtil을 통해 SQL 문과 파라미터 설정 후 실행
        jdbcUtil.setSqlAndParameters(sql, param);
        try {
            jdbcUtil.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(); // 리소스 반환
        }
    }

    /**
     * 리뷰 조회 (단일 리뷰)
     * @param reviewId
     * @return
     * @throws SQLException
     */
    public Optional<Review> viewReview(int reviewId) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE reviewId = ?";
        Object[] param = new Object[] { reviewId };
        
        // JDBCUtil을 통해 SQL 문과 파라미터 설정 후 실행
        jdbcUtil.setSqlAndParameters(sql, param);
        ResultSet rs = jdbcUtil.executeQuery(); // 쿼리 실행
        
        if (rs.next()) {
            Review review = new Review(
                rs.getInt("reviewId"),
                rs.getInt("placeId"),
                rs.getInt("userId"),
                rs.getDouble("rating"),
                rs.getDate("reviewDate").toLocalDate().getYear(),
                rs.getDate("reviewDate").toLocalDate().getMonthValue(),
                rs.getDate("reviewDate").toLocalDate().getDayOfMonth(),
                rs.getString("comment")
            );
            return Optional.of(review); // 리뷰 객체 반환
        } else {
            return Optional.empty(); // 리뷰가 존재하지 않으면 빈 Optional 반환
        }
    }

    /**
     * 날짜별 리뷰 정렬
     * @return
     * @throws SQLException
     */
    public List<Review> sortReviewByDate() throws SQLException {
        List<Review> reviews = getAllReviews(); // 모든 리뷰 가져오기
        reviews.sort(Comparator.comparing(Review::getReviewDate)); // 날짜 기준으로 정렬
        return reviews; // 정렬된 리뷰 리스트 반환
    }

    /**
     * 평점별 리뷰 정렬
     * @return
     * @throws SQLException
     */
    public List<Review> sortReviewByRating() throws SQLException {
        List<Review> reviews = getAllReviews(); // 모든 리뷰 가져오기
        reviews.sort(Comparator.comparingDouble(Review::getRating).reversed()); // 평점 기준으로 내림차순 정렬
        return reviews; // 정렬된 리뷰 리스트 반환
    }

    /**
     * 모든 리뷰 조회
     * @return
     * @throws SQLException
     */
    public List<Review> getAllReviews() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews"; 
        jdbcUtil.setSqlAndParameters(sql, null); // SQL 설정
        
        ResultSet rs = jdbcUtil.executeQuery(); // 쿼리 실행
        while (rs.next()) {
            Review review = new Review(
                rs.getInt("reviewId"),
                rs.getInt("placeId"),
                rs.getInt("userId"),
                rs.getDouble("rating"),
                rs.getDate("reviewDate").toLocalDate().getYear(),
                rs.getDate("reviewDate").toLocalDate().getMonthValue(),
                rs.getDate("reviewDate").toLocalDate().getDayOfMonth(),
                rs.getString("comment")
            );
            reviews.add(review); // 리뷰 객체 추가
        }
        return reviews; // 모든 리뷰 리스트 반환
    }
}