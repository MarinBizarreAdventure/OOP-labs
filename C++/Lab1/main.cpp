#include <iostream>
#include <string>

using namespace std;

class Guitar{
	//Atributes
	private:
		string* brand;
		string* model;
		string* color;
	public:
		//Constructor
		Guitar(string brand, string model, string color){
			this->brand = new string(brand);
			this->model = new string(model);
			this->color = new string(color);
		}
		
		// Destructor
		~Guitar(){
			delete brand;
			delete model;
			delete color;
		}
		
		//Method to play chord
		void playChord(string chord){
			cout << "playing the chord:" <<"\n";
		}
		// Method to tune the guitar
		void tune(){
			cout << "tuning the guitar.\n";
		}
		
		// Setter methods

		void setBrand(string brand){
			*this->brand = brand;
		}
		
		void setColor(string color){
			*this->color = color;
		}
		
		void setModel(string model) {
        	*this->model = model;
    	}
    	
    	// Getter methods for attributes
    	string getBrand() {
        return *brand;
    	}

	    string getModel() {
	        return *model;
	    }
	
	    string getColor() {
	        return *color;
	    }
};



int main() {
    // Create a new Guitar object
    Guitar myGuitar("Fender", "Stratocaster", "Sunburst");

    // Access and print attributes
    cout << "Brand: " << myGuitar.getBrand() << endl;
    cout << "Model: " << myGuitar.getModel() << endl;
    cout << "Color: " << myGuitar.getColor() << endl;

    // Call methods
    myGuitar.playChord("G major");
    myGuitar.tune();

    // Update attributes using the setters
    myGuitar.setBrand("Gibson");
    myGuitar.setColor("Cherry Sunburst");

    // Print updated attributes
    cout << "\nUpdated Attributes:" << endl;
    cout << "Brand: " << myGuitar.getBrand() << endl;
    cout << "Model: " << myGuitar.getModel() << endl;
    cout << "Color: " << myGuitar.getColor() << endl;

    return 0;
}

