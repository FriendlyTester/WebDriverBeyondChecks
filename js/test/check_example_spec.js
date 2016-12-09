// We are using the chai library for our assertions - http://chaijs.com/
// and a custom page builder so we need to pull those into our script
var chai  = require('chai'),
    page = require('./helpers/page_builder.js');

// Initialise a chai assertion library. We will be using chai
var expect = chai.expect;

// Start the suite of checks
describe('Initial JS unit check', function(){

  // Start our first check
  it('should pull down the site and run assertion', function(done){
    // Pass the url of the webpage we want to access.  We set the domain for the page in the config.json file
    // once build has been run it will callback a window which we can execute the JS in the page and assert upon
    page.build('/enter_bug.cgi?product=TestProduct', function(window){
      // Trigger the JS method that exists on the page (Try it out on console in devtools / firebug)
      window.show_comment_preview();

      // With the JS triggered, we can use jquery in the window to count how many instances of a css selector
      // there are on a page
      var commentClass = window.$('.bz_comment.bz_default_hidden').length

      // With the preview button clicked we expect there to be 0 instances of .bz_comment.bz_default_hidden to exist
      // so we use chai to assert that
      expect(commentClass).to.equal(0);

      done();
    });
  });

  it('should show login form on click of login', function(done) {
    page.buildWithNoAuth('/', function (window) {
      window.show_mini_login_form('_top');

      var loginClass = window.$('#new_account_container_top.bz_default_hidden').length;

      expect(loginClass).to.equal(1);

      done();
    });
  })

  it('expand the detailed bug information after click', function(done) {
    page.buildWithNoAuth('/query.cgi?format=advanced', function (window) {
      var hiddenDetailsSection = window.$('#detailed_information_section.bz_tui_hidden');
      expect(hiddenDetailsSection.length).to.equal(1);
      window.TUI_toggle_class('information_query');
      var hiddenDetailsSection = window.$('#detailed_information_section.bz_tui_hidden');
      expect(hiddenDetailsSection.length).to.equal(0);
      done();
    });
  })

});
