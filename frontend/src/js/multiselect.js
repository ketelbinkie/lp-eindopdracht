$(document).ready(function () {

    $('#multiselect_rightAll').click(function (e) {
        e.preventDefault();
        var from = $('#multiselect_from')[0];
        var to = $('#multiselect_to');

        for (let i = 0; i < from.length; i++) {
            var opt = from[i];
                to.append($('<option></option>').attr('value', opt.value).text(opt.innerText));

                opt.remove()
                i--;
            }
    })

    $('#multiselect_rightSelected').click(function (e) {
        e.preventDefault();
        var from = $('#multiselect_from')[0];
        var to = $('#multiselect_to');

        for (let i = 0; i < from.length; i++) {
            var opt = from[i];
            if (opt.selected === true) {
                // var idCategory = opt.value+"/"+opt.category;
                to.append($('<option></option>').attr('value', opt.value).text(opt.innerText));

                opt.remove()
                i--;
            }
        }
    })

    $('#multiselect_leftSelected').click(function (e) {
        e.preventDefault();
        var from = $('#multiselect_from');
        var to = $('#multiselect_to')[0];

        for (let i = 0; i < to.length; i++) {
            var opt = to[i];
            if (opt.selected === true) {
                from.append($('<option></option>').attr('value', opt.value).text(opt.innerText));

                opt.remove()
                i--;
            }
        }
    })

    $('#multiselect_leftAll').click(function (e) {
        e.preventDefault();
        var from = $('#multiselect_from');
        var to = $('#multiselect_to')[0];

        for (let i = 0; i < to.length; i++) {
            var opt = to[i];
            from.append($('<option></option>').attr('value', opt.value).text(opt.innerText));

            opt.remove()
            i--;
        }
    })
});
