var request        = require('supertest-as-promised'),
    expect         = require('chai').expect,
    should         = require('chai').should(),
    Bug            = require('../api/bug'),
    BugPayload     = require('../payloads/bug_payload');

describe('Bugzilla API', function(){

    it('returns a 200 when checking for a bug', function(done){
      Bug.getBug(1)
        .expect(200, done);
    });

    it('returns a 200 when creating a bug', function(done){
      var bug_payload = BugPayload.create('TestProduct', 'TestComponent', 'testing', 'unspecified', 'Windows', 'PC');

      Bug.createBug(bug_payload)
        .expect(200, done);
    });

});
