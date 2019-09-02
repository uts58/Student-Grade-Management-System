import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPanel extends JPanel {

    public static AdminPanel Aref;
    private JLabel addLabel, userLabel, nameLabel, passLabel, id, or, userEditLabel, userEditLabelName;
    private JTextField user, name, pass;
    private JComboBox users;
    private JPanel input, editPanel;
    private JButton logout, add, edit, delete, clear;
    private JCheckBox nameCheck, userNameCheck, passCheck;
    private String oldUserName;
    private JFrame tableFrame;
    private JTable table;

    private AdminPanel() {
        initialize();
        getID();
    }

    public static AdminPanel getRef() {
        if (Aref == null) {
            Aref = new AdminPanel();
        }
        return Aref;
    }

    private void initialize() {
        Aref = this;
        setBackground(new Color(44, 62, 80));
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 3));

        input = new JPanel();
        input.setBounds(4, 4, 440, 550);
        input.setBorder(new TitledBorder("<html><font size=3 face='Lucida Sans Unicode' color=#ECECEC>ADD:</html></font>"));
        input.setBackground(new Color(44, 62, 80));
        input.setLayout(null);
        add(input);

        editPanel = new JPanel();
        editPanel.setBounds(445, 4, 430, 550);
        editPanel.setBorder(new TitledBorder("<html><font size=3 face='Lucida Sans Unicode' color=#ECECEC>EDIT:</html></font>"));
        editPanel.setBackground(new Color(44, 62, 80));
        editPanel.setLayout(null);
        add(editPanel);

        addLabel = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Add Teachers!</html></font> ");
        addLabel.setBounds(40, 100, 1000, 50);
        input.add(addLabel);

        userLabel = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Teacher's Username:</html></font> ");
        userLabel.setBounds(40, 170, 1000, 50);
        input.add(this.userLabel);

        nameLabel = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Teacher's Fullname:</html></font> ");
        nameLabel.setBounds(40, 230, 1000, 50);
        input.add(this.nameLabel);

        passLabel = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Teacher's Password:</html></font> ");
        passLabel.setBounds(40, 290, 1000, 50);
        input.add(this.passLabel);

        user = new JTextField();
        user.setBackground(new Color(210, 215, 211));
        user.setBounds(210, 180, 170, 30);
        input.add(this.user);

        name = new JTextField();
        name.setBackground(new Color(210, 215, 211));
        name.setBounds(210, 240, 170, 30);
        input.add(this.name);

        pass = new JTextField();
        pass.setBackground(new Color(210, 215, 211));
        pass.setBounds(210, 300, 170, 30);
        input.add(this.pass);

        add = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Add</html></font>");
        add.setBounds(100, 400, 100, 40);
        add.setBackground(new Color(46, 204, 113));
        input.add(add);

        clear = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=black >Clear</html></font>");
        clear.setBounds(250, 400, 100, 40);
        clear.setBackground(new Color(210, 215, 211));
        input.add(clear);

        logout = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Logout</html></font>");
        logout.setBounds(175, 460, 100, 40);
        logout.setBackground(new Color(239, 72, 54));
        input.add(logout);

        users = new JComboBox();
        users.setBounds(210, 30, 200, 20);
        editPanel.add(users);

        id = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Select Teacher ID: </html></font>");
        id.setBounds(50, 30, 200, 20);
        editPanel.add(id);

        or = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >OR</html></font>");
        or.setBounds(215, 400, 100, 40);
        editPanel.add(or);

        userEditLabel = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Teacher's Username:</html></font> ");
        userEditLabel.setBounds(40, 120, 200, 50);
        editPanel.add(this.userEditLabel);

        userEditLabelName = new JLabel();
        userEditLabelName.setBounds(210, 120, 200, 50);
        editPanel.add(this.userEditLabelName);

        nameCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Fullname </html></font>");
        nameCheck.setBounds(15, 210, 100, 20);
        nameCheck.setBackground(new Color(44, 62, 80));
        editPanel.add(nameCheck);

        userNameCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Username </html></font>");
        userNameCheck.setBounds(280, 210, 100, 20);
        userNameCheck.setBackground(new Color(44, 62, 80));
        editPanel.add(userNameCheck);

        passCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Password </html></font>");
        passCheck.setBounds(150, 210, 100, 20);
        passCheck.setBackground(new Color(44, 62, 80));
        editPanel.add(passCheck);

        edit = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Edit</html></font>");
        edit.setBounds(175, 340, 100, 40);
        edit.setBackground(new Color(46, 204, 113));
        editPanel.add(edit);

        delete = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Delete User!</html></font>");
        delete.setBounds(150, 460, 150, 40);
        delete.setBackground(new Color(239, 72, 54));
        editPanel.add(delete);

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
                tableFrame.dispose();
                getTable();
            }
        });

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.getRef().dispose();
                LoginPanel.getRef().clear();
                clear();
                users.removeAllItems();
                tableFrame.dispose();
                new Main();
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        users.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacherName();
            }
        });
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

    }

    private void clear() {
        user.setText(null);
        name.setText(null);
        pass.setText(null);
    }

    private void getID() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String query = "SELECT id from users";
            PreparedStatement state = conn.prepareStatement(query);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                users.addItem(rs.getString("id"));
            }
        } catch (SQLException ex) {
            System.out.println("AdminPanel: " + ex.getMessage());
        }
    }

    private void addUser() {
        if (user.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill Up The UserName", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill Up The FullName", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (pass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill Up The PassWord", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (namecheck(user.getText()) == false) {
            JOptionPane.showMessageDialog(this, "UserName Can Only Have Alphabets", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if ((user.getText().contains(" ")) == true) {
            JOptionPane.showMessageDialog(this, "UserName Can Only Have One Word, Ex: iffat", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (namecheck(name.getText()) == false) {
            JOptionPane.showMessageDialog(this, "FullName Can Only Contain Letters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                String query = "INSERT INTO `users`(`username`, `name`, `password`) VALUES (?,?,?)";
                PreparedStatement state = conn.prepareStatement(query);
                state.setString(1, user.getText());
                state.setString(2, name.getText());
                state.setString(3, pass.getText());
                state.executeUpdate();
                String halfQuery = "CREATE TABLE $username (`name` varchar(80) NOT NULL, `roll` int(10) NOT NULL, `bangla` int(10) NOT NULL, `english` int(10) NOT NULL, `math` int(10) NOT NULL, `gk` int(10) NOT NULL,`religion` int(10) NOT NULL, `science` int(10) NOT NULL, PRIMARY KEY (`roll`)) ENGINE=MyISAM DEFAULT CHARSET=latin1;";
                String query1 = halfQuery.replace("$username", user.getText());
                PreparedStatement state1 = conn.prepareStatement(query1);
                state1.executeUpdate();
                JOptionPane.showMessageDialog(this, "Teacher Added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1050) {
                    JOptionPane.showMessageDialog(this, "Duplicate Username! Change Username or Edit The Old Ones!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        users.removeAllItems();
        getID();
    }

    private boolean namecheck(String y) {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(y);
        boolean b;
        b = matcher.find();
        return b;
    }

    private void deleteUser() {
        try {
            String userName = "";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String id = "" + users.getItemAt(users.getSelectedIndex());
            PreparedStatement state = conn.prepareStatement("SELECT `username` FROM `users` WHERE `id` = ?");
            state.setString(1, id);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                userName = rs.getString("username");
            }
            PreparedStatement state1 = conn.prepareStatement("DELETE FROM `users` WHERE `id` = ?");
            state1.setString(1, id);
            state1.executeUpdate();
            String s1 = "DROP TABLE $tablename";
            String s2 = s1.replace("$tablename", userName);
            PreparedStatement state2 = conn.prepareStatement(s2);
            state2.executeUpdate();
            JOptionPane.showMessageDialog(this, "Successfully Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            tableFrame.dispose();
            getTable();
        } catch (SQLException ex) {
            System.out.println("AdminPanel: " + ex.getMessage());
        }
        users.removeAllItems();
        getID();
    }

    private void edit() {
        String id = "" + users.getItemAt(users.getSelectedIndex());
        if ((nameCheck.isSelected() == false) && (userNameCheck.isSelected() == false) && (passCheck.isSelected() == false)) {
            JOptionPane.showMessageDialog(this, "Please Select What You Want To Edit", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (nameCheck.isSelected()) {
            String newName = JOptionPane.showInputDialog(this, "Enter New FullName");
            if (newName.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Name Can't Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (namecheck(newName) == false) {
                JOptionPane.showMessageDialog(this, "Name Should Be Alphabets Only", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editName(newName, id);
            }
        }
        if (userNameCheck.isSelected()) {
            String newUserName = JOptionPane.showInputDialog(this, "Enter New UserName");
            if (newUserName.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, " Username Can't Be Empty or Can't Contain Spaces", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (namecheck(newUserName) == false) {
                JOptionPane.showMessageDialog(this, "Username Should Be Alphabets Only", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (newUserName.contains(" ") == true) {
                JOptionPane.showMessageDialog(this, "UserName Can Only Have One Word, Ex: iffat", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editUserName(newUserName, id);
            }
        }
        if (passCheck.isSelected()) {
            String newPass = JOptionPane.showInputDialog(this, "Enter New Password");
            if (newPass.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Don't Forget To Fill Up Password!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editPass(newPass, id);
            }
        }
        tableFrame.dispose();
        getTable();
    }

    private void editName(String name, String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("UPDATE users SET name = ? WHERE id= ?");
            state.setString(1, name);
            state.setString(2, id);
            state.executeUpdate();
            JOptionPane.showMessageDialog(this, "Teacher Name Edited!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("AdminPanel: " + ex.getMessage());
        }
    }

    private void editPass(String pass, String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("UPDATE users SET password = ? WHERE id= ?");
            state.setString(1, pass);
            state.setString(2, id);
            state.executeUpdate();
            JOptionPane.showMessageDialog(this, "Teacher Password Edited!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("AdminPanel: " + ex.getMessage());
        }
    }

    private void editUserName(String username, String id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("UPDATE users SET username = ? WHERE id= ?");
            state.setString(1, username);
            state.setString(2, id);
            state.executeUpdate();
            String halfQuery = "RENAME TABLE $oldtable to $newtable";
            String semiHalfQuery = halfQuery.replace("$oldtable", oldUserName);
            String query = semiHalfQuery.replace("$newtable", username);
            PreparedStatement state1 = conn.prepareStatement(query);
            state1.executeUpdate();
            JOptionPane.showMessageDialog(this, "Teacher Username Edited!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            teacherName();
        } catch (SQLException ex) {
            System.out.println("AdminPanel: " + ex.getMessage());
        }
    }

    private void teacherName() {
        String id = "" + users.getItemAt(users.getSelectedIndex());
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            PreparedStatement state = conn.prepareStatement("SELECT username from users where id=?");
            state.setString(1, id);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                oldUserName = rs.getString("username");
                userEditLabelName.setText("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>" + oldUserName + "</html></font> ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void getTable() {
        tableFrame = new JFrame("User Data Table");
        tableFrame.setBounds(905, 0, 400, 350);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setResizable(false);
        tableFrame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 2));
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "UserName", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String query = "SELECT * FROM users ORDER BY id";
            PreparedStatement state = conn.prepareStatement(query);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                String a = rs.getString("id");
                String b = rs.getString("username");
                String c = rs.getString("name");
                model.addRow(new String[]{a, b, c});
            }
            table = new JTable(model);
            table.setAutoCreateRowSorter(true);
            JScrollPane sp = new JScrollPane(table);
            sp.getViewport().setBackground(new Color(44, 62, 80));
            tableFrame.add(sp);
        } catch (SQLException ex) {
            System.out.println("ManagePanel: " + ex.getMessage());
        }
    }
}

