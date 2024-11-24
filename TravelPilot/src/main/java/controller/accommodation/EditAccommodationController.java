package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.AccommodationManager;

public class EditAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));
        String name = request.getParameter("name");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Accommodation 정보 수정
        AccommodationManager accommodationManager = AccommodationManager.getInstance();
        accommodationManager.updateAccommodation(accommodationId, name, checkInDate, checkOutDate, cost);

        int planId = Integer.parseInt(request.getParameter("planId"));
        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
