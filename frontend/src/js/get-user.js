$(document).ready(function () {

    $('#check_user').submit(function (e) {
        e.preventDefault();

        var $results = $('#password-check-results'),
            name = $('#txt-name').val(),
            password = $('#txt-password').val();

        var requestURL = 'http://localhost:8080/hello?';


        $.getJSON(requestURL,
            {'name': name,}
            , function (data) {
                if (name === data.username.toLowerCase()&&password===data.password) {

                    localStorage.setItem('welcomeName',data.username);
                    // $results.html('<p>Hello ' + data.username + ' your password is correct</p>');
                    window.open("../src/welkom.html","_self")
                } else {
                    $results.html('<p>Sorry your password is incorrect</p>');
                }
            });

    })

});