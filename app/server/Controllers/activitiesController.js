var mongoose = require('mongoose'),
    Activity = mongoose.model('activities');

exports.list_all_activities = function(req, res){
    Activity.find({}, function(err, activity){
        if (err)
            res.send(err);
        res.json(activity);
    });
};

exports.create_a_activity = function(req, res) {
    if (!req.session.email) {
        res.json({success: false}) //lägg till message if fail
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
            email: req.session.email
        });
        new_activity.save(function (err, activity) {
            if (err)
                res.send(err)
            res.json(activity);
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