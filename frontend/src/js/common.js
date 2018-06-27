function logout() {
    let url = "/logout?sessionid=" + localStorage.getItem("sessionid");
    restfullGetServiceCall(url)
    window.open("../src/login.html","_self")

}


function restfullGetServiceCall(url) {

    $.ajax({
        url: "http://localhost:8080" + url,
        type: "GET",
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