
import os
import csv





class DataDumper:

    def __init__(self, db_file):
        self.db_file = db_file


    def add(self, current_drive):

        if not os.path.isfile(self.db_file):
            with open('employee_birthday.txt') as csv_file:
                csv_reader = csv.reader(csv_file, delimiter = ',')
                line_count = 0
                for row in csv_reader:
                    if line_count == 0:
                        print(f'Column names are {", ".join(row)}')
                        line_count += 1
                    else:
                        print(f'\t{row[0]} works in the {row[1]} department, and was born in {row[2]}.')
                        line_count += 1
                print(f'Processed {line_count} lines.')

        # Now the file exists for sure.
        with open(self.db_file) as csv_file:
            csv_reader = csv.reader(csv_file, delimiter = ',')
            line_count = 0
            for row in csv_reader:
                if line_count == 0:
                    print(f'Column names are {", ".join(row)}')
                    line_count += 1
                else:
                    print(f'\t{row[0]} works in the {row[1]} department, and was born in {row[2]}.')
                    line_count += 1
            print(f'Processed {line_count} lines.')



        #     with open(data_file, "r") as d:
        #         drives = json.load(d)
        # else:
        #     drives = []
        # drives.append(current_drive)
        #
        # with open("data.json", "a") as d:
        #     json.dump(IteratorAsList(current_drive), d, indent = 2, ensure_ascii = True)
