package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import model.domain.Plan;
import model.service.PlanManager;

public class ViewPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword"); // 키워드 가져오기

        PlanManager planManager = PlanManager.getInstance();

        List<Plan> plans;
        if (keyword == null || keyword.trim().isEmpty()) {
            plans = planManager.getAllPlans(); // 키워드 없이 모든 계획 조회
        } else {
            plans = planManager.getAllPlans(keyword); // 키워드 기반 계획 조회
        }

        // 조회 결과를 요청에 전달
        request.setAttribute("plans", plans);

        return "/myPage.jsp";
    }
}
