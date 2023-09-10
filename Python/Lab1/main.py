

class Guitar:
    def __init__(self, brand, model, color):
        self.brand = brand
        self.model = model
        self.color = color

    def playChord(self, chord):
        print(f"Playing the chord: {chord}")

    def tune(self):
        print("Tuning the guitar.")

    def setBrand(self, brand):
        self.brand = brand

    def setModel(self, model):
        self.model = model

    def setColor(self, color):
        self.color = color

    def getBrand(self):
        return self.brand

    def getModel(self):
        return self.model

    def getColor(self):
        return self.color

# Create a new Guitar object
my_guitar = Guitar("Fender", "Stratocaster", "Sunburst")

# Access and print attributes
print("Brand:", my_guitar.getBrand())
print("Model:", my_guitar.getModel())
print("Color:", my_guitar.getColor())

# Call methods
my_guitar.playChord("G major")
my_guitar.tune()

# Update attributes using the setters
my_guitar.setBrand("Gibson")
my_guitar.setColor("Cherry Sunburst")

# Print updated attributes
print("\nUpdated Attributes:")
print("Brand:", my_guitar.getBrand())
print("Model:", my_guitar.getModel())
print("Color:", my_guitar.getColor())