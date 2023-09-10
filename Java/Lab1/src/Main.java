import Guitar.Guitar;

public class Main {
    public static void main(String[] args) {
        // Create a new Guitar object
        Guitar myGuitar = new Guitar("Fender", "Stratocaster", "Sunburst");

        // Access and print attributes
        System.out.println("Brand: " + myGuitar.getBrand());
        System.out.println("Model: " + myGuitar.getModel());
        System.out.println("Color: " + myGuitar.getColor());

        // Call methods
        myGuitar.playChord("G major");
        myGuitar.tune();

        // Update attributes using the setters
        myGuitar.setBrand("Gibson");
        myGuitar.setColor("Cherry Sunburst");

        // Print updated attributes
        System.out.println("\nUpdated Attributes:");
        System.out.println("Brand: " + myGuitar.getBrand());
        System.out.println("Model: " + myGuitar.getModel());
        System.out.println("Color: " + myGuitar.getColor());
    }
}