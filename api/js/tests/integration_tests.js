var request        = require('supertest-as-promised'),
    expect         = require('chai').expect,
    should         = require('chai').should(),
    Bug            = require('../api/bug'),
    BugPayload     = require('../payloads/bug_payload');

describe('Bugzilla API', function(){

    it('returns a 200 when checking for a bug', function(done)
    {
      Bug.getBug(1)
        .expect(200, done);
    });

    it('returns a 200 when creating a bug', function(done)
    {
      var bug_payload = BugPayload.create('TestProduct', 'TestComponent', 'testing', 'unspecified', 'Windows', 'PC');

      Bug.createBug(bug_payload)
         .expect(200, done);
    });

    it('calling bug history throws error, due to invalid method argument', function(done)
    {
        var response;
        try {
            response = Bug.getBugHistory();
        } catch(err) {
            expect(err.name).to.equal('ValidException');
        }

        done();
    });

    it('returns a invalid bug history', function(done)
    {var invalid_object = BugPayload.create('TestProduct', 'TestComponent', 'testing', 'unspecified', 'Windows', 'PC');

       Bug.getBugHistory(invalid_object)
           .expect(400, done);
    });

    it('returns a valid bug history', function(done)
    {
        Bug.getBugHistory(1)
           .expect(200, done);
    });

});
