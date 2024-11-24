package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Place;
import model.service.PlaceManager;

public class ViewPlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        PlaceManager placeManager = PlaceManager.getInstance();
        Place place = placeManager.getPlaceById(placeId);

        request.setAttribute("place", place);
        return "/place/viewPlace.jsp"; // 장소 상세보기 페이지로 이동
    }
}
