import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPanel extends JPanel {
    public static LoginPanel Lref;
    public static String username;
    public JLabel passStat;
    private JLabel wc, user, pass, connected;
    private JButton login, clear;
    private JTextField userf;
    private JPasswordField passf;
    private JCheckBox PassCheck;
    private String fullname;
    private JCheckBox admin, teacher;

    private LoginPanel() {
        this.initialize();
        this.getConnection();

    }

    public static LoginPanel getRef() {
        if (Lref == null) {
            Lref = new LoginPanel();
        }

        return Lref;
    }

    private void initialize() {
        Lref = this;
        setBackground(new Color(44, 62, 80));
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 3));

        wc = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#013243>Please Login To Continue</html></font></center>");
        wc.setBorder(new EmptyBorder(0, 20, 0, 0));
        wc.setBounds(0, 40, 10000, 50);
        wc.setOpaque(true);
        wc.setBackground(new Color(233, 212, 96));
        add(this.wc);

        admin = new JCheckBox("<html><font size=3 face='Lucida Sans Unicode' color=#BFBFBF>Admin</html></font>");
        admin.setBounds(165, 130, 80, 20);
        admin.setBackground(new Color(44, 62, 80));
        add(admin);

        teacher = new JCheckBox("<html><font size=3 face='Lucida Sans Unicode' color=#BFBFBF>Teacher</html></font>");
        teacher.setBounds(250, 130, 100, 20);
        teacher.setBackground(new Color(44, 62, 80));
        add(teacher);

        user = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#BFBFBF>Username:</html></font> ");
        user.setBounds(50, 150, 1000, 50);
        add(this.user);

        pass = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#BFBFBF>Password:</html></font> ");
        pass.setBounds(50, 210, 1000, 50);
        add(this.pass);

        userf = new JTextField();
        userf.setBackground(new Color(210, 215, 211));
        userf.setBounds(170, 160, 170, 30);
        add(this.userf);

        passf = new JPasswordField();
        passf.setBackground(new Color(210, 215, 211));
        passf.setBounds(170, 220, 170, 30);
        add(this.passf);

        PassCheck = new JCheckBox("<html><font size=3 face='Lucida Sans Unicode' color=#BFBFBF>Show Password</html></font>");
        PassCheck.setBounds(170, 260, 150, 40);
        PassCheck.setBackground(new Color(44, 62, 80));
        add(this.PassCheck);

        login = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Login</html></font>");
        login.setBounds(80, 340, 100, 40);
        login.setBackground(new Color(46, 204, 113));
        add(this.login);

        clear = new JButton("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Clear</html></font>");
        clear.setBounds(220, 340, 100, 40);
        clear.setBackground(new Color(239, 72, 54));
        add(this.clear);

        passStat = new JLabel("");
        passStat.setBounds(70, 450, 10000, 40);
        add(this.passStat);

        connected = new JLabel("<html><font size=3 face='Lucida Sans Unicode' color=#C0392B>DB Not Connected!</html></font>");
        connected.setBorder(new EmptyBorder(0, 20, 0, 0));
        connected.setBounds(3, 520, 100000, 30);
        connected.setOpaque(true);
        connected.setBackground(new Color(233, 212, 96));
        add(this.connected);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passStat.setText(null);
                if ((admin.isSelected() == true && teacher.isSelected()) == true) {
                    passStat.setText("<html><font size=4 face='Lucida Sans Unicode' color=#F62459>Invalid Checkbox Selected!</html></font>");
                } else if (teacher.isSelected()) {
                    teacherLogin();
                } else if (admin.isSelected()) {
                    adminLogin();
                } else {
                    passStat.setText("<html><font size=4 face='Lucida Sans Unicode' color=#F62459>Invalid Checkbox Selected!</html></font>");
                }
            }
        });

        PassCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PassCheck.isSelected()) {
                    passf.setEchoChar((char) 0);
                } else {
                    passf.setEchoChar('*');
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    private void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            connected.setText("<html><font size=3 face='Lucida Sans Unicode' color=#26A65B>DB Connected!</html></font>");
        } catch (Exception e) {
            System.out.println("LoginPanel: " + e.getMessage());
            connected.setText("<html><font size=3 face='Lucida Sans Unicode' color=red>DB Not Connected!</html></font>");
        }
    }

    public void clear() {
        userf.setText(null);
        passf.setText(null);
    }

    private void teacherLogin() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("SELECT username, password, name FROM users WHERE username = ? AND password = ?");
            state.setString(1, userf.getText());
            state.setString(2, String.valueOf(passf.getPassword()));
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                gui GuiRef = gui.getRef();
                fullname = rs.getString("name");
                username = rs.getString("username");
                System.out.println(username);
                System.out.println("login success");
                GuiRef.remove(LoginPanel.getRef());
                GuiRef.add(UserPanel.getRef(), "Center");
                UserPanel.getRef().welcome.setText("<html><font size= 5 face='Lucida Sans Unicode'>Welcome, " + fullname);
                GuiRef.paintAll(GuiRef.getGraphics());
            } else {
                passStat.setText("<html><font size=4 face='Lucida Sans Unicode' color=#F62459>Invalid Username or Password!</html></font>");
            }
        } catch (SQLException ex) {
            System.out.println("LoginPanel: " + ex.getMessage());
        }
    }

    private void adminLogin() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("SELECT username, password FROM admins WHERE username = ? AND password = ?");
            state.setString(1, userf.getText());
            state.setString(2, String.valueOf(passf.getPassword()));
            ResultSet rs = state.executeQuery();
            if (rs.next()) {
                gui GuiRef = gui.getRef();
                GuiRef.remove(LoginPanel.getRef());
                GuiRef.add(AdminPanel.getRef(), "Center");
                GuiRef.setBounds(0, 0, 900, 600);
                AdminPanel.getRef().getTable();
                GuiRef.paintAll(GuiRef.getGraphics());
            } else {
                passStat.setText("<html><font size=4 face='Lucida Sans Unicode' color=#F62459>Invalid Username or Password!</html></font>");
            }
        } catch (SQLException ex) {
            System.out.println("LoginPanel: " + ex.getMessage());
        }
    }

}
