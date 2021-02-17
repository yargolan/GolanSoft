
import messages


def is_palindrom(test_string):
    
    test_list = list(test_string)
    
    index_end   = len (test_list) - 1
    index_start = 0

    while index_end > index_start:
        if test_list[index_start] != test_list[index_end]:
            return False
        index_start += 1
        index_end   -= 1
    
    return True


def main():
    
    user_string = input("Please insert your string: ")

    if is_palindrom(user_string):
        print (messages.message_true())
    else:
        print (messages.message_false())


if __name__ == "__main__":
    main()
