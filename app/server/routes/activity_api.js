//dependencies:
var express = require('express');
var router = express.Router();

//get models:
var Activities = require('../models/activities');

//routes:
Activities.methods(['get', 'post', 'put', 'delete']); //put = update
Activities.register(router, '/activities');

//return router:
module.exports = router;