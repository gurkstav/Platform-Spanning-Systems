//dependencies:
var restful = require('node-restful');
var mongoose = restful.mongoose;

//Schema:
var usersSchema = new mongoose.Schema({
    full_name: str.split(" ", 1),
    ssn: Number.min(8).max(8),
    email: String,
    pwd: String
});

//return models:
module.exports = restful.model('users', usersSchema);

