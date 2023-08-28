#!/usr/bin/env bash


DEFAULT_PORT=8080

PORT="${1:-$DEFAULT_PORT}"

echo "Firing requests to GET / using port ${PORT}..."

while true ; do
  sleep $((RANDOM % 3));
  curl -s -H 'Content-Type: application/json' -XGET "http://localhost:$PORT/" ;
  echo;
done