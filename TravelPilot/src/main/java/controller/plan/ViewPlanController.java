package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

import java.util.List;

public class ViewPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String userId = (String) request.getSession().getAttribute("userId");

        PlanManager planManager = PlanManager.getInstance();
        List<Plan> plans = planManager.getAllPlans(); // 모든 계획 가져오기

        Plan targetPlan = null;
        for (Plan plan : plans) {
            if (plan.getPlanId() == planId) {
                targetPlan = plan;
                break;
            }
        }

        if (targetPlan == null) {
            request.setAttribute("error", "해당 계획을 찾을 수 없습니다.");
            return "/error.jsp"; // 에러 페이지로 이동
        }

        if (!targetPlan.isPublic() && !targetPlan.getWriter().getUserId().equals(userId)) {
            request.setAttribute("error", "계획에 접근할 권한이 없습니다.");
            return "/error.jsp"; // 권한 없음 페이지로 이동
        }

        request.setAttribute("plan", targetPlan); // 계획 데이터를 View에 전달
        return "/plan/viewPlan.jsp"; // 계획 상세 페이지로 이동
    }
}
