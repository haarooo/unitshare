package unitshare.view;

import javax.swing.text.StyledEditorKit;

public class UserView {
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance(){return instance;}

}
