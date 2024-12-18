package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;

public class DeleteFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight ID와 Plan ID 가져오기
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        int planId = Integer.parseInt(request.getParameter("planId"));

        // PlanManager를 통해 비행 일정 삭제
        PlanManager planManager = PlanManager.getInstance();
        planManager.deleteFlight(flightId);

        // Plan 상세 페이지로 리디렉션
        return "/myPage.jsp"; 
    }
}
