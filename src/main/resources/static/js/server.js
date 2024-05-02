const express = require('express');
const app = express();
const bodyParser = require('body-parser');

const applications = [

];

app.use(bodyParser.json());

app.get('/applicationTable', (req, res) => {
    res.json(applicationTable);
});

app.listen(3000, () => {
    console.log('http://localhost:3306');
});