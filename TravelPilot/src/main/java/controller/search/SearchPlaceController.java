package controller.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlaceManager;
import model.domain.Place;

import java.util.List;

public class SearchPlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");

        PlaceManager placeManager = PlaceManager.getInstance();
        List<Place> places = placeManager.searchPlaces(keyword);

        request.setAttribute("places", places);
        request.setAttribute("keyword", keyword);
        return "/place/searchPlace.jsp"; // 장소 검색 결과 페이지로 이동
    }
}
