#!/usr/bin/env bash
trap "exit" INT TERM
trap "kill 0" EXIT

echo "Started requests - Ctrl-C to exit"

DEFAULT_PORT=8080
PORT="${1:-$DEFAULT_PORT}"

scripts/creations.sh "$PORT" &
sleep 0.5
scripts/reads.sh "$PORT" 1 &
sleep 0.25
scripts/reads.sh "$PORT" 2 &
sleep 0.33
scripts/reads.sh "$PORT" &

wait;