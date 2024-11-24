package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.AccommodationManager;

public class DeleteAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));

        // Accommodation 삭제
        AccommodationManager accommodationManager = AccommodationManager.getInstance();
        accommodationManager.deleteAccommodation(accommodationId);

        int planId = Integer.parseInt(request.getParameter("planId"));
        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
