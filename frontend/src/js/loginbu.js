$(document).ready(function () {

    $('#check_user').submit(function (e) {
        e.preventDefault();

        var $results = $('#password-check-results'),
            username = $('#txt-username').val(),
            password = $('#txt-password').val();

        var requestURL = 'http://localhost:8080/login?';


        $.getJSON(requestURL,
            {
                'username': username,
                'password': password
            }
            , function (data) {
                console.log(data);
                if (data) {
                    localStorage.setItem('welcomeName', username.toString());
                    window.open("../src/welkom.html", "_self")
                } else {
                    $results.html('<p>Sorry your password is incorrect</p>');
                }
            });

    })


})


$(function () {
    $('#profile').on('click', function (e) {
        e.preventDefault();
        $('#a').load("login.html");
    });
});
