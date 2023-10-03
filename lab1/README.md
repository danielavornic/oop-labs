# Laboratory 1

## Laboratory restrictions

- No third party libraries are allowed, you can use only the libraries that are available by default.
- You are free to create as many classes as you need to achieve a well structured working system.
- No limitation of concepts: You can create interface classes, use concepts like Polymorphism, Inheritance, Abstraction if you deem necessary. It is not a must!

## Task

### Base Labortatory (8 Grade)

Build a program loop, an interactive command line for the TUM Board to be able to do the next operations:

- **Faculty operations:**

1. Create and assign a student to a faculty.
2. Graduate a student from a faculty.
3. Display current enrolled students (Graduates would be ignored).
4. Display graduates (Currently enrolled students would be ignored).
5. Tell or not if a student belongs to this faculty.

- **General operations:**

1. Create a new faculty.
2. Search what faculty a student belongs to by a unique identifier (for example by email
   or a unique ID).
3. Display University faculties.
4. Display all faculties belonging to a field. (Ex. FOOD_TECHNOLOGY)

### Improved Laboratory (9 Grade)

Design a saving and loading system for the Student Management system.

### Working System (10 Grade)

Design an operation Logging System.

- Add extra operations:

1. Batch enrollment operation for students via a text file.
2. Batch graduation operation for students via text file.
3. Validate inputs with meaningful error statements. (**Can’t graduate student: ivan@isa.utm.md (student not present), Operation <operation> is not a valid operation, Operation requires extra data** etc.)

## Implementation

My laboratory includes the functionalities for the working system (10 grade), with some invalid input error handling. The main program is in the Main class.

**Notes:**

For the file operations, change the `FOLDER_PATH` constant from `/src/menu/MenuConstants.java` accordingly.

The structure for the `studentsToEnroll.txt` file is the following:
`<firstName>,<lastName>,<email>,<enrollmentDate>,<dateOfBirth>,<isGraduated>`

The `studentsToGraduate.txt` file contains only the student emails.
