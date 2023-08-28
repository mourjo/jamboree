DEFAULT_PORT=8080

PORT="${1:-$DEFAULT_PORT}"

echo "Firing requests to POST /party using port ${PORT}..."

while true ; do
  sleep $((RANDOM % 10));

  payload="{\"name\": \"Adi Dhakeswari\", \"location\": \"Kolkata\", \"time\": \"2020-34-55 20:20 Asia/Kolkata\"}";

  curl -s -H 'Content-Type: application/json' -XPOST "http://localhost:${PORT}/party/" -d "$payload";

  sleep 1;

  payload="{\"name\": \"Adi Dhakeswari\", \"location\": \"Kolkata\"}";

    curl -s -H 'Content-Type: application/json' -XPOST "http://localhost:${PORT}/party/" -d "$payload";

  sleep 1;

  curl -s -H 'Content-Type: application/json' -XPOST "http://localhost:${PORT}/wonky/" ;

  echo;
done