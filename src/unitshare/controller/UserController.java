package unitshare.controller;

import unitshare.model.dao.UserDao;

public class UserController {
    // 싱글톤 생성
    private UserController() {
    }
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}

    private UserDao ud = UserDao.getInstance();
    // 로그인 메소드
    public boolean login(String id , String pwd){
        System.out.println("UserController.login");
        System.out.println("id = " + id + ", pwd = " + pwd);
        boolean result = ud.login(id,pwd);
        return result;
    }

}
