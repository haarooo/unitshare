package unitshare.view;
import unitshare.controller.ProductController;
import unitshare.controller.UserController;
import unitshare.model.dto.UserDto;

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
                System.out.println("\n┌────────────────────────────────────────────────────┐");
                System.out.println("│             📢 UNIT SHARE FOR SOLO                │");
                System.out.println("├────────────────────────────────────────────────────┤");
                if (uc.getLoginSession() == 0) {
                    System.out.println("│  👤 1. 회원가입          🔑 2. 로그인             │");
                    System.out.println("│  🔍 3. 아이디 찾기       🔒 4. 비밀번호 찾기      │");
                }
                System.out.println("└────────────────────────────────────────────────────┘");
                System.out.print("✨ 선택 > ");
                int ch = scan.nextInt();

                if(uc.getLoginSession()==0){ // [로그인 전 메뉴 처리]
                if (ch == 1) { signup(); }
                else if (ch == 2) {login();}
                else if (ch == 3) { findIdView();} // 0213 수정
                else if (ch == 4) { findPwdView();}
            } }catch (InputMismatchException e) {
                System.out.println("[경고] 잘못된 입력 방식입니다.");
                scan = new Scanner(System.in); // 입력객체 초기화
            } catch (Exception e) {
                System.out.println("[시스템 오류] 관리자에게 문의하십시오.");
            }
        }
    }

    // 02. 아이디찾기 View
        public void findIdView() {
            System.out.print("이름 입력: ");
            String name = scan.next();

            System.out.print("전화번호 입력: ");
            String phone = scan.next();

            String result = uc.findId(name, phone);

            if(result != null){System.out.println("찾은 아이디 : " + result);}
            else {System.out.println("일치하는 회원이 없습니다.");}
        }
    // 02 end // 0213 수정

    // 03. 비밀번호찾기 View
        public void findPwdView() {
            System.out.print("아이디 입력: ");
            String id = scan.next();

            System.out.print("전화번호 입력: ");
            String phone = scan.next();

            String result = uc.findPwd(id, phone);

            if(result != null){System.out.println("찾은 비밀번호 : " + result);}
            else {System.out.print("일치하는 회원이 없습니다.");}
        }
    // 03 end // 0213 수정

    // 04. 회원가입 View
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
    } //04 end

    // 로그인 페이지 view
    public void login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("아이디 : ");
        String id = scan.next();
        System.out.print("비밀번호 : ");
        String pwd = scan.next();
        boolean result = uc.login(id,pwd);
        if (result==true) {
            System.out.println("[안내] 로그인에 성공하였습니다.");
            ProductView.getInstance().index2();
        } else {
            System.out.println("[경고] 로그인에 실패하였습니다.");
        }
    } // m END

    // 로그아웃 페이지 view
    public void logout() {
        boolean result = uc.logout();
        if(result){
        System.out.println("[안내] 로그아웃되었습니다.");}
        UserView.getInstance().index();
    }
    } // class END

