
import sys



def exit_with_error(error_message):
    sys.exit(f"\nError:\n{error_message}")



def print_usage(app_name):
    exit_with_error(f"Usage: {app_name}  [load  | guess]")
