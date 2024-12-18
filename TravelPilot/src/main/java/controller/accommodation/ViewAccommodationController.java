package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Accommodation;
import model.service.PlanManager;

public class ViewAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));

        PlanManager planManager = PlanManager.getInstance();
        Accommodation accommodation = planManager.getAccommodationById(accommodationId);

        request.setAttribute("accommodation", accommodation);
        return "/myPage.jsp";
    }
}
