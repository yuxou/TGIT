package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        ReviewManager reviewManager = new ReviewManager(new ReviewDAO());
        reviewManager.deleteReview(reviewId);

        return "/place/viewPlace.jsp?placeId=" + placeId; // 삭제된 리뷰의 장소로 이동
    }
}
