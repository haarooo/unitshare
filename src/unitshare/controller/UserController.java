package unitshare.controller;

import unitshare.model.dto.UserDto;
import unitshare.model.dao.UserDao;
import unitshare.view.ProductView;
import unitshare.view.UserView;

import java.util.Scanner;

public class UserController {
    UserDao userDao = new UserDao();
    // 싱글톤 생성
    private UserController() {}
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}


    private UserDao ud = UserDao.getInstance();


    // 01. 아이디 중복사용 여부 controller
    public boolean checkId(String id){
        return ud.getInstance().checkId(id);
    }

    // 01-2. 전화번호 중복사용 여부 controller
    public boolean checkphone(String phone) {
        return ud.getInstance().checkPhone(phone);
    }

    // 02.아이디찾기
    public String findId(String name, String phone){
        UserDto dto = ud.findId(name, phone);
        if(dto != null){
            return dto.getId();
        }
        return null;
    }
    // 02 end // 0213

    // 03.비밀번호찾기
    public String findPwd(String id, String phone){
        UserDto dto = ud.findPwd(id, phone);
        if(dto != null){
            return dto.getPwd();
        }
        return null;
    }
    // 03 end // 0213

    // 04. 회원가입 Controller
    public boolean signup( String id, String pwd, String name, String phone){
             // [*] 유효성검사 (중복검사, 데이터 길이검사)
        boolean result = ud.signup( id, pwd, name, phone);
        return result;
    } // 04 end


    // 로그인 메소드
    private int loginSession = 0; // 세션:일정한 저장소 구역
    public boolean login(String id , String pwd){
        int result = ud.login(id,pwd);
        if(result>0){
            loginSession = result;
            return true;
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

    public UserDao getUd() {
        return ud;
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }


    // 비밀번호 변경 페이지
    public boolean newPwd(String currentPwd, String newPwd){
        if (loginSession==0) {
            System.out.println("[경고] 로그인이 필요한 서비스입니다.");
            return false;
        }
        // 현재 비밀번호, 새로운 비밀번호 같은지 체크
        if(currentPwd.equals(newPwd)){
            System.out.println("[경고] 현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
            return false;
        }
        // DAO 호출, 전달받은 데이터와 현재 로그인 세션번호를 넘김
        boolean result = userDao.newPwd(this.loginSession,currentPwd,newPwd);
        return result;
    } // m END

}

