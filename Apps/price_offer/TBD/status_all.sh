#!/bin/bash

CWD=$(pwd)

for repo in $(ls); do

    fullPath="${CWD}/${repo}"

    if [ -d ${fullPath} ]; then

        echo "Repo:  ${repo}"

        cd ${fullPath}
        git status
        echo "+-------------------------------------------------------"
        echo ""
        echo ""
        echo ""
	sleep 1
    fi
done

