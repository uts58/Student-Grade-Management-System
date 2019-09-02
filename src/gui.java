import javax.swing.*;

public class gui extends JFrame {
    private static gui Gref;

    public gui() {
        super("Student Management System");
        Gref = this;
        add(LoginPanel.getRef(), "Center");
        setBounds(470, 100, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static gui getRef() {
        if (Gref == null) {
            Gref = new gui();
        }
        return Gref;
    }

}
