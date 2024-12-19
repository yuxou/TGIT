package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Checklist;
import model.domain.ChecklistItem;
import model.service.ChecklistManager;

public class AddChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("category");
        String[] itemNames = request.getParameterValues("itemNames"); // 항목 이름 배열

        ChecklistManager checklistManager = ChecklistManager.getInstance();

        // Checklist 생성
        Checklist checklist = new Checklist();
        checklist.setCategory(category);
        int checklistId = checklistManager.createChecklist(checklist);

        // 항목 추가
        if (itemNames != null && itemNames.length > 0) {
            for (String itemName : itemNames) {
                ChecklistItem item = new ChecklistItem();
                item.setName(itemName);
                item.setCompleted(false);
                checklistManager.addChecklistItem(checklistId, item); // DAO 호출
            }
        }

        request.setAttribute("checklistId", checklistId);
        return "/checklist/viewChecklist.jsp?checklistId=" + checklistId;
    }
}
