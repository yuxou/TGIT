package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;
import model.domain.Plan;

public class EditPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String planTitle = request.getParameter("planTitle");
        String country = request.getParameter("country");
        int startYear = Integer.parseInt(request.getParameter("startYear"));
        int startMonth = Integer.parseInt(request.getParameter("startMonth"));
        int startDay = Integer.parseInt(request.getParameter("startDay"));
        int endYear = Integer.parseInt(request.getParameter("endYear"));
        int endMonth = Integer.parseInt(request.getParameter("endMonth"));
        int endDay = Integer.parseInt(request.getParameter("endDay"));
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        Plan updatedPlan = new Plan();
        updatedPlan.setPlanId(planId);
        updatedPlan.setPlanTitle(planTitle);
        updatedPlan.setCountry(country);
        updatedPlan.setStartDate(startYear, startMonth, startDay);
        updatedPlan.setEndDate(endYear, endMonth, endDay);
        updatedPlan.setPublic(isPublic);

        PlanManager planManager = PlanManager.getInstance();
        planManager.editPlan(updatedPlan);

        return "/myPage.jsp";
    }
}
