package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Flight;
import model.service.PlanManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight 데이터 가져오기
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");

        // 날짜 파싱
        LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"), DateTimeFormatter.ISO_DATE);
        String departureTime = request.getParameter("departureTime");
        LocalDate arrivalDate = LocalDate.parse(request.getParameter("arrivalDate"), DateTimeFormatter.ISO_DATE);
        String arrivalTime = request.getParameter("arrivalTime");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Flight 객체 생성
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flight.setDeparture(departure);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate.getYear(), departureDate.getMonthValue(), departureDate.getDayOfMonth());
        flight.setDepartureTime(departureTime);
        flight.setArrivalDate(arrivalDate.getYear(), arrivalDate.getMonthValue(), arrivalDate.getDayOfMonth());
        flight.setArrivalTime(arrivalTime);
        flight.setCost(cost);

        // PlanManager를 통해 비행 일정 수정
        PlanManager planManager = PlanManager.getInstance();
        planManager.updateFlight(flightId, flight);

        // Flight가 속한 Plan 상세 페이지로 리다이렉트
        int planId = Integer.parseInt(request.getParameter("planId"));
        return "/myPage.jsp";
    }
}
