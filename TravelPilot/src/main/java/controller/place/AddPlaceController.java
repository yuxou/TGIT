package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.domain.Place;
import model.service.PlaceManager;

public class AddPlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String memo = request.getParameter("memo");

        Place place = new Place(0, name, location, memo); // PlaceId는 자동 생성
        PlaceManager placeManager = PlaceManager.getInstance();
        placeManager.addPlace(place);

        return "/place/listPlaces.jsp"; // 장소 목록 페이지로 리다이렉트
    }
}
