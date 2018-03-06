module.exports = function(app){
    var activities = require('../controllers/activitiesController');
    var users = require('../controllers/usersController');

    //activities Routes
    app.route('/activities')
        .get(activities.list_all_activities)
        .post(activities.create_a_activity);

    app.route('/activities/:activityId')
        .get(activities.read_a_activity)
        .put(activities.update_a_activity)
        .delete(activities.delete_a_activity);

    app.route('/create')
        .post(activities.create_a_activity);

    // users Routes
    app.route('/users')
        .get(users.list_all_users);

    app.route('/users/:userId')
        .get(users.read_a_user)
        .put(users.update_a_user)
        .delete(users.delete_a_user);

    app.route('/register')
        .post(users.register_user);

    app.route('/login')
        .post(users.login_user);



};