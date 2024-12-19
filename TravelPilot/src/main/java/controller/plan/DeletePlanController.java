package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;

public class DeletePlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        PlanManager planManager = PlanManager.getInstance();
        planManager.deletePlan(planId);

        return "/myPage.jsp";
    }
}
