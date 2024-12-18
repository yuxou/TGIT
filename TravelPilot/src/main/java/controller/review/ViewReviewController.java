package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Review;
import model.service.ReviewManager;

import java.util.Optional;

public class ViewReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));

        ReviewManager reviewManager = new ReviewManager(new ReviewDAO());
        Optional<Review> review = reviewManager.viewReview(reviewId);

        if (review.isPresent()) {
            request.setAttribute("review", review.get());
            return "/review/viewReview.jsp"; // 리뷰 상세 페이지로 이동
        } else {
            return "/error.jsp"; // 리뷰가 없는 경우 에러 처리
        }
    }
}
