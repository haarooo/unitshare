package unitshare.controller;

public class UserController {
    // 싱글톤 생성
    private UserController() {
    }
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}
}
