import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagePanel extends JPanel {
    public static ManagePanel Mref;
    int subCount, numCount = 0;
    private JLabel[] subject = new JLabel[8];
    private JTextField[] num = new JTextField[6];
    private JPanel input, details, edit;
    private JComboBox sturoll;
    private JButton add, clear, editEdit, back, delete;
    private JTextField name, roll;
    private JLabel rollLabel, select, or, stuname, stuname1;
    private JCheckBox banCheck, engCheck, mathCheck, gkCheck, relCheck, scCheck;
    private JFrame tableFrame;
    private JTable table;

    private ManagePanel() {
        this.initialize();
        getRoll();
    }

    public static ManagePanel getRef() {
        if (Mref == null) {
            Mref = new ManagePanel();
        }

        return Mref;
    }

    private void initialize() {
        Mref = this;
        setLayout(null);
        setBackground(new Color(44, 62, 80));
        setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 3));

        input = new JPanel();
        input.setBounds(4, 4, 500, 610);
        input.setBorder(new TitledBorder("<html><font size=3 face='Lucida Sans Unicode' color=#ECECEC>ADD:</html></font>"));
        input.setBackground(new Color(44, 62, 80));
        input.setLayout(null);
        add(input);

        details = new JPanel();
        details.setBounds(10, 20, 480, 140);
        details.setBorder(new TitledBorder("<html><font size=3 face='Lucida Sans Unicode' color=#ECECEC>STUDENT DETAILS:</html></font>"));
        details.setBackground(new Color(44, 62, 80));
        details.setLayout(null);
        input.add(details);

        subject[0] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Student Name :</html></font>");
        subject[0].setBounds(30, 30, 200, 40);
        details.add(subject[0]);

        subject[1] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Roll No :</html></font>");
        subject[1].setBounds(30, 80, 200, 40);
        details.add(subject[1]);

        name = new JTextField();
        name.setBounds(240, 31, 200, 30);
        name.setBackground(new Color(210, 215, 211));
        details.add(name);

        roll = new JTextField();
        roll.setBounds(240, 81, 200, 30);
        roll.setBackground(new Color(210, 215, 211));
        details.add(roll);
        {
            subject[2] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Bangla :</html></font>");
            subject[3] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>English :</html></font>");
            subject[4] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Math :</html></font>");
            subject[5] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Gen-Knowledge :</html></font>");
            subject[6] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Religion :</html></font>");
            subject[7] = new JLabel("<html><font size=5 face='Lucida Sans Unicode' color=#ECECEC>Social Science :</html></font>");

            for (int i = 2; i < subject.length; i++) {
                subject[i].setBackground(new Color(44, 62, 80));
                subject[i].setBounds(30, 170 + subCount, 200, 40);
                input.add(subject[i]);
                subCount += 50;
            }

            for (int i = 0; i < num.length; i++) {
                num[i] = new JTextField();
                num[i].setBackground(new Color(210, 215, 211));
                num[i].setBounds(250, 171 + numCount, 200, 30);
                input.add(num[i]);
                numCount += 50;
            }
        }

        add = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Add</html></font>");
        add.setBounds(50, 500, 100, 40);
        add.setBackground(new Color(46, 204, 113));
        input.add(add);

        clear = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=black >Clear</html></font>");
        clear.setBounds(170, 500, 100, 40);
        clear.setBackground(new Color(210, 215, 211));
        input.add(clear);

        back = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Back</html></font>");
        back.setBounds(290, 500, 100, 40);
        back.setBackground(new Color(239, 72, 54));
        input.add(back);

        edit = new JPanel();
        edit.setBounds(510, 4, 465, 610);
        edit.setBorder(new TitledBorder("<html><font size=3 face='Lucida Sans Unicode' color=#ECECEC>EDIT:</html></font>"));
        edit.setBackground(new Color(44, 62, 80));
        edit.setLayout(null);
        add(edit);

        sturoll = new JComboBox();
        sturoll.setBounds(210, 30, 200, 20);
        edit.add(sturoll);

        stuname = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Student Name: </html></font>");
        stuname.setBounds(40, 70, 200, 50);
        edit.add(stuname);

        stuname1 = new JLabel();
        stuname1.setBounds(165, 70, 200, 50);
        edit.add(stuname1);

        rollLabel = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Select Roll Number: </html></font>");
        rollLabel.setBounds(50, 30, 200, 20);
        edit.add(rollLabel);

        select = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Select What Subjects You Want To Edit!</html></font>");
        select.setBounds(65, 50, 400, 200);
        select.setBackground(new Color(44, 62, 80));
        edit.add(select);

        banCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Bangla </html></font>");
        banCheck.setBounds(10, 210, 100, 20);
        banCheck.setBackground(new Color(44, 62, 80));
        edit.add(banCheck);

        engCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >English </html></font>");
        engCheck.setBounds(150, 210, 100, 20);
        engCheck.setBackground(new Color(44, 62, 80));
        edit.add(engCheck);

        mathCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Math </html></font>");
        mathCheck.setBounds(280, 210, 100, 20);
        mathCheck.setBackground(new Color(44, 62, 80));
        edit.add(mathCheck);

        gkCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Gen-Knowledge </html></font>");
        gkCheck.setBounds(10, 260, 100, 20);
        gkCheck.setBackground(new Color(44, 62, 80));
        edit.add(gkCheck);

        relCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Religion </html></font>");
        relCheck.setBounds(150, 260, 100, 20);
        relCheck.setBackground(new Color(44, 62, 80));
        edit.add(relCheck);

        scCheck = new JCheckBox("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Social-Science </html></font>");
        scCheck.setBounds(280, 260, 100, 20);
        scCheck.setBackground(new Color(44, 62, 80));
        edit.add(scCheck);

        editEdit = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Edit</html></font>");
        editEdit.setBackground(new Color(46, 204, 113));
        editEdit.setBounds(190, 355, 100, 40);
        edit.add(editEdit);

        or = new JLabel("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >OR</html></font>");
        or.setBounds(225, 415, 100, 40);
        edit.add(or);

        delete = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=#ECECEC >Delete Student!</html></font>");
        delete.setBounds(140, 465, 200, 40);
        delete.setBackground(new Color(239, 72, 54));
        edit.add(delete);

        sturoll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String roll = "" + sturoll.getItemAt(sturoll.getSelectedIndex());
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                    String halfquery = "SELECT name from $tablename where roll=?";
                    String query = halfquery.replace("$tablename", LoginPanel.getRef().username);
                    PreparedStatement state = conn.prepareStatement(query);
                    state.setString(1, roll);
                    ResultSet rs = state.executeQuery();
                    while (rs.next()) {
                        stuname1.setText("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>" + rs.getString("name") + "</html></font> ");
                    }
                } catch (SQLException ex) {
                    System.out.println("ManagePanel: " + ex.getMessage());
                }
            }

        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String roll = "" + sturoll.getItemAt(sturoll.getSelectedIndex());
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                    String halfQuery = "DELETE FROM $tablename WHERE roll = ?";
                    String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
                    PreparedStatement state = conn.prepareStatement(query);
                    state.setString(1, roll);
                    state.executeUpdate();
                    tableFrame.dispose();
                    getTable();
                    JOptionPane.showMessageDialog(ManagePanel.getRef(), "Student Successfully Deleted!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    System.out.println("ManagePanel: " + ex.getMessage());
                }
                sturoll.removeAllItems();
                getRoll();
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
                gui GuiRef = gui.getRef();
                GuiRef.remove(ManagePanel.getRef());
                GuiRef.add(UserPanel.getRef(), "Center");
                GuiRef.setBounds(470, 100, 400, 600);
                tableFrame.dispose();
                GuiRef.paintAll(GuiRef.getGraphics());
            }
        });

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sturoll.removeAllItems();
                add();
                getRoll();
            }
        });
        editEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

    }

    private void add() {
        if (name.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Name Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (roll.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Roll Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[0].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Bangla Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[1].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The English Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[2].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Math Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[3].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The General Knowlodge Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[4].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Religion Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (num[5].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill Up The Social Science Field", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (namecheck(name.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Name Can Only Contain Letters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!checkroll(roll.getText())) {
            JOptionPane.showMessageDialog(this, "Roll Can Only Contain Numbers & be 0-40", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[0].getText())) {
            JOptionPane.showMessageDialog(this, "Bangla Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[1].getText())) {
            JOptionPane.showMessageDialog(this, "English Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[2].getText())) {
            JOptionPane.showMessageDialog(this, "Math Can Only Contain Numbers And & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[3].getText())) {
            JOptionPane.showMessageDialog(this, "General Knowledge Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[4].getText())) {
            JOptionPane.showMessageDialog(this, "Religion Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!check(num[5].getText())) {
            JOptionPane.showMessageDialog(this, "Social Science Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                String halfQuery = "INSERT INTO `$tablename` (`name`, `roll`, `bangla`, `english`, `math`, `gk`, `religion`, `science`) VALUES (?,?,?,?,?,?,?,?)";
                String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
                PreparedStatement state = conn.prepareStatement(query);
                state.setString(1, name.getText());
                state.setString(2, roll.getText());
                state.setString(3, num[0].getText());
                state.setString(4, num[1].getText());
                state.setString(5, num[2].getText());
                state.setString(6, num[3].getText());
                state.setString(7, num[4].getText());
                state.setString(8, num[5].getText());
                state.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student Added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                clear();
                tableFrame.dispose();
                getTable();
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(this, "Roll No. Should Be Unique", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                System.out.println(ex.getErrorCode());
            }
        }
    }

    private void clear() {
        for (int i = 0; i < num.length; i++) {
            num[i].setText(null);
            num[i].setBackground(new Color(210, 215, 211));
        }
        name.setText(null);
        roll.setText(null);
    }

    private boolean check(String y) {
        int x;
        if (y.matches("[1-9]?\\d")) {
            x = Integer.parseInt(y);
            {
                if (x >= 0 && x <= 100) {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
    }

    private boolean checkroll(String y) {
        int x;
        if (y.matches("[1-9]?\\d")) {
            x = Integer.parseInt(y);
            {
                if (x >= 0 && x <= 40) {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
    }

    private boolean namecheck(String y) {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(y);
        boolean b;
        b = matcher.find();
        return b;
    }

    private void getRoll() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String halfQuery = "SELECT roll from $tablename";
            String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
            PreparedStatement state = conn.prepareStatement(query);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                sturoll.addItem(rs.getString("roll"));
            }
        } catch (SQLException ex) {
            System.out.println("ManagePanel: " + ex.getMessage());
        }
    }

    private void edit() {
        String roll = "" + sturoll.getItemAt(sturoll.getSelectedIndex());
        if ((banCheck.isSelected() == false) && (engCheck.isSelected() == false) && (mathCheck.isSelected() == false) && (gkCheck.isSelected() == false) && (relCheck.isSelected() == false) && (scCheck.isSelected() == false)) {
            JOptionPane.showMessageDialog(this, "Please Select What You Want To Edit", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (banCheck.isSelected()) {
            String banglaNum = JOptionPane.showInputDialog(this, "Enter New Bangla Number");
            if (banglaNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The Bangla Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(banglaNum) == false) {
                JOptionPane.showMessageDialog(this, "Bangla Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("bangla", banglaNum, roll);
            }
        }
        if (engCheck.isSelected()) {
            String englishNum = JOptionPane.showInputDialog(this, "Enter New English Number");
            if (englishNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The English Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(englishNum) == false) {
                JOptionPane.showMessageDialog(this, "English Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("english", englishNum, roll);
            }
        }
        if (mathCheck.isSelected()) {
            String mathNum = JOptionPane.showInputDialog(this, "Enter New Math Number");
            if (mathNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The Math Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(mathNum) == false) {
                JOptionPane.showMessageDialog(this, "Math Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("math", mathNum, roll);
            }
        }
        if (gkCheck.isSelected()) {
            String gkNum = JOptionPane.showInputDialog(this, "Enter New General Knowledge Number");
            if (gkNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The General Knowledge Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(gkNum) == false) {
                JOptionPane.showMessageDialog(this, "General Knowledge Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("gk", gkNum, roll);
            }
        }
        if (relCheck.isSelected()) {
            String relNum = JOptionPane.showInputDialog(this, "Enter New Religion Number");
            if (relNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The Religion Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(relNum) == false) {
                JOptionPane.showMessageDialog(this, "Religion Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("religion", relNum, roll);
            }
        }
        if (scCheck.isSelected()) {
            String sscienceNum = JOptionPane.showInputDialog(this, "Enter New Social Science Number");
            if (sscienceNum.isEmpty() == true) {
                JOptionPane.showMessageDialog(this, "Fill Up The Social Science Field", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (check(sscienceNum) == false) {
                JOptionPane.showMessageDialog(this, "Social Science Can Only Contain Numbers & be 0-100", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                editNum("science", sscienceNum, roll);
            }
        }

    }

    private void editNum(String SubName, String Num, String roll) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String halfQuery = "UPDATE $tablename SET $subname = ? WHERE roll= ?";
            String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
            String query1 = query.replace("$subname", SubName);
            PreparedStatement state = conn.prepareStatement(query1);
            state.setString(1, Num);
            state.setString(2, roll);
            state.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student's " + SubName + "Subject's Number Edited!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            tableFrame.dispose();
            getTable();
        } catch (SQLException ex) {
            System.out.println("ManagePanel: " + ex.getMessage());
        }
    }

    void getTable() {
        tableFrame = new JFrame("Student Data Table");
        tableFrame.setBounds(990, 0, 400, 350);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setResizable(false);
        tableFrame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 2));
        DefaultTableModel model = new DefaultTableModel(new String[]{"Roll", "Student Name", "Banlga", "English", "Math", "GK", "Religion", "Science"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String halfQuery = "SELECT * FROM $tablename ORDER BY roll";
            String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
            PreparedStatement state = conn.prepareStatement(query);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                String a = rs.getString("roll");
                String b = rs.getString("name");
                String c = rs.getString("bangla");
                String d = rs.getString("english");
                String e = rs.getString("math");
                String f = rs.getString("gk");
                String g = rs.getString("religion");
                String h = rs.getString("science");
                model.addRow(new String[]{a, b, c, d, e, f, g, h});
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

