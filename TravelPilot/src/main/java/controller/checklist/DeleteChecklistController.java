package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ChecklistManager;

public class DeleteChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int checklistId = Integer.parseInt(request.getParameter("checklistId"));

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        checklistManager.deleteChecklist(checklistId);

        return "/myPage.jsp";
    }
}
