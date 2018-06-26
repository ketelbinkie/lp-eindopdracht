$(document).ready(function () {

    $('#Opslaan').click(function (e) {
        e.preventDefault();
        var selectedquestions = [],
            $results = $('#message');

        let enqueteId = $('#sel-enquete').val();

            if(enqueteId!=null) {

                var to = $('#multiselect_to')[0];
                if (to.length > 0) {
                    for (let i = 0; i < to.length; i++) {
                        var opt = to[i];

                        var enquete_question = new Object();

                        enquete_question.id = opt.value;
                        enquete_question.question = opt.text;
                        enquete_question.category = "";

                        var question_anwertype = new Object();
                        question_anwertype.id = "1";
                        enquete_question.answerType = question_anwertype;


                        selectedquestions.push(enquete_question);
                        alert(enquete_question);
                        alert(JSON.stringify(selectedquestions));
                    }

                    // var myString = JSON.stringify(selectedquestions);

                    $.ajax({
                        url: "http://localhost:8080/enquete/save",//<Todo> goede url invullen
                        type: "PUT",
                        data: JSON.stringify({"id": enqueteId, "name":"", "questions":selectedquestions}),

                        // data: JSON.stringify({"id": enqueteId, "name":"", "questions":[{
                        //         "id": 6,
                        //         "question":"Dribbelen",
                        //         "category": "",
                        //         "answerType":
                        //             {
                        //                 "id": 2
                        //             }
                        //     }]}),
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
