/**
 * Created by Brian on 2/23/17.
 */
(function () {
    var request = $.ajax({
        url: '/gallery.json'
    });

    request.done(function (project) {  // the HTTP response -> an array of JSON objects -> ads
        var i, html = '';

        for (i = 0; i < project.length; i++) {
            html += '<div><h2>'
                + project[i].title + '</h2>'
                + '<img src="/uploads/' + project[i].img_url + '" alt="No image"/>'
                + '</div>';
        }

        $('#load-projects').html(html);
    });
})();