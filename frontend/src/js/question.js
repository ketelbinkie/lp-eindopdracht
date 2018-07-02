$(document).ready(function () {
    $('#Opslaan').click(function (e) {
        e.preventDefault();

        var $results = $('#message'),
            question = $('#txt-question').val(),
            category = $('#txt-category').val();
        let answertype = $('#sel-answertype').val();

        console.log(question + ', ' + category + ', ' + answertype);

        $.ajax({
            url: "http://localhost:8080/question/add",
            type: "POST",
            data: JSON.stringify({question: question, category: category, answerType: {id: answertype}}),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: function (data, textStatus, xhr) {
                //formulier velden terugzetten naar default-waarde
                $('#add_question')[0].reset();
                $results.empty().append(data);
            },
            error: function (data, textStatus, xhr) {
                // alert(data.responseText);
                $results.empty().append(data.responseText);
            }
        })
    })
});

// Get and present available answertypes in field 'Antwoordsoort'
$(document).ready(function () {
    let select = $('#sel-answertype');

    select.append('<option selected disabled>Voer antwoordsoort in</option>');

    $.ajax({
        url: "http://localhost:8080/answertype/all",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {
                select.append($('<option></option>').attr('value', val.id).text(val.answertype + ", " + val.answersubtype));
                console.log("id, "+val.id)
            })
        },
    })
});