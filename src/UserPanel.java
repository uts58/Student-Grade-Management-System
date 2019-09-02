import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    public static UserPanel Uref;
    public JLabel welcome;
    private JLabel intention;
    private JButton logout, manage, print;


    private UserPanel() {
        this.initialize();
    }

    public static UserPanel getRef() {
        if (Uref == null) {
            Uref = new UserPanel();
        }

        return Uref;
    }

    private void initialize() {
        Uref = this;
        setLayout(null);
        setBackground(new Color(44, 62, 80));
        setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 3));

        welcome = new JLabel();
        welcome.setBounds(0, 20, 10000, 40);
        welcome.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcome.setBackground(new Color(233, 212, 96));
        welcome.setOpaque(true);
        add(welcome);
        repaint();

        intention = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Select What You Want To Do!</html></font>");
        intention.setBounds(75, 120, 1000, 40);
        add(intention);

        manage = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Manage</html></font>");
        manage.setBounds(130, 200, 140, 40);
        manage.setBackground(new Color(46, 204, 113));
        add(this.manage);

        print = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Mark Sheet</html></font>");
        print.setBounds(130, 300, 140, 40);
        print.setBackground(new Color(46, 204, 113));
        add(this.print);

        logout = new JButton("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Logout</html></font>");
        logout.setBounds(130, 400, 140, 40);
        logout.setBackground(new Color(239, 72, 54));
        add(this.logout);

        manage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("manage Success");
                gui GuiRef = gui.getRef();
                GuiRef.remove(UserPanel.getRef());
                GuiRef.add(ManagePanel.getRef(), "Center");
                ManagePanel.getRef().getTable();
                GuiRef.setBounds(0, 0, 1000, 660);
                GuiRef.paintAll(GuiRef.getGraphics());
            }
        });

        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui GuiRef = gui.getRef();
                GuiRef.remove(UserPanel.getRef());
                PrintPanel.getRef().getRoll();
                GuiRef.add(PrintPanel.getRef(), "Center");
                GuiRef.setBounds(0, 0, 1000, 660);
                GuiRef.paintAll(GuiRef.getGraphics());
            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("logout success");
                gui GuiRef = gui.getRef();
                GuiRef.removeAll();
                GuiRef.dispose();
                new Main();
                LoginPanel.getRef().clear();

            }
        });
    }
}
