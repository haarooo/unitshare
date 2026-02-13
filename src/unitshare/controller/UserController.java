package unitshare.controller;

import unitshare.model.dao.UserDao;

public class UserController {
    // 싱글톤 생성
    private UserController() {}
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}

    private UserDao ud = UserDao.getInstance(); // 0211 수정

    // 04. 회원가입 Controller
    public boolean signup( String id, String pwd, String name, String phone){
             // [*] 유효성검사 (중복검사, 데이터 길이검사)
        boolean result = ud.signup( id, pwd, name, phone);
        return result;
    } // [1] end // 0211 수정


    // 로그인 메소드
    private int loginSession = 0; // 세션:일정한 저장소 구역
    public boolean login(String id , String pwd){
        System.out.println("UserController.login");
        System.out.println("id = " + id + ", pwd = " + pwd);
        int result = ud.login(id,pwd);
        if(result>0){
            loginSession = result; return true;
        }
        return false;
    }

    // 로그아웃 메소드
    public boolean logout(){
        loginSession = 0; // 로그인 상태를 0(비로그인)으로 변경 // 세션(변수) 초기화
        return true;
    }

    // 현재 로그인된 유저 번호 반환해주는 메소드
    public int getLoginSession() {
        return loginSession;
    }

}
