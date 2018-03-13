'use strict';
var mongoose = require('mongoose'),
    bcrypt = require('bcrypt'),
    Schema = mongoose.Schema;



//Schema:
var usersSchema = new Schema({
    full_name: {type: String, required: true},
    ssn: {type: String, required: true},
    email: {type: String, unique: true, required: true, lowercase: true},
    password: {type: String, required: true}
    }

);
usersSchema.pre('save', function (next) {
    var users = this;
    if (this.isModified('password') || this.isNew) {
        bcrypt.genSalt(10, function (err, salt) {
            if (err) {
                return next(err);
            }
            bcrypt.hash(users.password, salt, function (err, hash) {
                if (err) {
                    return next(err);
                }
                users.password = hash;
                next();
            });
        });
    } else {
        return next();
    }
});

usersSchema.methods.comparePassword = function (pwd, res) {
    bcrypt.compare(pwd, this.password, function (isMatch, err) {
        if (err) {
             return res(err);
        }
        res(null, isMatch);
    });
};

//return models:
module.exports = mongoose.model('users', usersSchema);

