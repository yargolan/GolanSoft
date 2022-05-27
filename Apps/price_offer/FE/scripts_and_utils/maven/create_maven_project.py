#!/usr/bin/env python3

import os
import sys


data = {}

pwd = os.getcwd()

def get_input():

    # Root folder
    answer = input(f"Enter the root folder (Default: {pwd}): ")
    if answer is None or answer == "":
        data['root_dir'] = pwd
    else:
        data['root_dir'] = answer.strip()

    # Project's name
    answer = None
    while answer is None or answer == "":
        answer = input("Enter the project's name        : ")
        data['project_name'] = answer.strip()

    # Group ID
    answer = None
    while answer is None or answer == "":
        answer = input("Enter the project's group ID    : ")
        data['gid'] = answer.strip()

    # Artifact ID
    answer = None
    while answer is None or answer == "":
        answer = input("Enter the project's artifact ID : ")
        data['aid'] = answer.strip()

    # Group ID
    answer = None
    while answer is None or answer == "":
        answer = input("Enter the project's version     : ")
        data['version'] = answer.strip()


def generate_tree_structure():

    gid = str(data['gid']).lower()
    gid = gid.replace(".", "/")

    folders = [
        f"{data['root_dir']}/{data['project_name']}/src/main/java/{gid}",
        f"{data['root_dir']}/{data['project_name']}/src/test/java/{gid}"
    ]

    for folder in folders:
        os.makedirs(folder, exist_ok=True)


def generate_files():

    # Read the template pom
    template_pom = f"{os.path.dirname(__file__)}/template_pom.xml"
    with open(template_pom) as template:
        template_lines = template.readlines()

    # Generate the actual pom
    pom_file = f"{data['root_dir']}/{data['project_name']}/pom.xml"
    with open(pom_file, 'a') as pom:
        for line in template_lines:
            line = line.replace('__GID__',     data['gid'])
            line = line.replace('__AID__',     data['aid'])
            line = line.replace('__VERSION__', data['version'])

            pom.write(line)

    # Generate a dummy class.
    gid = str(data['gid']).lower()
    gid_lower = gid.replace(".", "/")

    aid_lower = str(data['aid']).lower()
    prj_lower = str(data['project_name']).lower()

    class_file = f"{data['root_dir']}/{data['project_name']}/src/main/java/{gid_lower}/{data['aid']}.java"
    with open(class_file, 'a') as file:
        file.write(f"package {gid}.{aid_lower};\n")
        file.write("\n")
        file.write("\n")
        file.write(f"public class {prj_lower} " + '{' + "\n")
        file.write(f"    public {prj_lower} " + '{' + "\n")
        file.write("\n")
        file.write("    }\n")
        file.write("}\n")
        file.write("\n")



if __name__ == "__main__":

    if len(sys.argv)-1 == 5:
        data['version']      = sys.argv.pop()
        data['aid']          = sys.argv.pop()
        data['gid']          = sys.argv.pop()
        data['project_name'] = sys.argv.pop()
        data['root_dir']     = sys.argv.pop()
    else:
        # Get the input from the user
        get_input()


    # Generate the folders structure.
    print("Generate the folders structure.")
    generate_tree_structure()


    # Generate the files.
    print("Generate the files.")
    generate_files()
