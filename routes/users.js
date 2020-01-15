const express = require('express');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const User = require('../models/users');
const router = express.Router();
const auth = require('../auth');


router.post('/signup', (req, res, next) => {
    let password = req.body.password;
    bcrypt.hash(password, 10, (err, hash) => {
        if (err) {
            let err = new Error('Could not hash');
            err.status = 401;
            return next(err);
        }
        User.create({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            phoneNumber: req.body.phoneNumber,
            email: req.body.email,
            password: hash,
            image: req.body.image
        }).then((user) => {
            let token = jwt.sign({ _id: user._id }, process.env.SECRET);
            res.json({ status: "Signup success!", token: token});
        }).catch(next);
    });
});

router.post('/login', (req, res, next) => {
    User.findOne({ email: req.body.email })
        .then((user) => {
            if (user == null) {
                let err = new Error('User not found!');
                err.status = 401;
                return next(err);
            } else {
                bcrypt.compare(req.body.password, user.password)
                    .then((isMatch) => {
                        if (!isMatch) {
                            let err = new Error('Password does not match!');
                            err.status = 401;
                            return next(err);
                        }
                        let token = jwt.sign({ _id: user._id }, process.env.SECRET);
                        res.json({ status: 'Login success!', token: token });
                    }).catch(next);
            }
        }).catch(next);
});

router.get('/images', (req, res, next) => {
    User.find()
    .then((users) =>{
        res.json(user);
    }).catch((err) => next(err)); 
        
});

router.get('/me', auth.verifyUser, (req, res, next) => {
    res.json({ _id: req.user._id, firstName: req.user.firstName, lastName: req.user.lastName, phoneNumber: req.user.phoneNumber, email: req.user.email, image: req.user.image });
});

router.put('/me', auth.verifyUser, (req, res, next) => {
    User.findByIdAndUpdate(req.user._id, { $set: req.body }, { new: true })
        .then((user) => {
            res.json({ _id: req.user._id, firstName: req.user.firstName, lastName: req.user.lastName, phoneNumber: req.user.phoneNumber, email: req.user.email, image: req.user.image });
        }).catch(next);
});

module.exports = router;