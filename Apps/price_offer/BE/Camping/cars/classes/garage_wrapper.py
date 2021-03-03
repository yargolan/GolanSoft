
class GarageWrapper:

    garages = {}


    def __init__(self):

        global garages

        if len(garages) == 0:
            pass




    def get_details_all(self):
        for name in self.garages.keys():
            print(self.garages.get(name))
        print("--------------------")


    def get_details(self):
        if self.name in self.garages.keys():
            print(self.garages.get(self.name))
        else:
            print("There is no such garage :", self.name)
        print("====================")


