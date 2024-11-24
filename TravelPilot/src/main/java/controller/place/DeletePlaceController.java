package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlaceManager;

public class DeletePlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        PlaceManager placeManager = PlaceManager.getInstance();
        placeManager.deletePlace(placeId);

        return "/place/listPlaces.jsp"; // 장소 목록 페이지로 리다이렉트
    }
}
