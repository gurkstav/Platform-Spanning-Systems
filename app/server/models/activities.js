'use strict';
var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

//Schema:
var activitiesSchema = new Schema({
    title: {type: String, required: true, lowercase: true},
    description: {type: String, required: true, lowercase: true},
    type: {type: String, required: true},
    date: {type: String, required: true, lowercase: true},
    time: {type: String, required: true, lowercase: true},
    location: {type: String, required: true, lowercase: true},
    min_participants: {type: String, required: true, lowercase: true},
    max_participants: {type: String, required: true, lowercase: true},
    email: {type: String, requires: true, lowercase: true}
    });


//return models:
module.exports = mongoose.model('activities', activitiesSchema);

