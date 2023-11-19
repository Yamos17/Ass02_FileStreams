import java.io.*;

public class Product {
    private String name;
    private String description;
    private String id;
    private double cost;

    public Product() {
        // Default constructor
    }

    public Product(String name, String description, String id, double cost) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void writeToFile(RandomAccessFile file) throws IOException {
        // Write the product data to the random access file
        file.writeUTF(padString(name, 35));
        file.writeUTF(padString(description, 75));
        file.writeUTF(padString(id, 6));
        file.writeDouble(cost);
    }

    public static Product readFromFile(RandomAccessFile file) throws IOException {
        // Read the product data from the random access file
        Product product = new Product();
        product.setName(file.readUTF().trim());
        product.setDescription(file.readUTF().trim());
        product.setId(file.readUTF().trim());
        product.setCost(file.readDouble());
        return product;
    }

    private String padString(String input, int length) {
        // Pad the string with spaces to the specified length
        StringBuilder paddedString = new StringBuilder(input);
        while (paddedString.length() < length) {
            paddedString.append(" ");
        }
        return paddedString.toString();
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", ID: " + id + ", Cost: " + cost;
    }
}
