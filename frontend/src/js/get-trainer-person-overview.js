$(document).ready(function () {

    $('#check_user').submit(function (e) {
        e.preventDefault();

        var requestURL = 'http://localhost:8080/hello?';


        $.getJSON(requestURL,
            {'trainerid': localStorage.getItem('trainer_person_id')}
            , function (data) {
                console.log(data)
            });

    })

});