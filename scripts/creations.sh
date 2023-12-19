#!/usr/bin/env bash


DEFAULT_PORT=8080

PORT="${1:-$DEFAULT_PORT}"

echo "Firing requests to POST /party using port ${PORT}..."

while true ; do
  sleep $((RANDOM % 3));

  year=$((((RANDOM % 100)) + 2023));

  month=$((((RANDOM % 11)) + 1));
  printf -v month_str "%02d" $month;

  day=$((((RANDOM % 26)) + 1));
  printf -v day_str "%02d" $day;

  hour=$(((RANDOM % 23) + 1));
  printf -v hour_str "%02d" $hour;

  minute=$((((RANDOM % 59)) + 1));
  printf -v minute_str "%02d" $minute;

  time_str="$day_str-$month_str-$year $hour_str:$minute_str Asia/Kolkata"

  payload="{\"name\": \"Adi Dhakeswari\", \"time\": \"$time_str\"}";

  curl -s -H 'Content-Type: application/json' -XPOST "http://localhost:${PORT}/party/" -d "$payload";

  echo;
done