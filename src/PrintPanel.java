import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class PrintPanel extends JPanel {
    public static PrintPanel Pref;
    private JButton print;
    private JButton back;
    private JButton get;
    private JTextArea textArea;
    private JLabel select;
    private JComboBox sturoll;
    private String name;
    private String ban;
    private String eng;
    private String math;
    private String gk;
    private String rel;
    private String sscience;


    private PrintPanel() {
        this.initialize();
        getRoll();
    }

    public static PrintPanel getRef() {
        if (Pref == null) {
            Pref = new PrintPanel();
        }

        return Pref;
    }

    private void initialize() {
        Pref = this;
        setLayout(null);
        setBackground(new Color(44, 62, 80));
        setBorder(BorderFactory.createLineBorder(new Color(207, 216, 220), 3));

        textArea = new JTextArea("Select The Student Roll and Press Make Button");
        textArea.setBounds(5, 5, 600, 607);
        textArea.setEditable(false);
        textArea.setVisible(true);
        textArea.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
        add(textArea);

        select = new JLabel("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Select Student Roll: </html></font>");
        select.setBounds(650, 50, 1000, 20);
        add(select);

        sturoll = new JComboBox();
        sturoll.setBounds(650, 90, 230, 20);
        add(sturoll);

        get = new JButton("<html><font size=4 face='Lucida Sans Unicode'color=black >Make</html></font>");
        get.setBackground(new Color(210, 215, 211));
        get.setBounds(750, 200, 100, 40);
        add(get);

        print = new JButton("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Print</html></font>");
        print.setBackground(new Color(46, 204, 113));
        print.setBounds(750, 300, 100, 40);
        add(print);

        back = new JButton("<html><font size=4 face='Lucida Sans Unicode' color=#ECECEC>Back</html></font>");
        back.setBackground(new Color(239, 72, 54));
        back.setBounds(750, 400, 100, 40);
        add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Select The Student Roll and Press Make Button");
                gui GuiRef = gui.getRef();
                GuiRef.remove(PrintPanel.getRef());
                GuiRef.add(UserPanel.getRef(), "Center");
                GuiRef.setBounds(470, 100, 400, 600);
                GuiRef.paintAll(GuiRef.getGraphics());
            }
        });
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    System.out.println("PrintPanel: " + ex.getMessage());
                }
            }
        });

        get.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getData();
                System.out.println(grade(average()));
                setText();
            }
        });

    }

    public void getRoll() {
        sturoll.removeAllItems();
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
            System.out.println("PrintPanel: " + ex.getMessage());
        }
    }

    private void getData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
            String halfQuery = "SELECT * from $tablename where roll= ?";
            String query = halfQuery.replace("$tablename", LoginPanel.getRef().username);
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, (String) sturoll.getItemAt(sturoll.getSelectedIndex()));
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                ban = rs.getString("bangla");
                eng = rs.getString("english");
                math = rs.getString("math");
                gk = rs.getString("gk");
                rel = rs.getString("religion");
                sscience = rs.getString("science");
            }
        } catch (SQLException ex) {
            System.out.println("PrintPanel: " + ex.getMessage());
        }
    }

    private int average() {
        int aver = (Integer.parseInt(ban) + Integer.parseInt(eng) + Integer.parseInt(math) + Integer.parseInt(gk) + Integer.parseInt(rel) + Integer.parseInt(sscience)) / 6;
        return aver;
    }

    private String grade(int grade) {
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else {
            return "F";
        }

    }

    private int parsiint(String a) {
        int x = Integer.parseInt(a);
        return x;
    }

    private void setText() {
        textArea.setText("==============================================================================================\n");
        textArea.append("\t            Kuratali School and College\n ");
        textArea.append("\t                   Kuratali, Dhaka\n");
        textArea.append("\t                 Academic Transcript\n");
        textArea.append("     Mark Sheet Of: " + name + "\n");
        textArea.append("------------------------------------------------------------------------------------------------\n");
        textArea.append("     Roll:          " + sturoll.getItemAt(sturoll.getSelectedIndex()) + "\n");
        textArea.append("     Average Grade: " + grade(average()) + "\n");
        textArea.append("------------------------------------------------------------------------------------------------\n");
        textArea.append("     Subject                                      Grades                     Marks\n\n");
        textArea.append("     Bangla Grade :                             " + grade(parsiint(ban)) + "\t        " + ban + "\n");
        textArea.append("     English Grade :                            " + grade(parsiint(ban)) + "\t        " + eng + "\n");
        textArea.append("     Math Grade :                                " + grade(parsiint(ban)) + "\t        " + math + "\n");
        textArea.append("     G-Knowledge Grade :                   " + grade(parsiint(ban)) + "\t        " + gk + "\n");
        textArea.append("     Religion Grade :                           " + grade(parsiint(ban)) + "\t        " + rel + "\n");
        textArea.append("     Social Science Grade :                  " + grade(parsiint(ban)) + "\t        " + sscience + "\n");
        textArea.append("------------------------------------------------------------------------------------------------\n");
        textArea.append("     Teacher's Remark:\n\n\n\n");
        textArea.append("      ------------------                                       -------------------\n");
        textArea.append("      Gurdian's Signature                                          Teacher's Signature\n");
    }
}
