#!/bin/bash

if [ $# == 0 ]; then
  read -rp "Enter YouTube URL : " url
else
  url=$1
fi


executable=/usr/local/bin/youtube-dl


${executable} -x -q  \
  -f 251             \
  --continue         \
  --proxy ""         \
  --no-progress      \
  --yes-playlist     \
  --no-cache-dir     \
  --no-overwrites    \
  --abort-on-error   \
  --no-mark-watched  \
  --write-thumbnail  \
  --audio-format mp3 \
  "${url}"


