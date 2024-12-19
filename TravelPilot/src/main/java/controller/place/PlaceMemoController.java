package controller.place;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PlaceManager;

public class PlaceMemoController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        int placeId = Integer.parseInt(request.getParameter("placeId"));

        PlaceManager placeManager = PlaceManager.getInstance();

        switch (action) {
            case "add":
                String memo = request.getParameter("memo");
                placeManager.addPlaceMemo(placeId, memo);
                break;
            case "edit":
                String newMemo = request.getParameter("memo");
                placeManager.editPlaceMemo(placeId, newMemo);
                break;
            case "delete":
                placeManager.deletePlaceMemo(placeId);
                break;
        }

        return "/myPage.jsp"; // 모든 액션 후 마이페이지로 리다이렉트
    }
}
