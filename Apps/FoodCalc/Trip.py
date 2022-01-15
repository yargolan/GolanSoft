from SqliteWrapper import SqliteWrapper


class Trip:
    def __init__(self, trip_name):
        self.db = SqliteWrapper()
        #self.name = trip_name


    def get_name(self):
        return self.name


    def exists(self):
        db = SqliteWrapper()
        return db.is_trip_exists(self.get_name())

    def add(self):

    trip.add(trip_name=trip_name, date=trip_date)
