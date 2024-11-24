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
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        // 현재 세션에서 사용자 정보 가져오기 (작성자 정보)
        String writerId = (String) request.getSession().getAttribute("userId");
        User writer = new User(); // User 객체 생성
        writer.setUserId(writerId); // 작성자 ID 설정

        // PlanManager를 통해 새로운 Plan 생성
        PlanManager planManager = PlanManager.getInstance();
        Plan newPlan = planManager.createPlan(planTitle, description, startDate, endDate, isPublic, writer);

        // 생성된 Plan 상세 페이지로 리디렉션
        return "/plan/viewPlan.jsp?planId=" + newPlan.getPlanId();
    }
}