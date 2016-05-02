## Reactive streams demo

This is an example of reactive app. 

Records are retrieved with [postgres async rx driver](https://github.com/alaisi/postgres-async-driver) and emitted over REST API using [RX Java extensions](https://github.com/ReactiveX/RxJava).

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

Fix rending for React table.