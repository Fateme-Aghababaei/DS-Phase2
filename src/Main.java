import com.opencsv.CSVReader;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = readFile();
        while (true) {
            int n = printMenu();
            menu(n, matrix);
        }
    }

    public static Matrix readFile() {
        Matrix matrix = new Matrix();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Open CSV file...");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Comma-Separated Files (*.csv)", "csv"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader fileReader;
                fileReader = new FileReader(fileChooser.getSelectedFile());
                CSVReader csvReader = new CSVReader(fileReader);
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    MyLinkedList row = new MyLinkedList();
                    for (int i = 0; i < line.length; i++) {
                        int val = Integer.parseInt(line[i]);
                        if (val != 0) {
                            row.addLast(val, i);
                        }
                    }
                    matrix.addRow(row);
                    matrix.setColumnSize(line.length);
                }
                csvReader.close();
                fileReader.close();
            } catch (Exception ignored) {}
        }
        return matrix;
    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("0. insert\n1. delete\n2. search\n3. update\n4. print\n5. save file\n6. exit");
        System.out.println("Please enter a number: ");
        return sc.nextInt();
    }

    public static void insert0(Matrix matrix) {
        JTextField rowField = new JTextField();
        JTextField colField = new JTextField();
        JTextField valField = new JTextField();
        Object[] message = {
                "Input row index: ", rowField,
                "Input column index: ", colField,
                "Input value: ", valField
        };
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Enter all the values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int row = Integer.parseInt(rowField.getText());
            int col = Integer.parseInt(colField.getText());
            int val = Integer.parseInt(valField.getText());
            matrix.insert(row, col, val);
        }
    }

    public static void delete1(Matrix matrix) {
        JTextField rowField = new JTextField();
        JTextField colField = new JTextField();
        Object[] message = {
                "Input row index: ", rowField,
                "Input column index: ", colField,
        };
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Enter all the values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int row = Integer.parseInt(rowField.getText());
            int col = Integer.parseInt(colField.getText());
            matrix.delete(row, col);
        }
    }

    public static void search2(Matrix matrix) {
        JTextField valField = new JTextField();
        Object[] message = {"Input value: ", valField};
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Enter the value", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int val = Integer.parseInt(valField.getText());
            matrix.search(val);
        }
    }

    public static void update3(Matrix matrix) {
        JTextField rowField = new JTextField();
        JTextField colField = new JTextField();
        JTextField valField = new JTextField();
        Object[] message = {
                "Input row index: ", rowField,
                "Input column index: ", colField,
                "Input value: ", valField
        };
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Enter all the values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int row = Integer.parseInt(rowField.getText());
            int col = Integer.parseInt(colField.getText());
            int val = Integer.parseInt(valField.getText());
            matrix.update(row, col, val);
        }
    }

    public static void print4(Matrix matrix) {
        JRadioButton jr0 = new JRadioButton("Print as 2D array (Code 0)", true);
        JRadioButton jr1 = new JRadioButton("Print as compressed List (Code 1)");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jr0);
        bg.add(jr1);
        Object[] message = {
                "Please select printing method:",
                jr0,
                jr1
        };
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Printing Method", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            boolean method = jr1.isSelected();
            matrix.print(method);
        }
    }

    public static void menu(int n, Matrix matrix) {
        switch (n) {
            case 0:
                insert0(matrix);
                break;
            case 1:
                delete1(matrix);
                break;
            case 2:
                search2(matrix);
                break;
            case 3:
                update3(matrix);
                break;
            case 4:
                print4(matrix);
                break;
            case 5:
                matrix.save_file();
                break;
            case 6:
                System.exit(0);
                break;
        }
    }
}
