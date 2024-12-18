package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Review;
import model.service.ReviewManager;

import java.util.Date;

public class EditReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int placeId = Integer.parseInt(request.getParameter("placeId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        double rating = Double.parseDouble(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // 현재 날짜로 수정
        Date reviewDate = new Date();
        Review updatedReview = new Review(reviewId, placeId, userId, rating, 
                                           reviewDate.getYear() + 1900, 
                                           reviewDate.getMonth() + 1, 
                                           reviewDate.getDate(), comment);

        ReviewManager reviewManager = new ReviewManager(new ReviewDAO());
        reviewManager.editReview(updatedReview);

        return "/place/viewPlace.jsp?placeId=" + placeId; // 수정된 장소 상세 페이지로 이동
    }
}
