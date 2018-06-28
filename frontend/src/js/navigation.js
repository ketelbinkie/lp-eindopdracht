document.write('<nav class="navbar navbar-default">\n' +
    '    <div class="container">\n' +
    '        <div class="navbar-header">\n' +
    '            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '            </button>\n' +
    '            <a class="navbar-brand" href="#">\n' +
    '                <script>\n' +
    '                    var user = localStorage.getItem("name");\n' +
    '                    document.writeln(user);\n' +
    '                </script>\n' +
    '            </a>\n' +
    '        </div>\n' +
    '        <ul class="nav navbar-nav navbar-right">\n' +
    '            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">gebruiker<span\n' +
    '                    class="caret"></span></a>\n' +
    '                <ul class="dropdown-menu">\n' +
    '                    <li><a href="#">opvoeren</a></li>\n' +
    '                    <li><a href="#">koppelen</a></li>\n' +
    '                </ul>\n' +
    '            </li>\n' +
    '            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">enquête<span\n' +
    '                    class="caret"></span></a>\n' +
    '                <ul class="dropdown-menu">\n' +
    '                    <li><a href="#" onclick=window.open("../src/maintainEnquete.html","_self");>samenstellen</a></li>\n' +
    '                    <li><a href="#" onclick=window.open("../src/maintainQuestion.html","_self");>koppelen</a></li>\n' +
    '                </ul>\n' +
    '            </li>\n' +
    '            <li><a href="#">team</a></li>\n' +
    '            <li><a href="#" onclick="logout();">uitloggen</a></li>\n' +
    '        </ul>\n' +
    '    </div>\n' +
    '</nav>'


)