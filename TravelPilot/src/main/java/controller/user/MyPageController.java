package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.PlanManager;
import model.domain.Plan;

import java.util.List;

public class MyPageController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");

        // 로그인 여부 확인
        if (userId == null) {
            return "/user/login.jsp"; // 로그인 페이지로 이동
        }

        try {
            // 사용자 정보 확인
            UserManager userManager = UserManager.getInstance();
            userManager.findUser(userId);

            // 작성한 계획 및 참여 계획 가져오기
            PlanManager planManager = PlanManager.getInstance();
            List<Plan> allPlans = planManager.getAllPlans();

            List<Plan> createdPlans = allPlans.stream()
                    .filter(plan -> plan.getWriter().getUserId().equals(userId))
                    .toList();
            List<Plan> participatedPlans = allPlans.stream()
                    .filter(plan -> plan.getCompanions().isPresent()
                            && plan.getCompanions().get().stream()
                                    .anyMatch(companion -> companion.getUserId().equals(userId)))
                    .toList();

            // View에 데이터 전달
            request.setAttribute("createdPlans", createdPlans);
            request.setAttribute("participatedPlans", participatedPlans);

            return "/user/myPage.jsp"; // MyPage로 이동

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "사용자 정보를 로드하는 중 문제가 발생했습니다.");
            return "/error.jsp"; // 에러 페이지로 이동
        }
    }
}
