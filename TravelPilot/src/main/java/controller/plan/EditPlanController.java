package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlanManager;

public class EditPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 요청에서 Plan 데이터를 가져오기
    	int planId = Integer.parseInt(request.getParameter("planId"));
    	String planTitle = request.getParameter("planTitle");
    	String description = request.getParameter("description");
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

    	// Plan 업데이트
    	PlanManager planManager = PlanManager.getInstance();
    	planManager.updatePlan(planId, planTitle, description, startDate, endDate, isPublic);
    	
    	return "/plan/viewPlan.jsp?planId=" + planId;

        }
    }
}
