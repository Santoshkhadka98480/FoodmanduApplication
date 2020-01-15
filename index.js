const express = require("express");
const mongoose = require("mongoose");
const env = require('dotenv').config();
const morgan = require('morgan');
const userRouter = require('./routes/users');
const uploadRouter = require('./routes/upload');
const auth = require('./auth');
const cors = require('cors');


mongoose.connect(process.env.db, {useNewUrlParser: true, useUnifiedTopology: true, 
    useCreateIndex: true})
        .then((db) =>{
            console.log("Successfully connected to Mongodb server");
        }, (err) => console.log(err));


const app = express();
app.use(morgan('tiny'));
app.use(express.json());
app.options(+'*', cors());
app.use(express.urlencoded({extended: true }));

app.use(express.static(__dirname + "/public"));

app.use('/users', userRouter);
app.use('/upload', uploadRouter);
app.use(auth.verifyUser);

app.use((err, req, res, next) => {
    console.error(err.stack);
    res.statusCode = 500;
    res.json({ status: err.message });
})

app.listen(process.env.PORT, () =>{
    console.log(`App is runnig at localhost:${process.env.PORT}`);
});