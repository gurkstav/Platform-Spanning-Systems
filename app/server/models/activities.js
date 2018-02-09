//dependencies:
var restful = require('node-restful');
var mongoose = restful.mongoose;

//Schema:
var activitiesSchema = new mongoose.Schema({
        type: {type: String, required: true},
        date: {type: Number, required: true},
        location: {type: String, required: true},
        min_participants: {type: Number, required: true},
        max_participants: {type: Number}
        //contact: {type: mongoose.Schema.Types.ObjectId, ref: "full_name"}
    }

);

//return models:
module.exports = restful.model('activities', activitiesSchema);

