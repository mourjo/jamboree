# jamboree

Structured Logging with Spring

## Start the application

Compile into a jar:

```bash 
mvn clean package spring-boot:repackage 
```

Run the jar:

```
java -jar target/jamboree-0.0.1-SNAPSHOT.jar
```

Or combine the steps and run it directly:

```bash 
mvn spring-boot:run
```

## Using the endpoints

Create a party:
```bash
$ curl -s \
  -H 'Content-Type: application/json' \
  -XPOST http://localhost:8080/party/ \
  -d '{"name": "Traders Assembly", "location": "Gariahat"}' | jq .
{
  "created_at": "Sun, 13 Aug 2023 16:05:18 +0200",
  "location": "Gariahat",
  "name": "Traders Assembly",
  "id": "1"
}
```

Get a party:
```bash
$ curl -s http://localhost:8080/party/1 | jq .
{
  "created_at": "Sun, 13 Aug 2023 16:05:18 +0200",
  "location": "Gariahat",
  "name": "Traders Assembly",
  "id": "1"
}
```

Get a party that has not been created:
```bash
$ curl -s http://localhost:8080/party/2 | jq .
{
  "error": "Not found"
}
```