'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

//Schema:
var activitiesSchema = new Schema({
    title: {type: String, required: true},
    description: {type: String, required: true},
    type: {type: String, required: true},
    date: {type: String, required: true},
    time: {type: String, required: true},
    location: {type: String, required: true},
    min_participants: {type: String, required: true},
    max_participants: {type: String, required: true},
    email: {type: String, required: true}
    }

);

//return models:
module.exports = mongoose.model('activities', activitiesSchema);

