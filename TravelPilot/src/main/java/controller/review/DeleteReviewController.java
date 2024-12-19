package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));

        ReviewManager reviewManager = ReviewManager.getInstance();
        reviewManager.deleteReview(reviewId);

        return "/place/viewPlace.jsp";
    }
}
