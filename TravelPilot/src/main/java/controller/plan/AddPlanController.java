package controller.plan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.Plan;
import model.domain.User;
import model.service.PlanManager;

import java.sql.Date;

public class AddPlanController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Plan 정보 가져오기
        String planTitle = request.getParameter("planTitle");
        String country = request.getParameter("country");
        Date startDate = Date.valueOf(request.getParameter("startDate")); // yyyy-MM-dd 형식
        Date endDate = Date.valueOf(request.getParameter("endDate"));     // yyyy-MM-dd 형식
        boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));

        // 세션에서 사용자 ID 가져와 작성자 설정
        String writerId = (String) request.getSession().getAttribute("userId");
        User writer = new User();
        writer.setUserId(writerId);

        // Plan 객체 생성
        Plan newPlan = new Plan();
        newPlan.setPlanTitle(planTitle);
        newPlan.setCountry(country);
        newPlan.setStartDate(startDate);
        newPlan.setEndDate(endDate);
        newPlan.setPublic(isPublic);
        newPlan.setWriter(writer);

        // PlanManager를 통해 새로운 Plan 생성
        PlanManager planManager = PlanManager.getInstance();
        planManager.createPlan(newPlan);

        return "redirect:/myPage.jsp";
    }
}
