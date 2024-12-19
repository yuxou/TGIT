package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Plan;
import model.domain.User;
import model.service.PlanManager;

import java.util.ArrayList;
import java.util.List;

public class ListPlansController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");

        PlanManager planManager = PlanManager.getInstance();
        List<Plan> allPlans = planManager.getAllPlans(); // 모든 계획 가져오기

        // 작성한 계획과 참여한 계획 필터링
        List<Plan> createdPlans = new ArrayList<>();
        List<Plan> participatingPlans = new ArrayList<>();

        for (Plan plan : allPlans) {
            // 작성한 계획 확인
            if (plan.getWriter().getUserId().equals(userId)) {
                createdPlans.add(plan);
            } 
            // 참여한 계획 확인
            else if (plan.getCompanions().isPresent()) { // Optional 값 확인
                List<User> companions = plan.getCompanions().get();
                if (companions.stream().anyMatch(companion -> companion.getUserId().equals(userId))) {
                    participatingPlans.add(plan);
                }
            }
        }

        // 데이터를 View에 전달
        request.setAttribute("createdPlans", createdPlans);
        request.setAttribute("participatingPlans", participatingPlans);

        return "/myPage.jsp"; // 마이페이지로 이동
    }
}
