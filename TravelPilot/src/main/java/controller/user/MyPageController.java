package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

import java.util.List;

public class MyPageController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null) {
            return "/user/login.jsp"; // 로그인 페이지로 이동
        }

        // PlanManager를 통해 작성한 Plan과 참여한 Plan 조회
        PlanManager planManager = PlanManager.getInstance();
        List<Plan> createdPlans = planManager.getPlansByCreator(userId);
        List<Plan> participatedPlans = planManager.getPlansByCompanion(userId);

        // 데이터를 View로 전달
        request.setAttribute("createdPlans", createdPlans);
        request.setAttribute("participatedPlans", participatedPlans);

        return "/user/myPage.jsp"; // MyPage View로 이동
    }
}
