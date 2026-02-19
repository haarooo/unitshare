package unitshare.controller;
import unitshare.model.dto.UserDto;
import unitshare.model.dao.UserDao;

public class UserController {
    // 싱글톤 생성
    private UserController() {}
    private  static final UserController instance = new UserController();
    public static UserController getInstance() {return instance;}


    private UserDao ud = UserDao.getInstance();


    // 01. 아이디 중복사용 여부 controller
    public boolean checkId(String id){
        return ud.getInstance().checkId(id);
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
}
