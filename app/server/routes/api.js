//dependencies:
var express = require('express');
var router = express.Router();

//get models:
var Users = require('../models/users');

//routes:
Users.methods(['get', 'post', 'put', 'delete']);
Users.register(router, '/users');

//return router:
module.exports = router;