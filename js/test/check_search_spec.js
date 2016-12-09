// We are using the chai library for our assertions - http://chaijs.com/
// and a custom page builder so we need to pull those into our script
var chai = require('chai'),
    page = require('./helpers/page_builder.js');

// Initialise a chai assertion library. We will be using chai
var expect = chai.expect;

// Start the suite of checks
describe('checking search ui', function(){

  it('should show the detailed bug information', function(done)
  {
    page.buildWithNoAuth('/query.cgi?format=advanced', function(window)
    {
      window.TUI_toggle_class('information_query');

      var hiddenDetailedBugInfoElement = window.$('#detailed_information_section.bz_tui_hidden');
      expect(hiddenDetailedBugInfoElement.length).to.equal(0);

      done();
    });
  });
});
