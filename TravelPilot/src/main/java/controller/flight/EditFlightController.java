package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.FlightManager;

public class EditFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight 데이터 가져오기
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Flight 정보 수정
        FlightManager flightManager = FlightManager.getInstance();
        flightManager.updateFlight(flightId, departure, arrival, departureTime, arrivalTime, cost);

        // Flight가 속한 Plan 상세 페이지로 리디렉션
        int planId = Integer.parseInt(request.getParameter("planId"));
        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
