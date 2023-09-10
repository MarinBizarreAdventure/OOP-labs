package Guitar;

public class Guitar {
    //Attributes
    private String brand;
    private String model;
    private String color;

    //Constructor
    public Guitar(String brand, String model, String color){
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    // Method to play a cord
    public void playChord(String chord){
        System.out.println("Playing the chord: " + chord);
    }

    //Method to tune the guitar
    public void tune(){
        System.out.println("Tuning the guitar");
    }

    //Setter methods for attributes

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setColor(String color){
        this.color = color;
    }

    //Getter methods for attributes

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public String getColor(){
        return color;
    }
}
