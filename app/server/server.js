//dependencies
var express = require('express'),
    app = express(),
    port = process.env.PORT || 8000,
    mongoose = require('mongoose'),
    morgan = require('morgan'),
    activities = require('./models/activities'),
    users = require('./models/users'),
    config = require('./config'),
    bodyParser = require('body-parser');

//connect to mongoDB:
mongoose.Promise = global.Promise;
mongoose.connect(config.database);
app.set('superSecret', config.secret);

//express:
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.use(morgan('dev'));

//routes:
var routes = require('./routes/api');
routes(app);

//start server:
app.listen(port);
console.log('Server is running on port:' + port);

//404-message when using wrong Url
app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});