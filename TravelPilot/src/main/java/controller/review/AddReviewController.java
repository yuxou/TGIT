package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Review;
import model.service.ReviewManager;

public class AddReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        double rating = Double.parseDouble(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // 현재 날짜 기반으로 Review 객체 생성
        Review review = new Review();
        review.setPlaceId(placeId);
        review.setUserId(userId);
        review.setRating(rating);
        review.setComment(comment);

        ReviewManager reviewManager = ReviewManager.getInstance();
        reviewManager.addReview(review);

        return "/place/viewPlace.jsp?placeId=" + placeId;
    }
}
