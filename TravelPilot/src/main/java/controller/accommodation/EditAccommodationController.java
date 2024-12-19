package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Accommodation;
import model.service.PlanManager;

import java.sql.Date;

public class EditAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int accommodationId = Integer.parseInt(request.getParameter("accommodationId"));
        String name = request.getParameter("name");
        Date checkInDate = Date.valueOf(request.getParameter("checkInDate"));
        Date checkOutDate = Date.valueOf(request.getParameter("checkOutDate"));
        double cost = Double.parseDouble(request.getParameter("cost"));

        Accommodation updatedAccommodation = new Accommodation();
        updatedAccommodation.setAccommodationId(accommodationId);
        updatedAccommodation.setName(name);
        updatedAccommodation.setCheckInDate(checkInDate);
        updatedAccommodation.setCheckOutDate(checkOutDate);
        updatedAccommodation.setCost(cost);

        PlanManager planManager = PlanManager.getInstance();
        planManager.updateAccommodation(updatedAccommodation);

        return "/myPage.jsp";
    }
}
