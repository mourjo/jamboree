#!/usr/bin/env bash

while true ; do
  sleep $((RANDOM % 3));
  curl -s -H 'Content-Type: application/json' -XGET http://localhost:8080/party/$1 ;
  echo;
done