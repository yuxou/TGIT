package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ReviewManager;
import model.domain.Review;

import java.util.List;

public class ListReviewsController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sortBy = request.getParameter("sortBy");

        ReviewManager reviewManager = ReviewManager.getInstance();
        List<Review> reviews;

        if ("rating".equals(sortBy)) {
            reviews = reviewManager.sortReviewByRating();
        } else {
            reviews = reviewManager.sortReviewByDate();
        }

        request.setAttribute("reviews", reviews);
        return "/review/listReviews.jsp"; // 리뷰 목록 페이지로 이동
    }
}
