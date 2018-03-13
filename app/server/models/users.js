'use strict';
var mongoose = require('mongoose'),
    express = require('express'),
    bcrypt = require('bcrypt'),
    Schema = mongoose.Schema,
    app = express(),
    jwt = require('jsonwebtoken');



//Schema:
var usersSchema = new Schema({
    full_name: {type: String, required: true},
    ssn: {type: String, required: true},
    email: {type: String, unique: true, required: true},
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

usersSchema.methods.auth_user = function(token, res){
    //var token = req.body.token;

    if (token){
        jwt.verify(token, app.get('superSecret'), function(decoded, err){
            if (err) {
                return res.json({success: false, message: 'Failed to authenticate'});
            } else {
                res(decoded);
            }
        });
    } else {
        return res.status(403).send({
            success: false,
            message: 'No token provided'
        });
    }
};
//return models:
module.exports = mongoose.model('users', usersSchema);

