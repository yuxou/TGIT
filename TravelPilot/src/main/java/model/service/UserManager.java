package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.UserDAO;
import model.domain.User;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 */
public class UserManager {
    private static UserManager userMan = new UserManager();
    private UserDAO userDAO;
    private UserAnalysis userAnalysis;

    private UserManager() {
        try {
            userDAO = new UserDAO();
            userAnalysis = new UserAnalysis(userDAO); // 커뮤니티 관련 코드 제거 후 userAnalysis로 변경
        } catch (Exception e) {
            e.printStackTrace();
        }            
    }
    
    public static UserManager getInstance() {
        return userMan;
    }
    
    /**
     * 사용자 생성
     */
    public int create(User user) throws SQLException, ExistingUserException {
        if (userDAO.existingUser(user.getUserId())) {
            throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
        }
        return userDAO.create(user);
    }

    /**
     * 사용자 정보 조회
     */
    public User findUser(String userId) throws SQLException, UserNotFoundException {
        User user = userDAO.findUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }        
        return user;
    }

    /**
     * 사용자 목록 조회
     */
    public List<User> findUserList() throws SQLException {
        return userDAO.findUserList();
    }
    
    /**
     * 페이징 처리된 사용자 목록 조회
     */
    public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
        return userDAO.findUserList();
    }

    /**
     * 로그인 기능
     */
    public boolean login(String userId, String password) throws SQLException, UserNotFoundException, PasswordMismatchException {
        User user = findUser(userId);
        if (!user.matchPassword(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    /**
     * 친구 추천
     */
    public List<User> makeFriends(String userId) throws Exception {
        return userAnalysis.recommendFriends(userId);
    }

    // 사용자 정보 수정
    public int update(User user) throws SQLException, UserNotFoundException {
        User existingUser = userDAO.findUser(user.getUserId());
        if (existingUser == null) {
            throw new UserNotFoundException(user.getUserId() + "는 존재하지 않는 아이디입니다.");
        }
        return userDAO.update(user);
    }

    // 사용자 삭제
    public int remove(String userId) throws SQLException, UserNotFoundException {
        User existingUser = userDAO.findUser(userId);
        if (existingUser == null) {
            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }
        return userDAO.remove(userId);
    }

    public UserDAO getUserDAO() {
        return this.userDAO;
    }
}