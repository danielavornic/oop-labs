# Challenge 1

class User:
    def __init__(self, firstName, last_Name):
        self.firstName = firstName
        self.last_Name = last_Name

    def abbreviate_name(self):
        if (self.firstName == "" or self.last_Name == ""):
            raise ValueError("Invalid name")

        print(f"{self.firstName[0]}. {self.last_Name[0]}.")
