package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Accommodation;
import model.service.AccommodationManager;

public class AddAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String name = request.getParameter("name");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Accommodation 객체 생성
        Accommodation accommodation = new Accommodation();
        accommodation.setPlanId(planId);
        accommodation.setName(name);
        accommodation.setCheckInDate(checkInDate);
        accommodation.setCheckOutDate(checkOutDate);
        accommodation.setCost(cost);

        // AccommodationManager를 통해 데이터 추가
        AccommodationManager accommodationManager = AccommodationManager.getInstance();
        accommodationManager.addAccommodation(accommodation);

        return "/plan/viewPlan.jsp?planId=" + planId;
    }
}
