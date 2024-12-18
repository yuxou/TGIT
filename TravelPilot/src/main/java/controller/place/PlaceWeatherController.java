package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlaceManager;
import model.domain.Weather;

public class PlaceWeatherController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        PlaceManager placeManager = PlaceManager.getInstance();
        Weather weather = placeManager.getWeatherInfo(placeId);

        request.setAttribute("weather", weather);
        return "/myPage.jsp";
    }
}
