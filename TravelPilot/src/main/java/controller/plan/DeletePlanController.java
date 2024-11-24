package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlanManager;

public class DeletePlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int planId = Integer.parseInt(request.getParameter("planId"));
    	
    	// PlanManager를 통해 Plan 및 관련 데이터 삭제
    	PlanManager planManager = PlanManager.getInstance();
    	planManager.deletePlan(planId);

    	return "/plan/listPlans.jsp"; // 목록으로 이동
        
    }
}
