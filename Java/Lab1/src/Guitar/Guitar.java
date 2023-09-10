package Guitar;

public class Guitar {
    // Attributes
    private String brand;
    private String model;
    private String color;

    // Constructor
    public Guitar(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    // Method to play a chord
    public void playChord(String chord) {
        System.out.println("Playing the chord: " + chord);
    }

    // Method to tune the guitar
    public void tune() {
        System.out.println("Tuning the guitar.");
    }

    // Setter method for brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Setter method for model
    public void setModel(String model) {
        this.model = model;
    }

    // Setter method for color
    public void setColor(String color) {
        this.color = color;
    }

    // Getter methods for attributes (optional)
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}
