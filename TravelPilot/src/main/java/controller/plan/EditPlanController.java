package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;

public class EditPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Plan 데이터를 가져오기
        int planId = Integer.parseInt(request.getParameter("planId"));
        String planTitle = request.getParameter("planTitle");
        String country = request.getParameter("country");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        // PlanManager를 통해 Plan 업데이트
        PlanManager planManager = PlanManager.getInstance();
        planManager.updatePlan(planId, planTitle, country, startDate, endDate, isPublic);

        // 수정된 Plan의 상세 페이지로 리디렉트
        return "/myPage.jsp";
    }
}
