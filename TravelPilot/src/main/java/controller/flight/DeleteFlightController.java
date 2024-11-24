package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.FlightManager;

public class DeleteFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight ID 가져오기
        int flightId = Integer.parseInt(request.getParameter("flightId"));

        // FlightManager를 통해 삭제
        FlightManager flightManager = FlightManager.getInstance();
        flightManager.deleteFlight(flightId);

        // Flight가 속한 Plan 상세 페이지로 리디렉션
        int planId = Integer.parseInt(request.getParameter("planId"));
        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
