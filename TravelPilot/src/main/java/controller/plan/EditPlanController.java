package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.PlanManager;
import model.domain.Plan;

public class EditPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Plan 데이터 가져오기
        int planId = Integer.parseInt(request.getParameter("planId"));
        String planTitle = request.getParameter("planTitle");
        String country = request.getParameter("country");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        // PlanManager를 통해 Plan 수정
        PlanManager planManager = PlanManager.getInstance();
        planManager.editPlan(new Plan(planId, planTitle, country, startDate, endDate, isPublic));

        return "/myPage.jsp";
    }
}
