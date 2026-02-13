package unitshare.view;
import unitshare.controller.UserController;
import java.util.Scanner;
import java.util.InputMismatchException;



public class UserView {
    private UserView() {}
    private static final UserView instance = new UserView();
    public static UserView getInstance() {return instance;}

    private UserController uc = UserController.getInstance();
    Scanner scan = new Scanner(System.in); // 스캐너 멤버변수로 빼면 더 편리함.

    // [*] 메인페이지
    public void index() {
        for (; ; ) {
            try {
                System.out.println("======================== Unit share for solo ========================");
                if(uc.getLoginSession()==0){System.out.println("1. 회원가입 2. 로그인 3. 아이디 찾기 4. 비밀번호 찾기");}
                else{
                    System.out.println("1.로그아웃 2. 물품 등록 3.전체 공동구매 목록 조회 및 신청 4. 내 구매 신청 목록 조회 5.취소");
                }
                System.out.println("=====================================================================");
                System.out.println("선택>");
                int ch = scan.nextInt();

                if(uc.getLoginSession()==0) { // [로그인 전 메뉴 처리]
                    if (ch == 1) {signup();}
                    else if (ch == 2) {login();}
                    else if (ch == 3) {}
                    else if (ch == 4) {}
                }else { // [로그인 후 메뉴 처리]
                    if( ch == 1 ){logout();}
                    else if( ch == 2){}
                    else if ( ch == 3) {}
                    else if ( ch == 4) {ProductView.getInstance().mylist();}
                    else if ( ch == 5) {}
                }
            }catch (InputMismatchException e) {
                System.out.println("[경고] 잘못된 입력 방식입니다.");
                scan = new Scanner(System.in); // 입력객체 초기화
            } catch (Exception e) {
                System.out.println("[시스템 오류] 관리자에게 문의하십시오.");
            }
        }
    }

    // [1] 회원가입 View
    public void signup() {
        System.out.print("아이디 : ");
        String id = scan.next();
        System.out.print("비밀번호 : ");
        String pwd = scan.next();
        System.out.print("성함 : ");
        String name = scan.next();
        System.out.print("연락처 : ");
        String phone = scan.next();
        boolean result = uc.signup(id, pwd, name, phone);
        if (result == true) {System.out.println("[안내] 회원가입이 완료되었습니다.");}
        else {System.out.println("[안내] 회원가입에 실패하였습니다.");}
    } // [1] end // 0211 수정

    // 로그인 페이지 view
    public void login() {
        System.out.println("아이디 : ");
        String id = scan.next();
        System.out.println("비밀번호 : ");
        String pwd = scan.next();
        boolean result = uc.login(id,pwd);
        if (result==true) {
            System.out.println("[안내] 로그인에 성공하였습니다.");
        } else {
            System.out.println("[경고] 로그인에 실패하였습니다.");
        }
    } // m END

    // 로그아웃 페이지 view
    public void logout() {
        boolean result = uc.logout();
        if(result){
        System.out.println("[안내] 로그아웃되었습니다.");}
        else{
            System.out.println("[오류] 현재 로그인 상태가 아닙니다.");
        }
    }
    } // class END

