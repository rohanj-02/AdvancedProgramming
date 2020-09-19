import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Patient {

    private String name;
    private int age;
    private char tower;
    private LocalDate reporting;
    private LocalDate recovery;
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    Patient(String name, int age, char tower, LocalDate reporting) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.reporting = reporting;
        this.recovery = reporting.plusDays(21);
    }

    Patient() {
        this("John Doe", 10, 'A', LocalDate.now());
    }

    public String getFormattedReportingDate() {
        return this.reporting.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getFormattedRecoveryDate() {
        return this.recovery.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

//    public void printPatient() {
//        System.out.println("Name: " + name);
//        System.out.println("Age: " + age);
//        System.out.println("Tower: " + tower);
//        System.out.println("Reporting: " + this.getFormattedReportingDate());
//        System.out.println("Recovery: " + this.getFormattedRecoveryDate());
//    }

    public static String[][] getTable(Patient[] patients, LocalDate date) {
        String[][] data = new String[patients.length][];
        for (int i = 0; i < patients.length; i++) {
            data[i] = new String[7];
            data[i][0] = Integer.toString(i + 1);
            data[i][1] = patients[i].name;
            data[i][2] = Integer.toString(patients[i].age);
            data[i][3] = Character.toString(patients[i].tower);
            data[i][4] = patients[i].getFormattedReportingDate();
            data[i][5] = patients[i].getFormattedRecoveryDate();
            //queried date before reporting date
            if (date.compareTo(patients[i].reporting) < 0) {
                data[i][6] = "Unknown";
            }
            //queried date between reporting and recovery date
            else if (date.compareTo(patients[i].reporting) >= 0 && date.compareTo(patients[i].recovery) <= 0) {
                data[i][6] = "Active";
            }
            //queried date after recovery date
            else if (date.compareTo(patients[i].recovery) > 0) {
                data[i][6] = "Recovered";
            } else {
                data[i][6] = "Corner case";
            }
        }
        return data;
    }

    public static int[] getTowerCount(String[][] data, char tower) {
        int[] count = new int[2];
        for (int i = 0; i < data.length; i++) {
            if (data[i][3].equals(Character.toString(tower))) {
                if (data[i][6].equals("Active")) {
                    count[0]++;
                }
                if (data[i][6].equals("Recovered")) {
                    count[1]++;
                }
            }
        }
        return count;
    }

}


public class Main {

    static void populate(Patient[] patients) {
        patients[0] = new Patient("Flora", 6, 'A', LocalDate.of(2020, Month.APRIL, 1));
        patients[1] = new Patient("Denys", 24, 'B', LocalDate.of(2020, Month.APRIL, 1));
        patients[2] = new Patient("Jim", 42, 'C', LocalDate.of(2020, Month.MAY, 18));
        patients[3] = new Patient("Hazel", 87, 'D', LocalDate.of(2020, Month.JUNE, 23));
        patients[4] = new Patient("Caery", 72, 'A', LocalDate.of(2020, Month.JUNE, 1));
        patients[5] = new Patient("David", 7, 'B', LocalDate.of(2020, Month.JUNE, 14));
        patients[6] = new Patient("Kevim", 37, 'D', LocalDate.of(2020, Month.JUNE, 5));
        patients[7] = new Patient("Tom", 67, 'D', LocalDate.of(2020, Month.JUNE, 20));
        patients[8] = new Patient("Bob", 74, 'A', LocalDate.of(2020, Month.JULY, 4));
        patients[9] = new Patient("Rachel", 48, 'C', LocalDate.of(2020, Month.JULY, 24));
        patients[10] = new Patient("Thomas", 21, 'C', LocalDate.of(2020, Month.JUNE, 11));
        patients[11] = new Patient("Mary", 17, 'D', LocalDate.of(2020, Month.JUNE, 21));
        patients[12] = new Patient("Smith", 89, 'A', LocalDate.of(2020, Month.AUGUST, 7));
        patients[13] = new Patient("Pearson", 47, 'B', LocalDate.of(2020, Month.JUNE, 4));
        patients[14] = new Patient("Anderson", 62, 'B', LocalDate.of(2020, Month.JULY, 27));
        patients[15] = new Patient("Johnson", 10, 'D', LocalDate.of(2020, Month.AUGUST, 1));
        patients[16] = new Patient("Robertz", 50, 'A', LocalDate.of(2020, Month.AUGUST, 9));
        patients[17] = new Patient("Julie", 86, 'B', LocalDate.of(2020, Month.MAY, 2));
        patients[18] = new Patient("Edith", 42, 'D', LocalDate.of(2020, Month.JUNE, 7));
        patients[19] = new Patient("John", 95, 'D', LocalDate.of(2020, Month.JUNE, 1));
    }

    public static boolean isDateValid(String format, String val) {
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate date = LocalDate.parse(val, FORMAT);
            String formattedDate = date.format(FORMAT);
            return formattedDate.equals(val);
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Patient[] patients = new Patient[20];
        populate(patients);
        GUI f = new GUI(patients);
    }
}


class GUI extends JFrame implements ActionListener {

    JFrame frame;
    JCheckBox towerA;
    JCheckBox towerB;
    JCheckBox towerC;
    JCheckBox towerD;
    JTextField dateInput;
    JLabel dateLabel;
    JButton submit;
    JButton reset;
    Patient[] patients;
    JTable table;
    JTable numberTable;
    JPanel towerCheckboxPanel;
    JPanel dateSubmitPanel;
    JPanel tablePanel;
    JPanel towerNumberPanel;
    JScrollPane tableScrollPane;
    JTableHeader numberHeader;

    JTextField towerNumberA;
    JLabel towerNumberALabel;
    JTextField towerNumberB;
    JLabel towerNumberBLabel;
    JTextField towerNumberC;
    JLabel towerNumberCLabel;
    JTextField towerNumberD;
    JLabel towerNumberDLabel;

    Color BLUE_BACKGROUND;
    Color WHITE_TEXT;
    Color BUTTON_RED;

    Font FONT_TYPE;

    GUI(Patient[] patients) {
        //Initialisation
        BLUE_BACKGROUND = new Color(31,64,104);
        BLUE_BACKGROUND = new Color(22,36,71);
        WHITE_TEXT = new Color(255,250,250);
        BUTTON_RED = new Color(228,63,90);
        FONT_TYPE = new Font("Verdana", Font.PLAIN, 12);

        frame = new JFrame("Assignment 3");
        towerA = new JCheckBox("Tower A");
        towerA.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        towerB = new JCheckBox("Tower B");
        towerB.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        towerC = new JCheckBox("Tower C");
        towerC.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        towerD = new JCheckBox("Tower D");
        towerD.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        dateInput = new JTextField(20);
        dateLabel = new JLabel("Date (DD/MM/YYYY)");
        submit = new JButton("Submit");
        reset = new JButton("Reset");
        this.patients = patients;

        //Color Initialisation
        towerA.setBackground(BLUE_BACKGROUND);
        towerB.setBackground(BLUE_BACKGROUND);
        towerC.setBackground(BLUE_BACKGROUND);
        towerD.setBackground(BLUE_BACKGROUND);
        towerA.setForeground(WHITE_TEXT);
        towerB.setForeground(WHITE_TEXT);
        towerC.setForeground(WHITE_TEXT);
        towerD.setForeground(WHITE_TEXT);
        frame.setBackground(BLUE_BACKGROUND);
        dateLabel.setBackground(BLUE_BACKGROUND);
        dateLabel.setForeground(WHITE_TEXT);
        dateInput.setBackground(BLUE_BACKGROUND);
        dateInput.setForeground(WHITE_TEXT);
        submit.setBackground(BUTTON_RED);
        submit.setForeground(WHITE_TEXT);
        reset.setBackground(BUTTON_RED);
        reset.setForeground(WHITE_TEXT);
        towerA.setFont(FONT_TYPE);
        towerB.setFont(FONT_TYPE);
        towerC.setFont(FONT_TYPE);
        towerD.setFont(FONT_TYPE);
        dateLabel.setFont(FONT_TYPE);
        dateInput.setFont(FONT_TYPE);
        submit.setFont(FONT_TYPE);
        reset.setFont(FONT_TYPE);

        towerNumberA = new JTextField(3);
        towerNumberB = new JTextField(3);
        towerNumberC = new JTextField(3);
        towerNumberD = new JTextField(3);
        towerNumberALabel = new JLabel("A");
        towerNumberBLabel = new JLabel("B");
        towerNumberCLabel = new JLabel("C");
        towerNumberDLabel = new JLabel("D");


        dateLabel.setLabelFor(dateInput);
        submit.addActionListener(this);
        reset.addActionListener(this);

        table = new JTable();
        numberTable = new JTable();

        table.setBackground(BLUE_BACKGROUND);
        numberTable.setBackground(BLUE_BACKGROUND);
        numberHeader = numberTable.getTableHeader();
        numberHeader.setBackground(BLUE_BACKGROUND);
        numberHeader.setForeground(WHITE_TEXT);

        JTableHeader theader = table.getTableHeader();
        theader.setBackground(BLUE_BACKGROUND);
        theader.setForeground(WHITE_TEXT);

        //Adding Components
        towerCheckboxPanel = new JPanel();
        GridLayout towerLayout = new GridLayout(1, 4, 10, 10);
        towerCheckboxPanel.setLayout(towerLayout);
        towerCheckboxPanel.add(towerA);
        towerCheckboxPanel.add(towerB);
        towerCheckboxPanel.add(towerC);
        towerCheckboxPanel.add(towerD);

        dateSubmitPanel = new JPanel();
        dateSubmitPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        dateSubmitPanel.add(dateLabel);
        dateSubmitPanel.add(dateInput);
        dateSubmitPanel.add(submit);
        dateSubmitPanel.add(reset);

        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 1, 5, 5));
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBackground(BLUE_BACKGROUND);
        tablePanel.add(tableScrollPane);

        towerNumberPanel = new JPanel();
        towerNumberPanel.setLayout(new BorderLayout());
        towerNumberPanel.add(numberTable);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1, 10, 10));
        inputPanel.add(towerCheckboxPanel);
        inputPanel.add(dateSubmitPanel);
        inputPanel.add(towerNumberPanel);

        //Panel Colors
        towerCheckboxPanel.setBackground(BLUE_BACKGROUND);
        dateSubmitPanel.setBackground(BLUE_BACKGROUND);
        tableScrollPane.setBackground(BLUE_BACKGROUND);
        towerNumberPanel.setBackground(BLUE_BACKGROUND);
        tablePanel.setBackground(BLUE_BACKGROUND);
        inputPanel.setBackground(BLUE_BACKGROUND);

        //Essential
        frame.setLayout(new GridLayout(2, 1, 5, 5));
        frame.add(inputPanel);
        frame.add(tablePanel);
        frame.getContentPane().setBackground(BLUE_BACKGROUND);
        inputPanel.setVisible(true);
        towerCheckboxPanel.setVisible(true);
        dateSubmitPanel.setVisible(true);
        tablePanel.setVisible(false);

        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String[][] getTowerCount(String[][] records) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        if (towerA.isSelected()) {
            int[] a = Patient.getTowerCount(records, 'A');
            ArrayList<String> stringA = new ArrayList<String>();
            stringA.add("A");
            stringA.add(Integer.toString(a[0]));
            stringA.add(Integer.toString(a[1]));
            data.add(stringA);
        }
        if (towerB.isSelected()) {
            int[] b = Patient.getTowerCount(records, 'B');
            ArrayList<String> stringB = new ArrayList<String>();
            stringB.add("B");
            stringB.add(Integer.toString(b[0]));
            stringB.add(Integer.toString(b[1]));
            data.add(stringB);
        }
        if (towerC.isSelected()) {
            int[] c = Patient.getTowerCount(records, 'C');
            ArrayList<String> stringC = new ArrayList<String>();
            stringC.add("C");
            stringC.add(Integer.toString(c[0]));
            stringC.add(Integer.toString(c[1]));
            data.add(stringC);
        }
        if (towerD.isSelected()) {
            int[] d = Patient.getTowerCount(records, 'D');
            ArrayList<String> stringD = new ArrayList<String>();
            stringD.add("D");
            stringD.add(Integer.toString(d[0]));
            stringD.add(Integer.toString(d[1]));
            data.add(stringD);
        }
        String[][] returnData = new String[data.size()][];
        for(int i = 0; i < data.size(); i++){
            returnData[i] = data.get(i).toArray(new String[0]);
        }
        return returnData;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String date = dateInput.getText();
            boolean x = Main.isDateValid("dd/MM/yyyy", date);
            if (!x) {
                JOptionPane.showMessageDialog(this, "Incorrect Date! Please enter date in format: DD/MM/YYYY");
            } else {
                if (tableScrollPane.isDisplayable()) {
                    tablePanel.remove(tableScrollPane);
                    towerNumberPanel.remove(numberHeader);
                    towerNumberPanel.remove(numberTable);
                }
                DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate formattedDate = LocalDate.parse(date, FORMAT);
                String[] columns = {"S. No.", "Name", "Age", "Tower", "Reporting", "Recovery", "Status"};
                String[][] data = Patient.getTable(patients, formattedDate);
                String[][] towerCount = getTowerCount(data);

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );

                String[] countColumns = {"Tower", "Active","Recovered"};
                numberTable = new JTable(towerCount, countColumns);
                for(int i = 0; i < countColumns.length; i++){
                    numberTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                numberHeader = numberTable.getTableHeader();
                numberHeader.setBackground(BLUE_BACKGROUND);
                numberHeader.setForeground(WHITE_TEXT);
                numberHeader.setFont(FONT_TYPE);
                numberTable.setVisible(true);
                numberTable.setBackground(BLUE_BACKGROUND);
                numberTable.setForeground(WHITE_TEXT);
                numberTable.setFont(FONT_TYPE);
                towerNumberPanel.add(numberHeader, BorderLayout.NORTH );
                towerNumberPanel.add(numberTable);
                towerNumberPanel.setVisible(true);

                table = new JTable(data, columns);
                for(int i = 0; i < columns.length; i++){
                    table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                JTableHeader theader = table.getTableHeader();
                theader.setBackground(BLUE_BACKGROUND);
                theader.setForeground(WHITE_TEXT);
                theader.setFont(FONT_TYPE);

                table.setVisible(true);
                table.setBackground(BLUE_BACKGROUND);
                table.setForeground(WHITE_TEXT);
                table.setFont(FONT_TYPE);
                tableScrollPane = new JScrollPane(table);
                tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                tableScrollPane.setBackground(BLUE_BACKGROUND);
                tablePanel.add(tableScrollPane);
                tablePanel.setVisible(true);
                frame.setVisible(true);
            }

        }
        else if(e.getSource() == reset){
            towerA.setSelected(false);
            towerB.setSelected(false);
            towerC.setSelected(false);
            towerD.setSelected(false);
            dateInput.setText("");
            if (tableScrollPane.isDisplayable()) {
                tablePanel.setVisible(false);
                towerNumberPanel.setVisible(false);
                towerNumberPanel.setVisible(false);
            }
            towerNumberPanel.setVisible(false);
            tablePanel.setVisible(false);
            tableScrollPane.setVisible(false);
            frame.setVisible(true);
        }

    }
}