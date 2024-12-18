package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Review;
import model.service.ReviewManager;

import java.util.Date;

public class AddReviewController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        double rating = Double.parseDouble(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // 현재 날짜로 리뷰 작성
        Date reviewDate = new Date();
        Review review = new Review(0, placeId, userId, rating, reviewDate.getYear() + 1900, 
                                   reviewDate.getMonth() + 1, reviewDate.getDate(), comment);

        ReviewManager reviewManager = new ReviewManager(new ReviewDAO());
        reviewManager.addReview(review);

        return "/place/viewPlace.jsp?placeId=" + placeId; // 작성된 장소 상세 페이지로 리다이렉트
    }
}
