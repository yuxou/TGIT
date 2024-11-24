package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Plan;
import model.domain.User;
import model.service.PlanManager;
import java.util.List;

public class CompanionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                return addCompanion(request, response);
            case "remove":
                return removeCompanion(request, response);
            case "view":
                return viewCompanions(request, response);
        }
        return null; // 아무 동작도 하지 않음
    }

    /**
     * 동반자 추가
     */
    private String addCompanion(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        PlanManager planManager = PlanManager.getInstance();
        planManager.addCompanion(planId, companionId);

        return "/plan/viewPlan.jsp?planId=" + planId; // Plan 상세 보기로 리다이렉트
    }

    /**
     * 동반자 제거
     */
    private String removeCompanion(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        PlanManager planManager = PlanManager.getInstance();
        planManager.removeCompanion(planId, companionId);

        return "/plan/viewPlan.jsp?planId=" + planId; // Plan 상세 보기로 리다이렉트
    }

    /**
     * 동반자 조회
     */
    private String viewCompanions(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        PlanManager planManager = PlanManager.getInstance();
        List<User> companions = planManager.getCompanions(planId);
        request.setAttribute("companions", companions);

        return "/plan/viewPlan.jsp"; // Plan 상세 View에서 동반자 정보 포함
    }
}
