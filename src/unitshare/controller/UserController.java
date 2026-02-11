package unitshare.controller;

import unitshare.model.dao.UserDao;

public class UserController {
    // 싱글톤 생성
    private UserController() {
    }
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}

    private UserDao us = UserDao.getInstance(); // 0211 수정

    // [1] 회원가입 Controller
    public boolean signup( String id, String pwd, String name, String phone){
             // [*] 유효성검사 (중복검사, 데이터 길이검사)
        boolean result = us.signup( id, pwd, name, phone);
        return result;
    } // [1] end // 0211 수정


    private UserDao ud = UserDao.getInstance();
    // 로그인 메소드
    public boolean login(String id , String pwd){
        System.out.println("UserController.login");
        System.out.println("id = " + id + ", pwd = " + pwd);
        boolean result = ud.login(id,pwd);
        return result;
    }

}
