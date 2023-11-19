import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RandProductMaker extends JFrame {
    private JTextField nameField, descriptionField, idField, costField, recordCountField;
    private JButton addButton;
    private RandomAccessFile randomAccessFile;
    private int recordCount;

    public RandProductMaker() {
        // Initialize GUI components
        nameField = new JTextField(35);
        descriptionField = new JTextField(75);
        idField = new JTextField(6);
        costField = new JTextField();
        recordCountField = new JTextField(10);
        recordCountField.setEditable(false);

        addButton = new JButton("Add");

        // Add ActionListener to the "Add" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        // Set up the frame layout and other components
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Description: "));
        add(descriptionField);
        add(new JLabel("ID: "));
        add(idField);
        add(new JLabel("Cost: "));
        add(costField);
        add(new JLabel("Record Count: "));
        add(recordCountField);
        add(addButton);

        try {
            randomAccessFile = new RandomAccessFile("products.dat", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addRecord() {
        // Validate input fields and add the record to the random access file
        String name = nameField.getText();
        String description = descriptionField.getText();
        String id = idField.getText();
        double cost = Double.parseDouble(costField.getText());

        // Create a Product object and write to the random access file
        Product product = new Product(name, description, id, cost);
        try {
            product.writeToFile(randomAccessFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update record count field
        recordCountField.setText(Integer.toString(++recordCount));

        // Clear input fields
        nameField.setText("");
        descriptionField.setText("");
        idField.setText("");
        costField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RandProductMaker();
            }
        });
    }
}
