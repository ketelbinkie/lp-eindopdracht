$(document).ready(
    function getPersonListOfTrainer() {

        $.ajax({
            url: 'http://localhost:8080/getpersonsbytrainerid',
            method: 'GET',
            data: {
                trainerid: localStorage.getItem("trainer_person_id"),
                sessionid: localStorage.getItem("sessionid")
            },
            dataType: 'json',
            success: function (data) {
                localStorage.setItem("teamperiod", data[0].teamPeriod);
                $('#table-content').bootstrapTable({
                    data: data
                });
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
        console.log("no errors, script head");
});

