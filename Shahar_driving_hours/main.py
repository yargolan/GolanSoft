
def calculate_time(start, end):
    hours = 0

    time_end   = 60 * int(end.split(":")[0]) + int(end.split(":")[1])
    time_start = 60 * int(start.split(":")[0]) + int(start.split(":")[1])
    delta = time_end - time_start
    while delta >= 60:
        hours += 1
        delta -= 60

    returned_value = str(hours) + ":"
    if delta < 10:
        returned_value += "0" + str(delta)
    else:
        returned_value += str(delta)

    return returned_value


def main():
    # Get the date
    # date = input("Insert the date:            ")
    # time_start = input("Insert the start time:     ")
    # time_end = input("Insert the end time:       ")
    # odometer_start = input("Insert the start odometer: ")
    # odometer_end = input("Insert the end odometer:   ")
    # is_urban = input("Drive in city:             ")
    # is_non_urban = input("Drive outside city:        ")

    # Calculate
    # total_kilometers = int(odometer_end) - int(odometer_start)
    # total_time = calculate_time(time_start, time_end)
    total_time = calculate_time("16:45", "17:55")
    print(total_time)

    # line = ",".join([date, time_start, time_end, total_time, odometer_start, odometer_end, total_kilometers, is_urban, is_non_urban])
    # print(line)


if __name__ == '__main__':
    main()
