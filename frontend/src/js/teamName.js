$(document).ready(function () {

    $('#Opslaan').click(function (e) {
        e.preventDefault();

        var $results = $('#message');
        let teamNaam = $('#txt_team_name').val();

        console.log("teamnaam "+teamNaam);
            if(teamNaam!=("")) {

                    $.ajax({
                        url: "http://localhost:8080/teamname/add",
                        type: "POST",
                        data: JSON.stringify({"name":teamNaam}),

                        contentType: "application/json; charset=utf-8",
                        // dataType: "text",
                        success: function (data, textStatus, xhr) {
                            //formulier velden terugzetten naar default-waarde
                            $('#add_team_name')[0].reset();
                            $results.empty().append(data);
                        },
                        error: function (data, textStatus, xhr) {
                            // alert(data.responseText);
                            $results.empty().append(data.responseText);

                        }
                    })

                }
                else {
                    $results.empty().append('<p>Geen teamnaam ingevuld!</p>');
                }
            })
    });
