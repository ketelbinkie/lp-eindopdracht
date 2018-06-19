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
                    var privateName = ID();
                    localStorage.setItem('sessionid',privateName);
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

var ID = function () {
    // Math.random should be unique because of its seeding algorithm.
    // Convert it to base 36 (numbers + letters), and grab the first 9 characters
    // after the decimal.
    return '_' + Math.random().toString(36).substr(2, 9);
};
