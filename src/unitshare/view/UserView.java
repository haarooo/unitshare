package unitshare.view;

import unitshare.controller.UserController;

import javax.swing.text.StyledEditorKit;
import java.util.Scanner;

public class UserView {
    private UserView() {
    }

    private static final UserView instance = new UserView();

    public static UserView getInstance() {
        return instance;
    }

    private UserController us = UserController.getInstance(); // 0211 수정

    private Scanner scan = new Scanner(System.in); // 0211 수정

    // [*] 메인 페이지
    public void index() {
        for (; ; ) {
            System.out.println("================ Unit share for solo ================");
            System.out.println("1. 회원가입  2. 로그인   3. 아이디 찾기  4. 비밀번호 찾기 ");
            System.out.println("=====================================================");
            System.out.println(" 선택 > ");
            int ch = scan.nextInt();
            if (ch == 1) {signup();}
            else if (ch == 2) { }
            else if (ch == 3) { }
            else if (ch == 4) {}

        }
    }// [*] end

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
        boolean result = us.signup(id, pwd, name, phone);
        if (result == true) {System.out.println("[안내] 회원가입이 완료되었습니다.");}
        else {System.out.println("[안내] 회원가입에 실패하였습니다.");}


    } // 04 end // 0211 수정
} // class end