$(document).ready(function () {

    $('#delete_user').submit(function (e) {
        e.preventDefault();

        var $results = $('#password-check-results'),
            name = $('#txt-name').val(),
            id;

        var requestURL = 'http://localhost:8080//users/{id}';


        $.getJSON(requestURL,
            {'name': name,}
            , function (data) {
                if (name === data.username.toLowerCase()) {
                    id = data.id;

                // hier een nieuwe functie aanroepen voor het verwijderen van een user
                //
                //     $results.html("<p>De gebuiker '" + name +"' is verwijderd.</p>");
                // } else {
                //     $results.html("<p>De gebruiker '"+ name +"'kan niet verwijderd worden.</p>");
                // }

                }
                else
                {
                    $results.html("<p>De gebruiker met de naam:'"+ name +"'is niet gevonden.</p>");
                }

            });

    })


});