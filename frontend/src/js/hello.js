$(document).ready(function() {

$('#check_user').submit(function (e) {
    e.preventDefault();

    var $results = $('#password-check-results'),
        name = $('#txt-name').val(),
        password = $('#txt-password').val();

    var requestURL = 'http://localhost:8081/hello?';


    //collect data
    $.getJSON(requestURL,
        { 'name' : name,}
        , function(data) {
        console.log(data);
    });

    $results.html('password will be checked for ' + name)






})



});