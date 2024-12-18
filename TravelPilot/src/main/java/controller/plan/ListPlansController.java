package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import controller.Controller;
import model.domain.Plan;
import model.service.PlanManager;

public class ListPlansController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 현재 사용자 ID 가져오기
        String userId = (String) request.getSession().getAttribute("userId");

        // PlanManager에서 작성한 Plan 및 참여한 Plan 조회
        PlanManager planManager = PlanManager.getInstance();
        List<Plan> createdPlans = planManager.getPlansByCreator(userId);
        List<Plan> participatingPlans = planManager.getPlansByCompanion(userId);

        // View에 데이터 전달
        request.setAttribute("createdPlans", createdPlans);
        request.setAttribute("participatingPlans", participatingPlans);

        return "/myPage.jsp"; // 사용자 Plan 목록 페이지
    }
}
