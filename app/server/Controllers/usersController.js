var mongoose = require('mongoose'),
    users = mongoose.model('users');

exports.list_all_users = function(req, res){
    users.find({}, function(err, users){
        if (err)
            res.send(err);
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

exports.register_user = function(req, res) {
  if (!req.body.full_name || !req.body.ssn || !req.body.email || !req.body.password ) {
    res.json({success: false, msg: 'Everything must be filled in'});
  } else {
    var newUser = new users({
      full_name: req.body.full_name,
      ssn: req.body.ssn,
      email: req.body.email,
      password: req.body.password
    });
    // save the user
    newUser.save(function(err) {
      if (err) {
        return res.json({success: false, msg: 'Email already exists.'});
      }
      res.json({success: true, msg: 'Successful created new user.'});
    });
  }
};

exports.login_user = function(req, res) {
  users.findOne({
    email: req.body.email
  }, function(err, users) {
    if (err) throw err;

    if (!users) {
      res.send({success: false, msg: 'Login failed. Email not found.'});
    } else {
      // check if password matches
      users.comparePassword(req.body.password, function (err, isMatch) {
        if (isMatch && !err) {
            req.session.email = req.body.email;
          res.json({success: true});
        } else {
          res.send({success: false, msg: 'Login failed. Wrong password.'});
        }
      });
    }
  });
};