package controller.flight;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Flight;
import model.service.FlightManager;

public class ViewFlightController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 요청에서 Flight ID 가져오기
        int flightId = Integer.parseInt(request.getParameter("flightId"));

        // FlightManager를 통해 데이터 조회
        FlightManager flightManager = FlightManager.getInstance();
        Flight flight = flightManager.getFlightById(flightId);

        // 조회한 Flight 데이터를 View로 전달
        request.setAttribute("flight", flight);
        return "/flight/viewFlight.jsp";
    }
}
