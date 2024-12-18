package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.User;
import model.service.PlanManager;

import java.util.List;

public class CompanionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            return addCompanion(request);
        } else if ("remove".equals(action)) {
            return removeCompanion(request);
        } else if ("view".equals(action)) {
            return viewCompanions(request);
        }
        return null; 
    }

    /**
     * 동반자 추가
     */
    private String addCompanion(HttpServletRequest request) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        PlanManager planManager = PlanManager.getInstance();
        planManager.addCompanionToPlan(planId, new User(companionId, null, null, null, null));

        return "/myPage.jsp"; 

    /**
     * 동반자 제거
     */
    private String removeCompanion(HttpServletRequest request) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        PlanManager planManager = PlanManager.getInstance();
        planManager.deleteCompanion(planId, companionId);

        return "/myPage.jsp";

    /**
     * 동반자 조회
     */
    private String viewCompanions(HttpServletRequest request) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        PlanManager planManager = PlanManager.getInstance();
        List<User> companions = planManager.getCompanionsByPlanId(planId);
        request.setAttribute("companions", companions);

        return "/myPage.jsp"; 
}
