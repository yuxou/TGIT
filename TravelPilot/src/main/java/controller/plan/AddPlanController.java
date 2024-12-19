package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Plan;
import model.domain.User;
import model.service.PlanManager;

public class AddPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Plan 정보 가져오기
        String planTitle = request.getParameter("planTitle");
        String country = request.getParameter("country");
        int startYear = Integer.parseInt(request.getParameter("startYear"));
        int startMonth = Integer.parseInt(request.getParameter("startMonth"));
        int startDay = Integer.parseInt(request.getParameter("startDay"));
        int endYear = Integer.parseInt(request.getParameter("endYear"));
        int endMonth = Integer.parseInt(request.getParameter("endMonth"));
        int endDay = Integer.parseInt(request.getParameter("endDay"));
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        String writerId = (String) request.getSession().getAttribute("userId");
        User writer = new User(writerId, null, null, null, null);

        Plan plan = new Plan();
        plan.setPlanTitle(planTitle);
        plan.setCountry(country);
        plan.setStartDate(startYear, startMonth, startDay);
        plan.setEndDate(endYear, endMonth, endDay);
        plan.setPublic(isPublic);
        plan.setWriter(writer);

        PlanManager planManager = PlanManager.getInstance();
        planManager.createPlan(plan);

        return "/myPage.jsp";
    }
}
