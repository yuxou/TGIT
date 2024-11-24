package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Flight;
import model.service.FlightManager;

public class AddFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight 데이터 가져오기
        int planId = Integer.parseInt(request.getParameter("planId"));
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Flight 객체 생성
        Flight flight = new Flight();
        flight.setPlanId(planId);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setCost(cost);

        // FlightManager를 통해 데이터 추가
        FlightManager flightManager = FlightManager.getInstance();
        flightManager.addFlight(flight);

        // Plan 상세 페이지로 리디렉션
        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
