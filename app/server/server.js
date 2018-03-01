//dependencies
var express = require('express'),
    app = express(),
    port = process.env.PORT || 8000,
    mongoose = require('mongoose'),
    activities = require('./models/activities'),
    users = require('./models/users'),
    bodyParser = require('body-parser');

//connect to mongoDB:
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://Matilda:playdate123@ds123658.mlab.com:23658/playdate');

//express:
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

//routes:
var routes = require('./routes/api');
routes(app);
//app.use('/activity_api', require('./routes/activity_api'), Activities);

//start server:
app.listen(port);
console.log('Server is running on port:' + port);

//404-message when using wrong Url
app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});