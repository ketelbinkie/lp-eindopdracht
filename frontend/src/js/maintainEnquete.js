$(document).ready(function () {

    $('#Opslaan').click(function (e) {
        e.preventDefault();
        var selectedquestions = [],
            $results = $('#message');

        let enqueteId = $('#sel-enquete').val();
        let enqueteNaam = $('#sel-enquete option:selected').text();

        alert($('#sel-enquete'));
            if(enqueteId!=null) {

                var to = $('#multiselect_to')[0];
                if (to.length > 0) {
                    for (let i = 0; i < to.length; i++) {
                        var opt = to[i];

                        var enquete_question = new Object();

                        var arr = opt.value.split('+');

                        enquete_question.id = arr[0];
                        enquete_question.question = opt.text;
                        enquete_question.category = arr[1];

                        var question_anwertype = new Object();
                        question_anwertype.id = "1";
                        enquete_question.answerType = question_anwertype;

                        selectedquestions.push(enquete_question);
                    }

                    $.ajax({
                        url: "http://localhost:8080/enquete/save",
                        type: "PUT",
                        data: JSON.stringify({"id": enqueteId, "name":enqueteNaam, "questions":selectedquestions}),

                        contentType: "application/json; charset=utf-8",
                        // dataType: "text",
                        success: function (data, textStatus, xhr) {
                            //formulier velden terugzetten naar default-waarde
                            $('#add_questions_to_enquete')[0].reset();
                            $results.empty().append(data);
                        },
                        error: function (data, textStatus, xhr) {
                            // alert(data.responseText);
                            $results.empty().append(data.responseText);

                        }
                    })

                }
                else {
                    $results.empty().append('<p>Geen vraag geselecteerd, selecteer een vraag!</p>');
                }
            }
                else{
                    $results.empty().append('<p>Geen enquête geselecteerd, kies een enquête!</p>');
            }
    })
});

function addEnqueteToDatabase(){

    var enqueteName = $('#txt-enquetename').val();

    $.ajax({
        url: "http://localhost:8080/enquete/add",
        type: "POST",
        data: JSON.stringify({
            name: enqueteName
        }),
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        success: function (data, textStatus, xhr) {
            //formulier velden terugzetten naar default-waarde
            $('#modal_new_enquete')[0].reset();
            $results.empty().append(data);
        },
        error: function (data, textStatus, xhr) {
            // alert(data.responseText);
            $results.empty().append(data.responseText);

        }
    })

}
