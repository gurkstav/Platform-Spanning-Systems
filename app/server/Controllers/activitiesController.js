var mongoose = require('mongoose'),
    express = require('express'),
    activities = mongoose.model('activities'),
    users = mongoose.model('users'),
    app = express(),
    jwt = require('jsonwebtoken');

exports.list_all_activities = function(req, res){
    activities.find({}, function(err, activity){
        if (err)
            res.send(err);
        res.json(activity);
    });
};

exports.search_activities = function(req, res){
    if(!req.body.type){
        res.json({success: false, message: 'Search criteria must be entered'})
    } else {
        activities.find({
            type: req.body.type
        }, function (err, activity) {
            if (err)
                res.send(err);
            else if (activity) {
                res.json(activity);
            }
        });
    }
};

exports.create_a_activity = function(req, res) {
    if (!req.body.title || !req.body.description || !req.body.type || !req.body.date || !req.body.time || !req.body.location || !req.body.min_participants || !req.body.max_participants || !req.body.token) {
        res.json({success: false, msg: 'Everything must be filled in'});
    } else {
        users.findOne({email: req.body.email}, function (err, users) {
        if (err) throw err;
        if (!users) {
            res.json({success: false, message: 'User not found.'});
        } else if (users) {
            users.auth_user(req.body.token, function (decoded, err) {
                if (!decoded) {
                    res.json({success: false, message: 'Failed to authorize'});
                } else {
                    var new_activity = new activities({
                        title: req.body.title,
                        description: req.body.description,
                        type: req.body.type,
                        date: req.body.date,
                        time: req.body.time,
                        location: req.body.location,
                        min_participants: req.body.min_participants,
                        max_participants: req.body.max_participants,
                        token: req.body.token
                    });
                    new_activity.save(function (err, activity) {
                        if (err)
                            res.send(err);
                        res.json(activity, {message: 'Activity successfully created'});
                    });
                }
            });
        }

    })
}
};


exports.read_a_activity = function(req, res){
    activities.findById(req.params.activityId, function(err, activity){
        if (err)
          res.send(err);
        res.json(activity);
    });
};

exports.update_a_activity = function(req, res){
    activities.findOneAndUpdate({_id: req.params.activityId}, req.body, {new: true}, function(err, activity){
        if (err)
            res.send(err);
        res.json(activity);
    });
};

exports.delete_a_activity = function(req, res){

    activities.remove({
        _id: req.params.activityId
    }, function(err, activity) {
        if (err)
            res.send(err);
        res.json({ message: 'Activity successfully deleted' });
    });
};

