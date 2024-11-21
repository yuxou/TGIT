package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserManager manager = UserManager.getInstance();

        if (request.getMethod().equals("GET")) {
            // GET request: 회원정보 등록 form 요청
            log.debug("RegisterForm Request");

            // 커뮤니티 관련 부분 제거됨

            return "/user/registerForm.jsp";   // 회원가입 폼으로 이동
        }

        // POST request (회원정보가 parameter로 전송됨)
        User user = new User(
            request.getParameter("userId"),
            request.getParameter("password"),
            request.getParameter("name"),
            request.getParameter("email"),
            request.getParameter("phone")
        );

        log.debug("Create User : {}", user);

        try {
            manager.create(user);
            return "redirect:/user/list";    // 성공 시 사용자 리스트 화면으로 리다이렉트
        } catch (ExistingUserException e) {   // 예외 발생 시 회원가입 폼으로 포워딩
            request.setAttribute("registerFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("user", user);
            return "/user/registerForm.jsp";
        }
    }
}