package unitshare.view;

import unitshare.controller.UserController;

import javax.swing.text.StyledEditorKit;
import java.util.Scanner;
import unitshare.controller.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;


public class UserView {
    private UserView() {}
    private static final UserView instance = new UserView();
    public static UserView getInstance() {return instance;}

    private UserController uc = UserController.getInstance();
    private UserController us = UserController.getInstance();

    private Scanner scan = new Scanner(System.in); // 스캐너 멤버변수로 빼면 더 편리함.

    // [*] 메인페이지
    public void index() {
        for (; ; ) {
            try {
                System.out.println("======================== Unit share for solo ========================");
                System.out.println("1. 회원가입 2. 로그인 3. 아이디 찾기 4. 비밀번호 찾기");
                System.out.println("=====================================================================");
                System.out.println("선택>");
                int ch = scan.nextInt();

                if (ch == 1) {
                } else if (ch == 2) {
                    login();
                } else if (ch == 3) {
                } else if (ch == 4) {
                } else {
                    System.out.println("[경고] 없는 기능 번호입니다.");
                }
            } catch (InputMismatchException e) {
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
        boolean result = us.signup(id, pwd, name, phone);
        if (result == true) {System.out.println("[안내] 회원가입이 완료되었습니다.");}
        else {System.out.println("[안내] 회원가입에 실패하였습니다.");}
    } // [1] end // 0211 수정
} // class end

    // 로그인 페이지 view
    public void login() {
        System.out.println("아이디 : ");
        String id = scan.next();
        System.out.println("비밀번호 : ");
        String pwd = scan.next();
        boolean result = uc.login(id, pwd);
        if (result) {
            System.out.println("[안내] 로그인에 성공하였습니다.");
        } else {
            System.out.println("[경고] 로그인에 실패하였습니다.");
        }
    } // m END
} // class END