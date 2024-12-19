package controller.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");

        PlanManager planManager = PlanManager.getInstance();
        List<Plan> allPlans = planManager.getAllPlans(); // 모든 계획 가져오기

        // 키워드 기반 필터링
        List<Plan> filteredPlans = allPlans.stream()
                .filter(plan -> plan.getPlanTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        request.setAttribute("plans", filteredPlans);
        request.setAttribute("keyword", keyword);
        return "/plan/searchPlan.jsp"; // 검색 결과 페이지로 이동
    }
}
