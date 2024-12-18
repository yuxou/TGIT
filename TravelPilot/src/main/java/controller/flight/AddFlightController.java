package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Flight;
import model.service.PlanManager;

import java.sql.Date;

public class AddFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        Date departureDate = Date.valueOf(request.getParameter("departureDate")); // yyyy-MM-dd
        String departureTime = request.getParameter("departureTime");
        Date arrivalDate = Date.valueOf(request.getParameter("arrivalDate"));     // yyyy-MM-dd
        String arrivalTime = request.getParameter("arrivalTime");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Flight 객체 생성
        Flight flight = new Flight();
        flight.setDeparture(departure);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setDepartureTime(departureTime);
        flight.setArrivalDate(arrivalDate);
        flight.setArrivalTime(arrivalTime);
        flight.setCost(cost);

        // PlanManager를 통해 비행 일정 추가
        PlanManager planManager = PlanManager.getInstance();
        planManager.addFlightToPlan(planId, flight);

        return "/myPage.jsp";
    }
}
