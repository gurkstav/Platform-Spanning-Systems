'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

//Schema:
var activitiesSchema = new Schema({
    type: {type: String, required: true},
    date: {type: Date, default: Date.now, required: true},
    location: {type: String, required: true},
    min_participants: {type: Number, required: true},
    max_participants: {type: Number}
        //contact: {type: mongoose.Schema.Types.ObjectId, ref: "full_name"}
    }

);

//return models:
module.exports = mongoose.model('activities', activitiesSchema);

