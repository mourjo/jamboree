#!/usr/bin/env bash

while true ; do
  sleep $((RANDOM % 3));
  curl -s -H 'Content-Type: application/json' -XPOST http://localhost:8080/party/ -d '{"name": "Adi Dhakeswari", "location": "Kolkata"}';
  echo;
done