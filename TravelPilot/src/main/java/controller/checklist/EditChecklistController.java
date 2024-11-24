package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.ChecklistManager;

public class EditChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int checklistId = Integer.parseInt(request.getParameter("checklistId"));
    	String category = request.getParameter("category");

    	// Checklist 수정
    	ChecklistManager checklistManager = ChecklistManager.getInstance();
    	checklistManager.updateChecklist(checklistId, category);

    	return "/checklist/viewChecklist.jsp";
    }
}
