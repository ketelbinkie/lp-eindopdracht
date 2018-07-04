$(document).ready(function () {
    $('#Opslaan').click(function (e) {
        e.preventDefault();
        let personArr = [];
        let enquete = $('#sel-enquete').val();
        let teamName = $('#sel-team').val();


        // loop through persons
        $('#multiselect_pers > option:selected').each(function () {
            let personPeriod = {
                enquetes: {"id": enquete},
                personPeriod: {
                    startdate: null,
                    enddate: null,
                    person: {id: ($(this).val())},
                    teamName: {id: teamName},
                    role: {id: 4}
                }
            };
            postPersonEnquete(personPeriod);
        });

        let trainerPeriod = {
            enquetes: {"id": enquete},
            personPeriod: {
                startdate: null,
                enddate: null,
                person: {id: $('#sel-trainer').val()},
                teamName: {id: teamName},
                role: {id: 3}
            }
        };
        postPersonEnquete(trainerPeriod);

    })
});

function postPersonEnquete(body) {
    let  $results = $('#message');


    $.ajax({
        url: "http://localhost:8080/personenquete/add",
        type: "POST",
        data: JSON.stringify(body),
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        success: function (data, textStatus, xhr) {
            $results.empty().append(data);
        },
        error: function (data, textStatus, xhr) {
            $results.empty().append(data.responseText);

        }
    })
}

$(document).ready(function () {
    let select = $('#multiselect_pers');

    $.ajax({
        url: "http://localhost:8080/person/all",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {

                select.append($('<option></option>').attr('value', val.id).text(val.firstname + " " + val.lastname + " " + dateFormat(val.dateOfBirth, "dd-mm-yyyy")));
            })
        }
    })
});

$(document).ready(function () {
    $("#filter-age").change(function () {
        $("#multiselect_pers option[value]").remove();
        let selectPersoon = $('#multiselect_pers');
        let age = $("#filter-age").val()
        $.ajax({
            url: "http://localhost:8080/person/all",
            type: "GET",
            data: {
                age: age
            },
            success: function (data) {
                $.each(data, function (key, val) {

                    selectPersoon.append($('<option></option>').attr('value', val.id).text(val.firstname + " " + val.lastname + " " + dateFormat(val.dateOfBirth, "dd-mm-yyyy")));
                })
            }
        });

    });


});

// Get and present available teamnames in field 'Team'
$(document).ready(function () {
    let filterTeam = $('#filter-team');
    let selectTeam = $('#sel-team');

    filterTeam.append('<option selected disabled>Selecteer team</option>');
    selectTeam.append('<option selected disabled>Selecteer team</option>');
    $.ajax({
        url: "http://localhost:8080/teamname/all",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {
                filterTeam.append($('<option></option>').attr('value', val.id).text(val.name));
                selectTeam.append($('<option></option>').attr('value', val.id).text(val.name));
                console.log("id, " + val.id)
            })
        },
    })
    let selectEnquete = $('#sel-enquete');

    selectEnquete.append('<option selected disabled>Selecteer enquête</option>');
    $.ajax({
        url: "http://localhost:8080/enquete/all",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {
                selectEnquete.append($('<option></option>').attr('value', val.id).text(val.name));
                console.log("id, " + val.id)
            })
        },
    })

    let trainers = $('#sel-trainer');
    trainers.append('<option selected disabled>Selecteer trainer</option>');
    $.ajax({
        url: "http://localhost:8080/trainer/all",
        type: "GET",
        // data
        success: function (data) {
            $.each(data, function (key, val) {
                trainers.append($('<option></option>').attr('value', val.id).text(val.firstname + " " + val.lastname));
                console.log("id, " + val.id)
            })
        },
    })


});

$(document).ready(function () {
    $("#filter-team").change(function () {
        $("#multiselect_pers option[value]").remove();
        let selectEnquete = $('#multiselect_pers');
        let teamName = $("#filter-team option:selected").text()
        $.ajax({
            url: "http://localhost:8080/person/all",
            type: "GET",
            data: {
                currentTeam: teamName
            },
            success: function (data) {
                $.each(data, function (key, val) {

                    selectEnquete.append($('<option></option>').attr('value', val.id).text(val.firstname + " " + val.lastname + " " + dateFormat(val.dateOfBirth, "dd-mm-yyyy")));
                })
            }
        });

    });
});