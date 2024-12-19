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

        ReviewManager reviewManager = ReviewManager.getInstance();
        Optional<Review> review = reviewManager.viewReview(reviewId);

        if (review.isPresent()) {
            request.setAttribute("review", review.get());
            return "/review/viewReview.jsp";
        } else {
            request.setAttribute("error", "리뷰를 찾을 수 없습니다.");
            return "/error.jsp";
        }
    }
}
