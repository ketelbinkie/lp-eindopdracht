$(document).ready(function () {

    $('#check_user').submit(function (e) {
        e.preventDefault();

        var $results = $('#password-check-results'),
            username = $('#txt-username').val(),
            password = $('#txt-password').val();

        var requestURL = 'http://localhost:8080/checkcreds?';


        $.getJSON(requestURL,
            {
                'username': username,
                'password': password
            }
            , function (data) {
                console.log(data);
                if (data !== null) {
                    localStorage.setItem('name', data.person.firstname + " " + data.person.lastname);

                    localStorage.setItem('testObject', JSON.stringify(data));


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
