package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

public class ViewPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String userId = (String) request.getSession().getAttribute("userId");

        PlanManager planManager = PlanManager.getInstance();
        Plan plan = planManager.getPlanWithDetails(planId); // Plan 조회

        // 공개된 Plan인지, 또는 작성자/참여자인지 확인
        if (!plan.isPublic() && !planManager.isUserAuthorizedForPlan(planId, userId)) {
            return "/plan/unauthorized.jsp"; // 권한이 없으면 접근 불가 페이지로 이동
        }

        request.setAttribute("plan", plan); // Plan 데이터를 View에 전달
        return "/plan/viewPlan.jsp";
    }
}
