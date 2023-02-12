import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Matrix {
    private ArrayList<MyLinkedList> rows;
    private int columnSize;

    public Matrix() {
        rows = new ArrayList<>();
    }

    public void addRow(MyLinkedList newRow) {
        rows.add(newRow);
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void insert(int row, int col, int value) {
        if (rows.get(row).isZero(col))
            rows.get(row).addBetween(value, col);
        else
            JOptionPane.showMessageDialog(null, "The element at specified row and column is not zero. Use update method to change this value.");
    }

    public void delete(int row, int col) {
        if (!rows.get(row).isZero(col))
            rows.get(row).remove(col);
        else
            JOptionPane.showMessageDialog(null, "The element at specified row and column is already zero.");
    }

    public void search(int value) {
        boolean found;
        for (MyLinkedList m : rows) {
            found = m.contains(value);
            if (found) {
                JOptionPane.showMessageDialog(null, "value " + value + " exists in matrix.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "value " + value + " doesn't exist in matrix.");
    } //O(mn)

    public void update(int row, int col, int value) {
        boolean changed = rows.get(row).changeValue(col, value);
        if (changed)
            JOptionPane.showMessageDialog(null, "Value at (" + row + ", " + col + ") is updated successfully.");
        else
            JOptionPane.showMessageDialog(null, "Value at (" + row + ", " + col + ") is zero. Please use insert method to change it.");
    }

    public void print(boolean type) {
        if (type) {
            for (int i = 0; i < rows.size(); i++) {
                MyLinkedList.Iterator it = rows.get(i).getHead();
                for (int j = 0; j < rows.get(i).getSize(); j++) {
                    System.out.println(i + " " + it.getColumnIndex() + " " + it.getValue());
                    it = it.next();
                }
            } //O(mn)
        } else {
            for (MyLinkedList row : rows) {
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < columnSize; j++) {
                    s.append(row.getValue(j)).append("\t\t");
                }
                s.append("\n");
                System.out.println(s);
            }
        } // O(mp2)
    }

    public void save_file() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Save CSV file...");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Comma-Separated Files (*.csv)", "csv"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".csv"))
                    file = new File(file.getAbsolutePath() + ".csv");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                for (MyLinkedList m : rows) {
                    MyLinkedList.Iterator it = m.getHead();
                    for (int j = 0; j < columnSize; j++) {
                        if (j != 0)
                            writer.write(",");
                        if (it.reachedEnd() || j < it.getColumnIndex())
                            writer.write(String.valueOf(0));
                        else {
                            writer.write(String.valueOf(it.getValue()));
                            it.next();
                        }
                    }
                    writer.write("\n");
                }
                writer.flush();
                writer.close();
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    } // O(mp)
}
