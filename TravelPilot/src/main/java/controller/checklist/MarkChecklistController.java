package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChecklistManager;

public class MarkChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        checklistManager.updateItemStatus(itemId, isCompleted);

        return "/myPage.jsp";
    }
}
