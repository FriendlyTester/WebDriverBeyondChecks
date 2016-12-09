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


  it('should show search by people as closed by default', function(done){
        page.build('/query.cgi?format=advanced', function(window){
            var hiddenDetailSection = window.$('#people_filter_section.bz_tui_hidden').length
            var visibleDetailSection = window.$('#people_filter_section.bz_tui').length

            expect(hiddenDetailSection).to.equal(1);
            expect(visibleDetailSection).to.equal(0);

        done();

    })
  });

    it('should show search by people if its clicked', function(done){
          page.build('/query.cgi?format=advanced', function(window){
               window.TUI_toggle_class('people_query');
            var hiddenDetailSection = window.$('#people_filter_section.bz_tui_hidden').length
            var visibleDetailSection = window.$('#people_filter_section.bz_tui').length

            expect(hiddenDetailSection).to.equal(0);
            expect(visibleDetailSection).to.equal(1);

          done();

      })
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

  it('should expand search by change history', function(done) {
    page.buildWithNoAuth('/query.cgi?format=advanced', function(window){

    window.TUI_toggle_class('history_query');

    var historyBlock = window.$('.bug_changes bz_search_section history_query bz_tui_hidden').length

    expect(historyBlock).to.equal(0);
    done();
    });
  });
  it('should show login form on click of login', function(done) {
        page.build('/', function(window){
            window.show_mini_login_form('_top');
            var loginClass = window.$('#mini_login_container_top #new_account_container_top.bz_default_hidden').length

            expect(loginClass).to.equal(0);
            done();
        })
  });



  it('adds row to custom search', function(done) {
        page.build('query.cgi?no_redirect=1&query_format=advanced&short_desc_type=allwordssubstr&short_desc=&longdesc_type=allwordssubstr&longdesc=&bug_file_loc_type=allwordssubstr&bug_file_loc=&deadlinefrom=&deadlineto=&bug_id=&bug_id_type=anyexact&emailtype1=substring&email1=&emailtype2=substring&email2=&emailtype3=substring&email3=&chfieldvalue=&chfieldfrom=&chfieldto=Now&j_top=AND&f1=noop&o1=noop&v1=&v2=&cmdtype=doit&order=Importance', function(window){
            var rowsBefore = window.$('.custom_search_condition').length
            window.TUI_toggle_class('custom_search_query');
            try {
                window.custom_search_new_row();
            } catch(err) {
                //TODO: fix it later
            }
            var rowsAfter = window.$('.custom_search_condition').length

            expect(rowsAfter).to.equal(rowsBefore+1);
            done();
        })
  });

    it('custom search can be expanded', function(done) {
          page.build('query.cgi?no_redirect=1&query_format=advanced&short_desc_type=allwordssubstr&short_desc=&longdesc_type=allwordssubstr&longdesc=&bug_file_loc_type=allwordssubstr&bug_file_loc=&deadlinefrom=&deadlineto=&bug_id=&bug_id_type=anyexact&emailtype1=substring&email1=&emailtype2=substring&email2=&emailtype3=substring&email3=&chfieldvalue=&chfieldfrom=&chfieldto=Now&j_top=AND&f1=noop&o1=noop&v1=&v2=&cmdtype=doit&order=Importance', function(window){
              var before = window.$('#custom_search_filter_section.bz_tui_hidden').length
              expect(before).to.equal(1);
              window.TUI_toggle_class('custom_search_query');
              var after = window.$('#custom_search_filter_section.bz_tui_hidden').length
              expect(after).to.equal(0);
              done();
          })
    });

});
