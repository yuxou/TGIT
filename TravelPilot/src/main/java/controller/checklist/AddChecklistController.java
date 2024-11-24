package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import controller.Controller;
import model.domain.Checklist;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

public class AddChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String category = request.getParameter("category");

    	// Checklist 생성
    	ChecklistManager checklistManager = ChecklistManager.getInstance();
    	Checklist checklist = checklistManager.createChecklist(category);

    	// 초기 ChecklistItems 추가 (선택 사항)
    	String[] itemNames = request.getParameterValues("itemNames");
    	if (itemNames != null) {
    		for (String name : itemNames) {
    			checklistManager.addItemToChecklist(checklist.getChecklistId(), name, false);
    		}
    	}

    	request.setAttribute("checklist", checklist);
    	return "/checklist/viewChecklist.jsp";
    }
}
