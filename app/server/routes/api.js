//dependencies:
var express = require('express');
var router = express.Router();
var router1 = express.Router();

//get models:
var Users = require('../models/users');
var Activities = require('../models/activities');

//routes:
Users.methods(['get', 'post', 'put', 'delete']);
Users.register(router, '/users');
Activities.methods(['get', 'post', 'put', 'delete']);
Activities.register(router1, '/activities');

//return router:
module.exports = router;
module.exports = router1;