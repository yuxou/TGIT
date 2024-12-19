package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.User;
import model.service.PlanManager;

public class CompanionController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        PlanManager planManager = PlanManager.getInstance();

        if ("add".equals(action)) {
            return addCompanion(request, planManager);
        } else if ("remove".equals(action)) {
            return removeCompanion(request, planManager);
        }
        return "/myPage.jsp";
    }

    private String addCompanion(HttpServletRequest request, PlanManager planManager) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        planManager.addCompanionToPlan(planId, new User(companionId, null, null, null, null));

        return "/myPage.jsp";
    }

    private String removeCompanion(HttpServletRequest request, PlanManager planManager) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String companionId = request.getParameter("companionId");

        planManager.deleteCompanion(planId, companionId);

        return "/myPage.jsp";
    }
}
