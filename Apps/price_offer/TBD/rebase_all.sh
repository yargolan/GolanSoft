#!/bin/bash

CWD=$(pwd)

for repo in $(ls); do

    fullPath="${CWD}/${repo}"

    if [ -d ${fullPath} ]; then

        echo "Rebase repo: ${repo}"

        cd ${fullPath}

        git pull

        echo ""
    fi
done

