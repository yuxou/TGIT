package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Flight;
import model.service.PlanManager;

import java.util.List;

public class ViewFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));

        // PlanManager를 통해 해당 계획의 비행 일정 목록 조회
        PlanManager planManager = PlanManager.getInstance();
        List<Flight> flights = planManager.getFlightsByPlanId(planId);

        // 특정 Flight ID와 일치하는 항목 필터링
        Flight targetFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightId() == flightId) {
                targetFlight = flight;
                break;
            }
        }

        // 조회된 Flight 데이터 전달
        if (targetFlight != null) {
            request.setAttribute("flight", targetFlight);
        }

        return "/myPage.jsp"; // 일관된 JSP 페이지 경로
    }
}
