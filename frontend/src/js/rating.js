$(document).ready(function () {
    $('#save-rating').click(function (e) {
        e.preventDefault();
        console.log("opslaan is clicked")
        //alert("click")

        let pe = localStorage.getItem("personEnquete")
        let personEnquete = $.parseJSON(pe);
        let responses = personEnquete.responses;
        let responsesNew = [];
        let screenResponse = {};


        $('#rating-table > tbody  > tr').each(function () {

            var questionId = this.cells[0].textContent;
            var answer = $('input[name=rating' + this.cells[0].textContent + ']:checked').val();

            console.log("id: " + questionId + ' aantal sterren: ' + answer);

            if (responses.length === 0) {

                let enqueteId = personEnquete.enquetes.id;
                responsesNew.push({
                    questionId: questionId,
                    enqueteId: enqueteId,
                    answer: answer
                });

                console.log(JSON.stringify(responsesNew));

            } else {
                $.each(responses, function (index, value) {
                    if (value.questionId === questionId) {
                        responses[index].answer = answer;
                    }
                });
            }
        });

        if (responsesNew.length === 0) {
            personEnquete.responses = responses;
        } else {
            // responses.push(responsesNew);
            responses = responsesNew;
            personEnquete.responses = responses;

        }

        console.log(JSON.stringify(personEnquete));

        restUpdateRating("reponse/add", personEnquete)
    })

    $('#Terug').click(function (e) {
        e.preventDefault();
        window.open("../src/trainer.html", "_self")
    })

});

function restUpdateRating(url, body) {
    let $results = $('#message');
    $.ajax({
        url: "http://localhost:8080/" + url,
        type: "PUT",
        data: JSON.stringify(body),
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (data, textStatus, xhr) {
            $results.html('<p>' + data + '</p>');
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
    bodyContent.empty().append('<thead><tr><th>id</th><th>Rating item</th><th>Score</th></tr></thead>');
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


function navigate(direction) {
    let ids = localStorage.getItem('idsToBeRated').split(',');
    let names = localStorage.getItem('namesToBeRated').split(',');
    let nextid = 0;
    let nextname = ''
    $.each(ids, function (i) {
        if (localStorage.getItem('toBeRatedId') === ids[i]) {
            if (direction === 'prev') {
                nextid = ids[i - 1];
                nextname = names[i - 1]
            } else {
                nextid = ids[i + 1];
                nextname = names[i + 1];
            }
            if (direction === 'prev' && i - 1 === 0) {
                $('#nav-prev').prop('disabled', true);
            } else {
                $('#nav-prev').prop('disabled', false);
            }
            if (direction === 'next' && i + 1 === ids.length - 1) {
                $('#nav-next').prop('disabled', true);
            } else {
                $('#nav-next').prop('disabled', false);
            }
            if (direction === "none" && i === 0) {
                $('#nav-prev').prop('disabled', true);
            } else if (direction === "none" && i === ids.length - 1) {
                $('#nav-next').prop('disabled', true);

            }


        }
    });

    if (direction !== 'none') {
        localStorage.setItem('toBeRatedId', nextid);
        localStorage.setItem("toBeRatedVoornaam", nextname.split(' ')[0]);
        localStorage.setItem("toBeRatedAchternaam", nextname.replace(localStorage.getItem("toBeRatedVoornaam")+ ' ',''));
    }

    createRatingTable();
}


function createRatingTable() {

    let $results = $('#message');
    $.ajax({
        url: 'http://localhost:8080/person/personenquete',
        method: 'GET',
        data: {
            personId: localStorage.getItem("toBeRatedId")
        },
        dataType: 'json',
        success: function (data) {
            localStorage.setItem('personEnquete', JSON.stringify(data));
            createTable(data)
            $('#ratingName').empty().append(localStorage.getItem('toBeRatedVoornaam') + ' ' + localStorage.getItem('toBeRatedAchternaam'));
        },
        error: function (requestObject, error, errorThrown) {
            console.log("error thrown, add handler to exit gracefully");
            $('#ratingName').empty();
            $('#rating-table').empty();
        },
        timeout: 3000 //to do: research and develop further in combination with error handling
    });
}
