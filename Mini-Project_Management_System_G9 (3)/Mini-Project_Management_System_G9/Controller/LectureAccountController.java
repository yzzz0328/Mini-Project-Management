package Controller;

import Model.Lecturer;
import View.LectureAccountView;

// Teoh Ye Zhian
public class LectureAccountController {
    private Lecturer model;
    private LectureAccountView view;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureAccountController(Lecturer model) {
        this.model = model;
        this.view = new LectureAccountView();
        initView();
    }

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureAccountController(Lecturer model, LectureAccountView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    // Teoh Ye Zhian
    // an initial view class when user enters the view
    public void initView() {
        this.view.setID(this.model.getName());
        this.view.setFullName(this.model.getUserFullName());
    }

    // Teoh Ye Zhian
    // an initial controller with LectureMenuController parameter to handle the action 
    // whenever certain button is clicked.
    // In this method, our programs tries to direct lecturer back to previous page
    // by clicking the back button.
    public void initController(LectureMenuController c) {
        this.view.getBackButton().addActionListener(e -> backActionListener(c));
    }

    // Teoh Ye Zhian
    // an action listener method that calls the controller of the previous page
    // and display lecturer the previous page.
    private void backActionListener(LectureMenuController c) {
        c = new LectureMenuController(model);
        c.initController();
        this.view.dispose();
    }
}
