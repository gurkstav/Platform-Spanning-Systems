var mongoose = require('mongoose'),
    users = mongoose.model('users');

exports.list_all_users = function(req, res){
    users.find({}, function(err, users){
        if (err)
            res.send(err);
        res.json(users);
    });
};

exports.find_user = function(req, res){
   users.findOne({email: req.body.email}, function(err, user) {
       if (err)
           res.send(err);
       if (!user) {
           res.json({success: false, message: 'Authentication failed. User not found'});
       } else if (users) {
           res.json({success: true, message: 'User found', user});
       }
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
    users.remove({_id: req.params.userId}, function(err, users) {
        if (err)
            res.send(err);
        res.json({ message: 'User successfully deleted'});
    });
};

exports.register_user = function(req, res) {
  if (!req.body.full_name || !req.body.ssn || !req.body.email || !req.body.password ) {
    res.json({success: false, message: 'Everything must be filled in'});
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
        return res.json({success: false, message: 'Email already exists.'});
      }
      res.json({success: true, message: 'Successful created new user.'});
    });
  }
};

exports.login_user = function(req, res) {
  users.findOne({email: req.body.email}, function(err, users) {
      if (err) throw err;
      if (!users) {
          res.json({success: false, message: 'Login failed. Email not found.'});
      } else if (users) {
          // check if password matches
          users.comparePassword(req.body.password, function (isMatch) {
              if (!isMatch) {
                  res.json({success: false, message: 'Login failed. Wrong password.'});
              } else {
                  res.json({success: true, message: 'Login complete'});
              }
          });
      }

   });
};
