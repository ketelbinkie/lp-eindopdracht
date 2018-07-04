function logout() {
    let $results = $('#message');
    $.ajax({
        url: "http://localhost:8080/users/logout",
        type: "GET",
        data: {sessionid: localStorage.getItem("sessionid")},
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (data, textStatus, xhr) {
            localStorage.clear()
            window.open("../src/index.html", "_self")
        },
        error: function (data, textStatus, xhr) {
            // alert(data.responseText);
            $results.html('<p>Error: results not updated</p>');
        }
    })


}
