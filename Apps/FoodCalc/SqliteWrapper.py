import os
import sqlite3


class SqliteWrapper:

    def __init__(self):

        self.db_name = "trips.db"

        if not os.path.exists(self.db_name):
            connection = sqlite3.connect(self.db_name)
            connection.execute('''CREATE TABLE TRIPS
                     (ID INTEGER PRIMARY KEY AUTOINCREMENT,
                     NAME           TEXT  NOT NULL,
                     DATE           TEXT  NOT NULL);''')
            connection.close()


    def get_name(self):
        return self.db_name


    def is_trip_exists(self, trip_name):

        f_exists = False

        conn = sqlite3.connect(self.get_name())

        cursor = conn.execute("SELECT NAME FROM TRIPS")
        for row in cursor:
            if row[0] == trip_name:
                f_exists = True
                break

        return f_exists


    def add_trip(self, trip_name, trip_date):
        conn = sqlite3.connect(self.get_name())
        command = f"INSERT INTO TRIPS (NAME,DATE) VALUES ('{trip_name}', '{trip_date}')"
        print(command)
        conn.execute(command)
        conn.commit()
        conn.close()
