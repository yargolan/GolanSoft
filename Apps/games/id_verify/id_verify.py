

def get_id_from_user():

    is_valid_input = False

    while not is_valid_input:

        val = str(input("Enter your ID number :  "))

        if len(val) < 8:
            print("The ID provided is  too short.")
        elif len(val) > 8:
            print("The ID provided is  too long.")
        else:
            return val


def validate_id_number(id_number):

    total = 0

    current_multiplier = 1

    id_parts = list(id_number)

    for index in range(0, 8):

        current_digit = int(id_parts[index])

        total += current_digit * current_multiplier

        if current_multiplier == 1:
            current_multiplier += 1
        else:
            current_multiplier -= 1

    return total % 10 == 0



def main():

    # Get the ID from the user
    id_number = get_id_from_user()

    # Validate the ID.

    if validate_id_number(id_number):
        print("The ID number is valid.")
    else:
        print("The ID number is invalid.")


# Main
if __name__ == "__main__":
    main()
