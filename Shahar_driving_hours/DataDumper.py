
import os
import csv


class DataDumper:

    def __init__(self, db_file):
        self.db_file = db_file
        self.field_names = ['date', 'time_start', 'time_end', 'driven_time', 'odometer_start', 'odometer_end',
                            'driven_distance', 'urban', 'non-urban']


    def add(self, current_drive):
        # Verify that the CSV file exists with the needed headers.
        if not os.path.isfile(self.db_file):
            with open(self.db_file, mode='w') as csv_file:
                writer = csv.writer(csv_file, delimiter=",", quotechar='"', quoting=csv.QUOTE_MINIMAL)
                writer.writerow(self.field_names)

        # Add the current drive to the file.
        with open(self.db_file, mode = 'a') as csv_file:
            writer = csv.writer(csv_file, delimiter = ",", quotechar = '"', quoting = csv.QUOTE_MINIMAL)
            writer.writerow(",".join([
                current_drive['date'],
                current_drive['time_start'],
                current_drive['time_end'],
                current_drive['driven_time'],
                current_drive['odometer_start'],
                current_drive['odometer_end'],
                current_drive['driven_distance'],
                current_drive['urban'],
                current_drive['non_urban']
            ]))
