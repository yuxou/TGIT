package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

public class ViewPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String userId = (String) request.getSession().getAttribute("userId");

        PlanManager planManager = PlanManager.getInstance();
        Plan plan = planManager.findPlanById(planId).orElse(null); // Plan 조회

        // Plan이 존재하지 않거나 권한이 없으면 데이터 전달하지 않음
        if (plan != null && (plan.isPublic() || planManager.isUserAuthorizedForPlan(planId, userId))) {
            request.setAttribute("plan", plan); // Plan 정보만 전달
        }

        return "/myPage.jsp";
    }
}
