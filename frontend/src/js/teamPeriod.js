$(document).ready(function () {
    $('#Opslaan').click(function (e) {
        e.preventDefault();

        var $results = $('#message'),
            season = $('#sel-season option:selected').text(),
            periode = $('#sel-period option:selected').text(),
            startDate = $('#date-start'),
            endDate = $('#date-end');
        let teamId = $('#sel-team').val(),
            name = $('#sel-team option:selected').text(),
            allTeams = $('#sel-all-teams').prop("checked");


        // Check if Einddatum is before Startdatum
        if (startDate["0"].value > endDate["0"].value){
            $results.empty().append("Startdatum moet voor einddatum liggen!");
        }

        var teamPeriod = new Object();

        teamPeriod.season = season;
        teamPeriod.periodName = periode;
        teamPeriod.startdate = startDate["0"].value;
        teamPeriod.enddate = endDate["0"].value;

        // Fill team name
        var teamName = new Object();

        // if = Checkbox 'allteams' is checked
        // else = Checkbox 'allteams' is unchecked
        if(allTeams){
            teamName.id = 99999;
            teamName.name = "allteams";
        }
        else {
            teamName.id = teamId;
            teamName.name = name
        }

        // Add teamName to team period
        teamPeriod.teamName = teamName;


        $.ajax({
            url: "http://localhost:8080/teamperiod/add",
            type: "POST",
            data: JSON.stringify(teamPeriod),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: function (data, textStatus, xhr) {
                //reset form to default-values
                $('#add_teamPeriod')[0].reset();
                $results.empty().append(data);
            },
            error: function (data, textStatus, xhr) {
                // alert(data.responseText);
                $results.empty().append(data.responseText);
            }
        })
    })

    // Get and present available teamnames in field 'Team'
    let select = $('#sel-team');

    select.append('<option selected disabled>Selecteer team</option>');

    $.ajax({
        url: "http://localhost:8080/teamname/all",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {
                select.append($('<option></option>').attr('value', val.id).text(val.name));
                console.log("id, "+val.id)
            })
        },
    })
});

// enable/disable field 'sel-team' by clicking checkbox 'sel-all-teams'
$(function(){
    $("#sel-all-teams").click(function(){
        if($(this).is(":checked")) {
            $("#sel-team").attr("disabled", "disabled");
        } else {
            $("#sel-team").removeAttr("disabled");
            $("#sel-team").focus();
        }
    });
});
