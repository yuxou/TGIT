package controller.checklist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Checklist;
import model.service.ChecklistManager;

public class AddChecklistController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("category");

        ChecklistManager checklistManager = ChecklistManager.getInstance();
        Checklist checklist = new Checklist();
        checklist.setCategory(category);

        int checklistId = checklistManager.createChecklist(checklist);

        return "/myPage.jsp"; // 리턴 경로 수정
    }
}
