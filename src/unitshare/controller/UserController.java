package unitshare.controller;

import unitshare.model.dao.UserDao;

public class UserController {
    // 싱글톤 생성
    private UserController() {
    }
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}

    private UserDao us = UserDao.getInstance();

    // [1] 회원가입 Controller
    public boolean signup( String id, String pwd, String name, String phone){
        System.out.println("UserController.signup"); // 중간검사
        System.out.println("id = " + id + ", pwd = " + pwd + ", name = " + name + ", phone = " + phone);

        // [*] 유효성검사 (중복검사, 데이터 길이검사)
        boolean result = us.signup( id, pwd, name, phone);
        return result;
    } // [1] end

}
