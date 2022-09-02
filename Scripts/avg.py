#!/usr/bin/python3 -u

total_grade = 0
index = 0

while True:
    current_grade = int(input("Enter grade, '-1' to abort : "))
    if current_grade == -1:
        break
    else:
        if current_grade >= 0 and current_grade <=100:
            total_grade += current_grade
            index = index + 1
        else:
            print("The grade must be between 0 and 100")
        
if index == 0:
    print("No grades entered.")
else:
    print(f"The avg of {index} grades is :", total_grade / index)
