var mongoose = require('mongoose'),
    Activity = mongoose.model('activities');

exports.list_all_activities = function(req, res){
    Activity.find({}, function(err, activity){
        if (err)
            res.send(err);
        res.json(activity);
    });
};

exports.search_activities = function(req, res){
    Activity.find({type: req.body.type}, function(err, activity){
        if (err)
            res.send(err);
        if (!type) {
            res.send({success: false, message: 'Input search criteria'});
        } else {
            activity.matchSearch(req.body.type, function(err, isMatch){
                if (isMatch && !err) {
                    res.json({success: true});
                } else {
                    res.send({success: false, message: 'No matching acitvities'});
                }
            });
        }
    });
};

exports.create_a_activity = function(req, res) {
    if (err) {
        res.json({success: false, message: 'Not logged in, no activity created'})
    } else {
        var new_activity = new Activity({
            title: req.body.title,
            description: req.body.description,
            type: req.body.type,
            date: req.body.date,
            time: req.body.time,
            location: req.body.location,
            min_participants: req.body.min_participants,
            max_participants: req.body.max_participants,
            email: req.body.email
        });
        new_activity.save(function (err, activity) {
            if (err)
                res.send(err)
            res.json(activity, {message: 'Activity successfully created'});
        });
    }
};


exports.read_a_activity = function(req, res){
    Activity.findById(req.params.activityId, function(err, activity){
        if (err)
          res.send(err);
        res.json(activity);
    });
};

exports.update_a_activity = function(req, res){
    Activity.findOneAndUpdate({_id: req.params.activityId}, req.body, {new: true}, function(err, activity){
        if (err)
            res.send(err);
        res.json(activity);
    });
};

exports.delete_a_activity = function(req, res){

    Activity.remove({
        _id: req.params.activityId
    }, function(err, activity) {
        if (err)
            res.send(err);
        res.json({ message: 'Activity successfully deleted' });
    });
};