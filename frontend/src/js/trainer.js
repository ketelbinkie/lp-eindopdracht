$(document).ready(
    function getPersonListOfTrainer() {
        let selectTeam = $('#sel-team');
        // selectTeam.append('<option selected disabled>Selecteer team</option>');
        $.ajax({
            url: "http://localhost:8080/trainer/getteams",
            type: "GET",
            data: {
                trainerid: localStorage.getItem("trainer_person_id"),
                sessionid: localStorage.getItem("sessionid")
            },
            success: function (data) {

                let firstTeam;
                $.each(data, function (key, val) {
                    if (key === 0) {
                        firstTeam = val.id;
                    }
                    selectTeam.append($('<option></option>').attr('value', val.id).text(val.name));
                    console.log("id, " + val.id)

                });
                getTableToRate(firstTeam, false);
            },
        });
        console.log("no errors, script head");

        $("#sel-team").change(function () {
            getTableToRate($('#sel-team').val(), true)
        });

    });


function getTableToRate(teamid, reload) {

    $.ajax({
        url: 'http://localhost:8080/getpersonsbytrainerid',
        method: 'GET',
        data: {
            trainerid: localStorage.getItem("trainer_person_id"),
            sessionid: localStorage.getItem("sessionid"),
            teamnameid: teamid
        },
        dataType: 'json',
        success: function (data) {

            // $('#table-content').bootstrapTable('removeAll');
            localStorage.setItem("teamperiod", data[0].teamPeriod);
            if (reload === true) {
                $('#table-content').bootstrapTable('load', {data: data});
            } else {
                $('#table-content').bootstrapTable({data: data});
            }
            let ids='';
            let names='';
            $.each(data, function (key, val) {
                ids += val.person.id + ',';
                names += val.person.firstname + ' '+ val.person.lastname + ',';
            });


            localStorage.setItem('idsToBeRated', ids.slice(0, -1));
            localStorage.setItem('namesToBeRated', names.slice(0, -1));


            console.log("no errors");
            var tableRow = $('.table > tbody > tr');
            tableRow.click(function () {
                var rows = this.childNodes;

                localStorage.setItem("toBeRatedId", rows[0].innerHTML)
                localStorage.setItem("toBeRatedVoornaam", rows[1].innerHTML)
                localStorage.setItem("toBeRatedAchternaam", rows[2].innerHTML)
                window.location = 'personrating.html'
            });
        },
        error: function (requestObject, error, errorThrown) {
            console.log("error thrown, add handler to exit gracefully");
        },
        timeout: 3000 //to do: research and develop further in combination with error handling
    });
}