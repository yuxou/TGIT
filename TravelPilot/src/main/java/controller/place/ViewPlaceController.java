package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Place;
import model.service.PlaceManager;

import java.util.List;

public class ViewPlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        // 모든 장소 조회 후 필터링
        PlaceManager placeManager = PlaceManager.getInstance();
        List<Place> places = placeManager.getAllPlaces();

        Place targetPlace = null;
        for (Place place : places) {
            if (place.getPlaceId() == placeId) {
                targetPlace = place;
                break;
            }
        }

        // 결과 전달
        if (targetPlace != null) {
            request.setAttribute("place", targetPlace);
        }

        return "/myPage.jsp"; // 리턴 경로
    }
}
