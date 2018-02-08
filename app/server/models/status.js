//dependencies:
var restful = require('node-restful');
var mongoose = restful.mongoose;

//Schema:
var statusSchema = new mongoose.Schema({
    first_name: String,
    last_name: String,
    age: Number,
    email: String
});

//return models:
module.exports = restful.model('tblstatus', statusSchema);

