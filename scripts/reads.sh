#!/usr/bin/env bash


DEFAULT_PORT=8080

PORT="${1:-$DEFAULT_PORT}"

echo "Firing requests to GET /party/${2} using port ${PORT}..."

while true ; do
  sleep $((RANDOM % 3));
  ID="${2:-"$((RANDOM % 100))"}";
  curl -s -H 'Content-Type: application/json' -XGET "http://localhost:$PORT/party/$ID" ;
  echo;
done