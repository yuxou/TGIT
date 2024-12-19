package controller.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;

public class SearchController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        switch (action) {
            case "places":
                return searchPlaces(request, response);
            case "plans":
                return searchPlans(request, response);
            default:
                return "/error.jsp"; // 잘못된 요청 처리
        }
    }

    private String searchPlaces(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        request.setAttribute("keyword", keyword);
        return "/searchPlace.do?keyword=" + keyword;
    }

    private String searchPlans(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        request.setAttribute("keyword", keyword);
        return "/searchPlan.do?keyword=" + keyword;
    }
}
