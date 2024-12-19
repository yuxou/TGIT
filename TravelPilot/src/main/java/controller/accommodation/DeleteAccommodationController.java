package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlanManager;

public class DeleteAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));

        PlanManager planManager = PlanManager.getInstance();
        planManager.deleteAccommodation(accommodationId);

        return "/myPage.jsp";
    }
}
