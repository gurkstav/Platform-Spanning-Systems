module.exports = function(app){
    var activities = require('../controllers/activitiesController');
    var users = require('../controllers/usersController');

    //activities Routes
    app.route('/activities')
        .get(activities.list_all_activities);

    app.route('/create')
        .post(activities.create_a_activity);

    app.route('/search')
        .post(activities.search_activities);

    app.route('/ownActivities')
        .post(activities.search_own_activities);

    app.route('/deleteActivity')
        .delete(activities.delete_a_activity);

    app.route('/updateActivity')
        .delete(activities.update_a_activity);

    // users Routes
    app.route('/users')
        .get(users.list_all_users);

    app.route('/findUser')
        .get(users.find_user);

    app.route('/deleteUser')
        .delete(users.delete_a_user);

    app.route('/updateUser')
        .delete(users.update_a_user);

    app.route('/register')
        .post(users.register_user);

    app.route('/login')
        .post(users.login_user);




};