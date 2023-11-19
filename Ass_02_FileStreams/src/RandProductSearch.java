import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RandProductSearch extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private RandomAccessFile randomAccessFile;

    public RandProductSearch() {
        // Initialize GUI components
        searchField = new JTextField();
        resultArea = new JTextArea(20, 50);
        resultArea.setEditable(false);
        searchButton = new JButton("Search");

        // Add ActionListener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });

        // Set up the frame layout and other components
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Product: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        try {
            randomAccessFile = new RandomAccessFile("products.dat", "r");
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchProducts() {
        // Retrieve and display products that match the search criteria
        String searchTerm = searchField.getText();
        resultArea.setText(""); // Clear previous results

        // Iterate through the records and display matching products
        try {
            randomAccessFile.seek(0);
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                Product product = Product.readFromFile(randomAccessFile);
                if (product.getName().contains(searchTerm)) {
                    resultArea.append(product.toString() + "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RandProductSearch();
            }
        });
    }
}
