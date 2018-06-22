$(document).ready(function () {

    $('#multiselect_rightSelected').click(function (e) {
        e.preventDefault();
        var from = $('#multiselect_from')[0];
        var to = $('#multiselect_to');

        for (let i = 0; i < from.length; i++) {
            var opt = from[i];
            if (opt.selected === true) {
                to.append($('<option></option>').attr('value', opt.value).text(opt.innerText));

                opt.remove()
                i--;

            }


        }
    })

});
