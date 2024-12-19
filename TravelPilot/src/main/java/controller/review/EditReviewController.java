package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Review;
import model.service.ReviewManager;

public class EditReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // 수정할 Review 객체 생성
        Review updatedReview = new Review();
        updatedReview.setReviewId(reviewId);
        updatedReview.setRating(rating);
        updatedReview.setComment(comment);

        ReviewManager reviewManager = ReviewManager.getInstance();
        reviewManager.editReview(updatedReview);

        return "/place/viewPlace.jsp";
    }
}
