'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

//Schema:
var usersSchema = new Schema({
    full_name: {type: String, required: true},
    ssn: {type: Number, required: true},
    email: {type: String, required: true},
    pwd: {type: String, required: true}
    }

);

//return models:
module.exports = mongoose.model('users', usersSchema);

