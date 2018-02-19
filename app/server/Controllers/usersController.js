var mongoose = require('mongoose'),
    Users = mongoose.model('users');

exports.list_all_users = function(req, res){
    Users.find({}, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.create_a_user = function(req, res){
    var new_users = new Users(req.body);
    new_users.save(function(err, users){
        if (err)
            res.send(err)
        res.json(users);
    });
};

exports.read_a_user = function(req, res){
    Users.findById(req.params.userId, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.update_a_user = function(req, res){
    Users.findOneAndUpdate({_id: req.params.userId}, req.body, {new: true}, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.delete_a_user = function(req, res){

    Users.remove({
        _id: req.params.userId
    }, function(err, users) {
        if (err)
            res.send(err);
        res.json({ message: 'User successfully deleted' });
    });
};