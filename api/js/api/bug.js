request = require('supertest-as-promised')

exports.getBug = function(id){
  return request('http://52.17.197.56:8080')
            .get('/bugzilla/rest/bug/' + id)
}

exports.createBug = function(payload){
  return request('http://52.17.197.56:8080')
           .post('/bugzilla/rest/bug?login=admin@bugzilla.org&password=password')
           .set('Content-type', 'application/json')
           .send(payload)
};
