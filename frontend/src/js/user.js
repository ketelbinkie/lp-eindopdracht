function getUserRoles() {
    let select = $('#user-sel-role');

    select.append('<option selected disabled>Voer rol on</option>');

    $.ajax({
        url: "http://localhost:8080/users/roles",
        type: "GET",
        // data: ,
        success: function (data) {
            $.each(data, function (key, val) {
                select.append($('<option></option>').attr('value', val.id).text(val.role));
                console.log("id, " + val.id)
            })
        },
    })
}

$(document).ready(function () {
    $('#user-save').click(function (e) {
        e.preventDefault();

        let vusername = $('#user-username').val(),
            vpassword = $('#user-password').val(),
            vroleid = $('#user-sel-role').val(),
            vfirstname = $('#user-firstname').val(),
            vlastname = $('#user-lastname').val(),
            vdatebirth = $('#user-dateBirth').val(),
            vgender = $("#user-gender input[type='radio']:checked").val();

        let body = {
            username: vusername,
            password: vpassword,
            person: {
                firstname: vfirstname,
                lastname: vlastname,
                dateOfBirth: vdatebirth,
                gender: vgender,
                number: null
            },
            role: {id: vroleid,}
        };
        restPostUser("users/add",body)

    })
});


function restPostUser(url, body) {

    alert(JSON.stringify(body));
    let $results = $('#message');
    $.ajax({
        url: "http://localhost:8080/" + url,
        type: "POST",
        data: JSON.stringify(body),
        contentType: "application/json; charset=utf-8",
        async:true,
        success: function(msg){
            // alert( "Data Saved: " + msg );
   //         $('#check_user').reset();
            $results.empty().append(msg);

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("some error" + errorThrown);
            $results.html('<p>Error: results not updated</p>');

        }
    })
}
//
// $(document).ready(function () {
//
//     $('#user-save').click(function (e) {
//
//         let $results = $('#message');
//
//         $.ajax({
//             url: "http://localhost:8080/users/add",
//             type: "POST",
//             data: JSON.stringify(),
//             contentType: "application/json; charset=utf-8",
//             dataType: "text",
//             success: function (data, textStatus, xhr) {
//                 $results.empty().append(data);
//             },
//             error: function (data, textStatus, xhr) {
//                 // alert(data.responseText);
//                 $results.empty().append(data.responseText);
//             }
//         })
//     })
// });