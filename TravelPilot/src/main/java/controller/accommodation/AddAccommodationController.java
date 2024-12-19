package controller.accommodation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Accommodation;
import model.service.PlanManager;

import java.sql.Date;

public class AddAccommodationController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int planId = Integer.parseInt(request.getParameter("planId"));
        String name = request.getParameter("name");
        Date checkInDate = Date.valueOf(request.getParameter("checkInDate"));   // yyyy-MM-dd
        Date checkOutDate = Date.valueOf(request.getParameter("checkOutDate")); // yyyy-MM-dd
        double cost = Double.parseDouble(request.getParameter("cost"));

        // Accommodation 객체 생성
        Accommodation accommodation = new Accommodation();
        accommodation.setName(name);
        accommodation.setCheckInDate(checkInDate);
        accommodation.setCheckOutDate(checkOutDate);
        accommodation.setCost(cost);

        // PlanManager를 통해 숙소 추가
        PlanManager planManager = PlanManager.getInstance();
        planManager.addAccommodationsToPlan(planId, accommodation);

        // Plan 상세 페이지로 리다이렉트
        return "/myPage.jsp";
    }
}
