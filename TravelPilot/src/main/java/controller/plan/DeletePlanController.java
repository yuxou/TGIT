package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;

public class DeletePlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));

        // PlanManager를 통해 Plan 삭제
        PlanManager planManager = PlanManager.getInstance();
        planManager.deletePlan(planId);

        // 사용자 계획 목록 페이지로 이동
        return "/myPage.jsp";
    }
}
