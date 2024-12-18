package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Place;
import model.service.PlaceManager;

import java.util.List;

public class ListPlacesController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PlaceManager placeManager = PlaceManager.getInstance();
        List<Place> places = placeManager.getAllPlaces();

        request.setAttribute("places", places);
        return "/myPage.jsp";
    }
}
