package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.PlaceManager;
import model.domain.Weather;

public class PlaceWeatherController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            return viewWeather(request, response);
        }
        return null;
    }

    /**
     * 날씨 정보 보기
     */
    private String viewWeather(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        PlaceManager placeManager = PlaceManager.getInstance();
        Weather weather = placeManager.getWeatherInfo(placeId);

        request.setAttribute("weather", weather);
        return "/place/placeDetail.jsp"; // 상세보기 페이지로 이동
    }
}
