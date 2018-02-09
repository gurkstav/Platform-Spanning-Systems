//dependencies:
var restful = require('node-restful');
var mongoose = restful.mongoose;

//Schema:
var usersSchema = new mongoose.Schema({
    full_name: {type: String, required: true},
    ssn: {type: Number, required: true},
    email: {type: String, required: true},
    pwd: {type: String, required: true}
    }

);

//return models:
module.exports = restful.model('users', usersSchema);

