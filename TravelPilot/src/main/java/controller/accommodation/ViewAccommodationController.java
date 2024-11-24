package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Accommodation;
import model.service.AccommodationManager;

public class ViewAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));

        // AccommodationManager를 통해 데이터 조회
        AccommodationManager accommodationManager = AccommodationManager.getInstance();
        Accommodation accommodation = accommodationManager.getAccommodationById(accommodationId);

        // 조회한 데이터를 View에 전달
        request.setAttribute("accommodation", accommodation);
        return "/accommodation/viewAccommodation.jsp";
    }
}
