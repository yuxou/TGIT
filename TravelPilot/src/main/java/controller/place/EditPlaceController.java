package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlaceManager;

public class EditPlaceController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int placeId = Integer.parseInt(request.getParameter("placeId"));
        String memo = request.getParameter("memo");

        PlaceManager placeManager = PlaceManager.getInstance();
        placeManager.updateMemo(placeId, memo);

        return "/myPage.jsp";
    }
}
