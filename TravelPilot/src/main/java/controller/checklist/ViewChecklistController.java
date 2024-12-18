package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Checklist;
import model.service.ChecklistManager;

public class ViewChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int checklistId = Integer.parseInt(request.getParameter("checklistId"));

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        Checklist checklist = checklistManager.viewChecklist(checklistId);

        request.setAttribute("checklist", checklist);
        return "/myPage.jsp";
    }
}
