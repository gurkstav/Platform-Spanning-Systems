var jwt = require('jsonwebtoken'),
    config = require('./config');

function verifyToken(req, res, next) {
    var token = req.headers['x-access-token'];
    if (!token)
        return res.status(403).send({ success: false, message: 'No token provided.' });
    jwt.verify(token, config.secret, function(err, data) {
        if (err)
            return res.status(500).send({ success: false, message: 'Failed to authenticate token.' });
        // if everything good, save to request for use in other routes
        req.userId = data.id;
        next();
    });
}
module.exports = verifyToken;