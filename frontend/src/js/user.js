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

function addUserToDatabase(){

    var vusername = $('#user-username').val(),
        vpassword =$('#user-password').val(),
        vrole = $('#user-sel-role').val(),
        vfirstname = $('#user-firstname').val(),
        vlastname = $('#user-lastname').val(),
        vdatebirth= $('#user-dateBirth').val(),
        vgender = $('#user-gender').val();





    $.ajax({
        url: "http://localhost:8080/users/add",
        type: "POST",
        data: JSON.stringify({username: vusername, password: vpassword, person: {firstname: vfirstname, lastname:vlastname,dateOfBirth:vdatebirth,ggender:vgender,number:null},role:{role:vrole}}),
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
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

}