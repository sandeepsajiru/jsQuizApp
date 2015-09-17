var express = require('express');

// Mongoose import

var mongoose = require('mongoose');

// Mongoose connection to MongoDB 

mongoose.connect('mongodb://localhost/quiz', function (error)
 {
    if (error) 
	{
        console.log(error);
	}

 });

// Mongoose Schema definition

var Schema = mongoose.Schema;

var UserSchema = new Schema(
{
    Question: 'string',
     option1: 'string',
     option2: 'string',
     option3: 'string',
     option4: 'string',
     answer: 'string'
 });



// Mongoose Model definition


var Questions = mongoose.model('quizquestion', UserSchema);

// Bootstrap express

var app = express();

// URLS management

app.get('/', function (req, res) 
	{
    
		res.send("<a href='/questions'>Show Questions</a>");

	});


app.get('/questions', function (req, res)
	 {    
		Questions.find({}, function (err, docs) 
		{
			res.json({docs:docs});
		});
});

app.get('/test', function(req, res)
	{
		res.sendFile(__dirname+'/sample.html');
	});

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://locahost:%s", port)

})