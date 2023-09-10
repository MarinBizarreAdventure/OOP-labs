using System;

class Guitar : IDisposable
{
    //Attibutes
    private string brand;
    private string model;
    private string color;
    
    //Constructor
    public Guitar(string brand, string model, string color)
    {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    // Method to play chord
    public void PlayChord(string chord)
    {
        Console.WriteLine("Playing the chord: " + chord);
    }

    //Method to tune the guitar
    public void Tune()
    {
        Console.WriteLine("Tuning the guitar.");
    }

    //Setter methods
    public void SetBrand(string brand)
    {
        this.brand = brand;
    }

    public void SetModel(string model)
    {
        this.model = model;
    }

    public void SetColor(string color)
    {
        this.color = color;
    }

    //Getter methods for attributes
    public string GetBrand()
    {
        return this.brand;
    }
    public string GetModel()
    {
        return model;
    }

    public string GetColor()
    {
        return color;
    }

    // Dispose method to release resources
    public void Dispose()
    {
        // Clean up any resources, if needed
    }
    public static void Main(string[] args)
    {
        // Create a new Guitar object
        using (Guitar myGuitar = new Guitar("Fender", "Stratocaster", "Sunburst"))
        {
            // Access and print attributes
            Console.WriteLine("Brand: " + myGuitar.GetBrand());
            Console.WriteLine("Model: " + myGuitar.GetModel());
            Console.WriteLine("Color: " + myGuitar.GetColor());

            // Call methods
            myGuitar.PlayChord("G major");
            myGuitar.Tune();

            // Update attributes using the setters
            myGuitar.SetBrand("Gibson");
            myGuitar.SetColor("Cherry Sunburst");

            // Print updated attributes
            Console.WriteLine("\nUpdated Attributes:");
            Console.WriteLine("Brand: " + myGuitar.GetBrand());
            Console.WriteLine("Model: " + myGuitar.GetModel());
            Console.WriteLine("Color: " + myGuitar.GetColor());
        }
    }
}