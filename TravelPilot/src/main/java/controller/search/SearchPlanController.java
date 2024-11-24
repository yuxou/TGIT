package controller.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlanManager;
import model.domain.Plan;

import java.util.List;

public class SearchPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");

        PlanManager planManager = PlanManager.getInstance();
        List<Plan> plans = planManager.searchPlans(keyword);

        request.setAttribute("plans", plans);
        request.setAttribute("keyword", keyword);
        return "/plan/searchPlan.jsp"; // 일정 검색 결과 페이지로 이동
    }
}
