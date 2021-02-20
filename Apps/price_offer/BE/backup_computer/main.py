
import sys
from pprint import pprint


def usage(app_name):
    app_usage = f"\nUsage: {app_name}\n"
    app_usage += " - create_profile\n"
    app_usage += " - run_backup\n"
    sys.exit(app_usage)



def create_profile():

    profile_name = input("Enter the profile name: ")

    items = []

    current_item = "x"

    while current_item != "":

        current_item = input("Enter item to backup (Empty enter to abort): ")
        if current_item != "":
            items.append(current_item)

    user_profile = {"name": profile_name, "items": items}
    pprint(user_profile)




if __name__ == '__main__':

    # Parse commandline arguments.
    action = ""

    if len(sys.argv) != 2:
        usage(sys.argv[0])
    else:
        action = (sys.argv[1])


    if action == "create_profile":
        create_profile()
    elif action == "backup":
        pass
    else:
        sys.exit(f"The action '{action}' is invalid.")













