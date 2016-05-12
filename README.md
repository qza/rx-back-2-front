## Reactive streams demo

This is an example of reactive app. 

Events are retrieved from database with [Postgres async RX driver](https://github.com/alaisi/postgres-async-driver). For each event location is retrieved from external API using [Netty RX http client](https://github.com/ReactiveX/RxNetty), merged and emitted over REST API as json stream.

Frontend with JQuery, Node, Express, React and [Oboe](https://github.com/jimhigson/oboe.js) shows effects of such approach.

Except performance benefits in real environment of low connectivity, perceived performance is also increased.
 
### How to

Postgres database should be up and running as configured in application.properties  

Start the Spring boot backend:

```
gradle bootRun --info
```

Start the Node frontend:

```
node public/app.js
```

Generate some events:

```
curl -X POST http://localhost:8080/rest/v1/events?count=5000
```

Checkout JQuery version on localhost:3000/simple

Checkout React version on localhost:3000/react

### TODO

Warm up stream

Handle failures, retries and back-pressure

Fix rending for React table.

Use timestamp instead of date.