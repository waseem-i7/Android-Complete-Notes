var express = require('express');
var app = express();
var fs = require("fs");

var bodyParser = require('body-parser')
app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({
    extended: true
}));



// A promo message to user 
var message = "Promo msg coming  from another server";

app.get('/messages', function (req, res) {
    res.end(JSON.stringify(message));
})


// Home Page 
app.get('/', (req, res) => res.send('Welcome to the next world'))

// Configure server 
var server = app.listen(7000, '192.168.43.115', function (req, res) {

    var host = server.address().address
    var port = server.address().port

    console.log(`Server running at http://${host}:${port}/`);
})

