var mongoose = require('mongoose'),
    users = mongoose.model('users');

exports.list_all_users = function(req, res){
    users.find({}, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.create_a_user = function(req, res){
    var new_users = new users(req.body);
    new_users.save(function(err, users){
        if (err)
            res.send(err)
        res.json(users);
    });
};

exports.read_a_user = function(req, res){
    users.findById(req.params.userId, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.update_a_user = function(req, res){
    users.findOneAndUpdate({_id: req.params.userId}, req.body, {new: true}, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.delete_a_user = function(req, res){

    users.remove({
        _id: req.params.userId
    }, function(err, users) {
        if (err)
            res.send(err);
        res.json({ message: 'User successfully deleted' });
    });
};