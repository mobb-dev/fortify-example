var http = require('http');
var url = require('url');


function listener(request, response){
  var eid = url.parse(request.url, true)['query']['eid'];
  if (eid !== undefined){
    response.write('<p>Welcome, ' + eid + '!</p>');
    
  }
}

function listener2(request, response){
  connection.query('SELECT * FROM emp WHERE eid="' + eid + '"', function(err, rows){
    if (!err && rows.length > 0){
      response.write('<p>Welcome, ' + rows[0].name + '!</p>');
    }
  });
}
