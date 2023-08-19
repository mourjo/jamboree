#!/usr/bin/env bash
trap "exit" INT TERM
trap "kill 0" EXIT

echo "Started requests - Ctrl-C to exit"


scripts/creations.sh &
sleep 0.5
scripts/reads.sh 1 &
sleep 0.25
scripts/reads.sh 2 &

wait;