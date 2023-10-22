class Person:
    def __init__(self, first_name, last_name, age):
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.is_minor = age <= 17

    def print_age(self):
        print(f"{self.first_name} {self.last_name} is {self.age} years old")

    def print_is_minor(self):
        minor_status = "a minor" if self.is_minor else "not a minor"
        print(f"{self.first_name} {self.last_name} is {minor_status}")
