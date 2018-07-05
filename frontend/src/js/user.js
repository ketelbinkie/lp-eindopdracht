function getUserRoles() {
    let select = $('#user-sel-role');

    select.append('<option selected disabled>Voer rol in</option>');

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

        let $results = $('#message'),
            vusername = $('#user-username').val(),
            vpassword = $('#user-password').val(),
            vroleid = $('#user-sel-role').val(),
            vfirstname = $('#user-firstname').val(),
            vlastname = $('#user-lastname').val(),
            vdatebirth = $('#user-dateBirth').val(),
            vgender = $("#user-gender input[type='radio']:checked").val();

        // Check verplichte velden
        let result = checkRequiredFields(vusername, vpassword, vroleid, vlastname, vdatebirth);
        if(result!==""){
            $results.empty().append(result);
            return
        }

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

        $('#check_user')[0].reset();

    })
});


function restPostUser(url, body) {

    let $results = $('#message');
    $.ajax({
        url: "http://localhost:8080/" + url,
        type: "POST",
        data: JSON.stringify(body),
        contentType: "application/json; charset=utf-8",
        async:true,
        success: function(data){
            $results.empty().append(data);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $results.empty().append("Er is iets misgegaan, gebruiker is niet opgeslagen!");

        }
    })
}

function checkRequiredFields(username, password, role_id, lastname, datebirth) {
let result;
    if (username == "") {
        result = "Gebruikersnaam is niet gevuld!";
    }
    if (password == "") {
        result = "Wachtwoord is niet gevuld!";
    }
    if (role_id == null) {
        result = "Rol is niet gevuld!";
    }
    if (lastname == "") {
        result = "Achternaam is niet gevuld!";
    }
    if ( datebirth == "") {
        result = "Geboortedatum is niet gevuld!";
    }

    return result;
}
