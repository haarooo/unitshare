package unitshare.controller;

import unitshare.model.dto.UserDto;
import unitshare.model.dao.UserDao;

public class UserController {
    UserDao userDao = new UserDao();

    // 싱글톤 생성
    private UserController() {
    }

    private static final UserController instance = new UserController();

    public static UserController getInstance() {
        return instance;
    }


    private UserDao ud = UserDao.getInstance();


    // 01. 아이디 중복사용 여부 controller
    public boolean checkId(String id) {
        return ud.getInstance().checkId(id);
    }

    // 01-2. 전화번호 중복사용 여부 controller
    public boolean checkphone(String phone) {
        return ud.getInstance().checkPhone(phone);
    }

    // 02.아이디찾기
    public String findId(String name, String phone) {
        UserDto dto = ud.findId(name, phone);
        if (dto != null) {
            return dto.getId();
        }
        return null;
    }
    // 02 end // 0213

    // 03.비밀번호찾기
    public String findPwd(String id, String phone) {
        UserDto dto = ud.findPwd(id, phone);
        if (dto != null) {
            return dto.getPwd();
        }
        return null;
    }
    // 03 end // 0213

    // 04. 회원가입 Controller
    public boolean signup(String id, String pwd, String name, String phone) {
        // [*] 유효성검사 (중복검사, 데이터 길이검사)
        boolean result = ud.signup(id, pwd, name, phone);
        return result;
    } // 04 end


    // 로그인 메소드
    private int loginSession = 0; // 세션:일정한 저장소 구역
    private timerThread timerThread;

    public boolean login(String id, String pwd) {
        int result = ud.login(id, pwd);
        if (result > 0) {
            loginSession = result;

            //* 휴면계정 // 0223 수정
            System.out.println("\n[안내]"+id+"님 환영합니다.");

           // 타이머 시작
            if( timerThread != null) timerThread.state= false;// 기존 타이머가 있다면 종료
            timerThread  = new timerThread(id, loginSession, this);// 현재 유저 정보 전달
            timerThread.state = true; //(1)실행 상태로 변경
            timerThread.start(); // 새 스레드로 30초 시작

            return true;
        }
        return false;
        }

    // 로그아웃 메소드
    public boolean logout() {
        loginSession = 0; // 로그인 상태를 0(비로그인)으로 변경 // 세션(변수) 초기화
        if ( timerThread != null) timerThread.state = false; // 0223 수정
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
    public boolean newPwd(String currentPwd, String newPwd) {
        if (loginSession == 0) {
            System.out.println("[경고] 로그인이 필요한 서비스입니다.");
            return false;
        }
        // 현재 비밀번호, 새로운 비밀번호 같은지 체크
        if (currentPwd.equals(newPwd)) {
            System.out.println("[경고] 현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
            return false;
        }
        // DAO 호출, 전달받은 데이터와 현재 로그인 세션번호를 넘김
        boolean result = userDao.newPwd(this.loginSession, currentPwd, newPwd);
        return result;
    } // m END

}
class timerThread extends Thread{ // 0223 수정
    boolean state = false;
    String id;
    int uno;
    UserController uc;
    // 생성자
    public timerThread(String id, int uno, UserController userController){
        this.id = id;
        this.uno = uno;
        this.uc = userController;
    }
    @Override
    public void run(){
        int second = 0; // 타이머 초 초기화
        while(state){ // state가 true인 동안 반복
            try{ Thread.sleep(1000); // 1초 대기
                second++;
                // 경고메세지
                if (second == 3) {
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("[안내] 아무런 활동이 없으시다면, 7초 뒤에 휴면계정으로 전환 및 자동 로그아웃됩니다.");
                    System.out.println("--------------------------------------------------\n");
                }
                // 타이머가 10초 지났을 때
                else if( second > 10){
                    System.out.println("[안내] 장기간 활동이 없으므로 메인페이지로 돌아갑니다.");
                    uc.getUd().loginStatement(uno); // DB를 '휴면계정'으로 상태 변경
                    uc.logout(); // 자동 로그아웃
                    unitshare.view.UserView.getInstance().index(); // 메인으로 강제 이동
                    this.state = false; // 타이머 중지
                    break;
                }
            }catch (Exception e){System.out.println("[경고] 휴면계정으로 전환 중에 문제가 발생했습니다." + e);}
        }// while end
    } // method(run) end
} // Thread class end

