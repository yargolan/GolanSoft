class Garage:


    def __init__(self, name, address, phone, contact):
        self.name    = name
        self.phone   = phone
        self.contact = contact
        self.address = address


    def get_name(self):
        return self.name


    def get_phone(self):
        return self.phone


    def get_contact(self):
        return self.contact


    def get_address(self):
        return self.address
