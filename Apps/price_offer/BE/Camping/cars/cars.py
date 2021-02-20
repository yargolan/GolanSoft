import sys
from classes import colors


def generate_message(*parts):

    message = ""

    for part in parts:
        message += part

    return message + colors.END


def main():

    main_choices = ["Garages", "Maintenance", "Quit"]

    quit_index = len(main_choices)

    user_choice = 0

    while user_choice != quit_index:

        print("\n")

        for i in range(1, len(main_choices) + 1):
            print(colors.blue(str(i) + ". " + colors.green(main_choices[i-1])))

        user_choice = int(input(colors.yellow("Pick your choice: ")))


        # Valid choices
        if user_choice == 1:
            action_garages()
        elif user_choice == 2:
            action_maintenance()
        else:
            if user_choice == quit_index:
                pass
            else:
                print(colors.bold_red("Invalid option.\n"))
                user_choice = 0


    print(colors.bold_blue("\nGoodbye...\n"))
    sys.exit(0)



def action_garages():
    garages_option = ["Add garage", "Edit garage", "Delete garage"]

    print("\n")

    for i in range(1, len(garages_option) + 1):
        print(colors.blue(str(i) + ". " + colors.green(garages_option[i - 1])))



def action_maintenance():
    print("maintenance")


if __name__ == '__main__':
    main()
