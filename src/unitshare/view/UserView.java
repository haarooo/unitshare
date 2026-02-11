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

    private UserController us = UserController.getInstance();

    private Scanner scan = new Scanner(System.in); // 입력객체

    // [*] 메인 페이지
    public void index() {
        for (; ; ) {
            System.out.print("1.회원가입 2.로그인  선택>");
            int ch = scan.nextInt();
            if (ch == 1) {signup();}
            else if (ch == 2) {}
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
        if (result == true) {System.out.println("회원가입 성공");}
        else {System.out.println("회원가입 실패");}
    } // [1] end
} // class end