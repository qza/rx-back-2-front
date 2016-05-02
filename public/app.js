var express = require('express');

var app = express();

app.use(express.static(__dirname));

app.listen(3000, function () {
  console.log('Listening on port 3000');
});

app.get('/simple', function (req, res) {
  res.sendFile(__dirname + '/index-simple.html');
});

app.get('/react', function (req, res) {
  res.sendFile(__dirname + '/index-react.html');
});