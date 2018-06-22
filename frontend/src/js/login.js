$(document).ready(function () {

    $('#check_user').submit(function (e) {
        e.preventDefault();

        var $results = $('#message'),
            username = $('#txt-username').val(),
            password = $('#txt-password').val();

        var requestURL = 'http://localhost:8080/checkcreds?';
        var sessionid = ID();

        $.getJSON(requestURL,
            {
                'sessionid': sessionid,
                'username': username,
                'password': password
            }
            , function (data) {
                console.log(data);
                if (data.id!== 0) {

                    localStorage.setItem('sessionid',sessionid);
                    localStorage.setItem('name', data.person.firstname + " " + data.person.lastname);
                    localStorage.setItem('testObject', JSON.stringify(data));
                    localStorage.setItem('trainer_person_id',data.person.id)
                   if(data.role.id ===3){
                       window.open("../src/trainer.html", "_self")
                   }else{
                       window.open("../src/maintainQuestion.html", "_self")

                   }
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
