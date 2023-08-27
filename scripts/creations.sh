#!/usr/bin/env bash


DEFAULT_PORT=8080

PORT="${1:-$DEFAULT_PORT}"

echo "Firing requests to POST /party using port ${PORT}..."

while true ; do
  sleep $((RANDOM % 3));
  curl -s -H 'Content-Type: application/json' -XPOST "http://localhost:${PORT}/party/" -d '{"name": "Adi Dhakeswari", "location": "Kolkata"}';
  echo;
done