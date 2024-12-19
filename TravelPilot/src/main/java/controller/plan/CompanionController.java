package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.User;
import model.service.PlanManager;

public class CompanionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        PlanManager planManager = PlanManager.getInstance();

        switch (action) {
            case "add":
                return addCompanion(request, planManager);
            case "remove":
                return removeCompanion(request, planManager);
            default:
                return "/myPage.jsp";
        }
    }

    private String addCompanion(HttpServletRequest request, PlanManager planManager) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        User companion = new User(companionId, null, null, null, null); // User 객체 생성
        planManager.addCompanionToPlan(planId, companion);

        return "/plan/viewPlan.jsp?planId=" + planId; // 계획 상세 페이지로 리다이렉트
    }

    private String removeCompanion(HttpServletRequest request, PlanManager planManager) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        int companionId = Integer.parseInt(request.getParameter("companionId"));

        planManager.deleteCompanion(planId, companionId);

        return "/plan/viewPlan.jsp?planId=" + planId; // 계획 상세 페이지로 리다이렉트
    }
}
