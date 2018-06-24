$(document).ready(function () {
    $('#save-rating').click(function (e) {
        e.preventDefault();
        console.log("opslaan is clicked")
      //alert("click")

        $('#rating-table > tbody  > tr').each(function() {
            console.log("id: " + this.cells[0].textContent +' aantal sterren: ' + $('input[name=rating'+this.cells[0].textContent+']:checked').val());

        });


    })

});

function createTable(data) {
    let questions = data;
    let bodyContent = $('#rating-table');
    bodyContent.append('<thead><tr><th>id</th><th>Rating item</th><th>Score</th></tr></thead>');
    bodyContent.append('<tbody><form>');

    for (let i = 0; i < questions.length; i++) {
        let questionId = questions[i].questionId;
        let answer = questions[i].answer;


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
