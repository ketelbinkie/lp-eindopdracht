$(document).ready(function () {
    $('#save-rating').click(function (e) {
        e.preventDefault();
        console.log("opslaan is clicked")
        //alert("click")

        var pe = localStorage.getItem("personEnquete")
        var personEnquete = $.parseJSON(pe);
        var responses = personEnquete.responses;

        $('#rating-table > tbody  > tr').each(function () {

            var questionId = this.cells[0].textContent;
            var answer = $('input[name=rating' + this.cells[0].textContent + ']:checked').val();

            console.log("id: " + questionId + ' aantal sterren: ' + answer);

            $.each(responses, function (index, value) {
                if (value.questionId === questionId) {
                    responses[index].answer = answer;
                }
            });
        });

        personEnquete.responses = responses
        console.log(personEnquete)

        restRerviceCall("reponse/add","PUT", personEnquete)

    })

});


function restRerviceCall(url,RequestMethod, body) {
    var $results = $('#rating-update-results');


    $.ajax({
        url: "http://localhost:8080/" + url,
        type: RequestMethod,
        data: JSON.stringify(body),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (data, textStatus, xhr) {
            $results.html('<p>'+data+'</p>');
        },
        error: function (data, textStatus, xhr) {
            // alert(data.responseText);
            $results.html('<p>Error: results not updated</p>');
        }
    })
}


function createTable(data) {
    let questions = data.enquetes.questions;


    let bodyContent = $('#rating-table');
    bodyContent.append('<thead><tr><th>id</th><th>Rating item</th><th>Score</th></tr></thead>');
    bodyContent.append('<tbody><form>');

    //loop through questions
    for (let i = 0; i < questions.length; i++) {
        let questionId = questions[i].id;
        let answer = '';

        $.each(data.responses, function (index, value) {

            if (value.questionId === questionId.toString()) {
                answer = value.answer;
            }
        });

        //create table
        let row = '';
        row += '<tr><td>' + questionId + '</td><td style="text-align: left">' + questions[i].question + '</td>';


        let stars = '<td><div class="rating">';
        for (let j = 0; j < 6; j++) {
            if (j.toString() === "0") {
                stars += '<input type="radio" name="rating' + questionId + '" value="' + j + '" checked /><span id="hide"></span>';
            } else if (answer === j.toString()) {
                stars += '<input type="radio" name="rating' + questionId + '" value="' + j + '" checked /><span></span>';
            } else {
                stars += '<input type="radio" name="rating' + questionId + '" value="' + j + '"/><span></span>';
            }
        }
        row += stars + '</div></td></tr>';

        bodyContent.append(row)
    }
    bodyContent.append('<tbody><form>')

}
