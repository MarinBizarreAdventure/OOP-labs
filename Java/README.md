Dear Mr. Flocea,

I apologize for not meeting the initial expectations for this lab assignment. Last week was particularly hectic for me due to various commitments, including organizing the XFaf event. Consequently, I was unable to dedicate as much time to this project as I had initially intended. However, I want to clarify that I have successfully implemented all the tasks required for a 10-grade level, despite the deviations from my original plan.

Please allow me to correct any errors and provide a paraphrased version of the project description to better align with the intended scope and goals of the assignment.

Thank you for your understanding.
xdddd 
# Student Management System

The Student Management System is a command-line Java application designed for the Technical University of Moldova (TUM) to manage student and faculty records. The system adheres to the requirements and restrictions outlined in the laboratory assignment.

## Project Structure

The project follows a well-organized structure to maintain code modularity and readability:

- `src/` - Contains the source code of the application.
  - `datastorage/` - Data storage classes.
  - `enums/` - Enumerations used in the system.
  - `models/` - Model classes representing faculties and students.
  - `menu/` - Command menu and command classes for user interactions.
  - `manager/` - Manager classes to handle faculty and student operations.
  - `services/` - Service classes for file operations and logging.
  - `utils/` - Utility classes for logging and input validation.

## Features


- Faculty operations:
  - Create and assign a student to a faculty.
  - Graduate a student from a faculty.
  - Display currently enrolled students (Graduates are ignored).
  - Display graduates (Currently enrolled students are ignored).
  - Determine whether a student belongs to a faculty.
  
- General operations:
  - Create a new faculty.
  - Search for the faculty to which a student belongs using a unique identifier (e.g., email or ID).
  - Display University faculties.
  - Display all faculties belonging to a specific field (e.g., FOOD_TECHNOLOGY).


- Saving and loading system:
  - The system can save its state, ensuring that all previous operations remain valid even after restarting.
  - A SaveManager (or FileManager) class is implemented to achieve this functionality.


- Operation Logging System:
  - The system logs every meaningful operation, including student creation, graduation, and faculty creation, to enable monitoring by the TUM Board.
  
- Additional operations:
  - Batch enrollment of students via a text file (format: `ns/<faculty abbreviation>/<firstname>/<lastname>/<email>/<day>/<month>/<year>`).
  - Batch graduation of students via a text file (format: `gs/<email>`).
  - Input validation with meaningful error messages for various scenarios.
## File Operations

- Batch enrollment and graduation operations are supported using text files.
- Create text files with specified formats for batch enrollment and graduation.
- Use the respective commands in the system to process these files.

