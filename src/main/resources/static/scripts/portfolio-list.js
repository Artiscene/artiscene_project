/**
 * Created by Brian on 2/27/17.
 */
(function () {
    var user_id=$("#user_id").val();
    var request = $.ajax({

        url: '/portfolio.json',
        data:{id:user_id}
    });
    console.log("JS portfolio is working");
    request.done(function (projects) {  // the HTTP response -> an array of JSON objects -> ads
        var i, html = '';

        for (i = 0; i < projects.length; i++) {
            html += '<div><h2>'
                + projects[i].title + '</h2>'
                + '<img src="/uploads/' + projects[i].img_url + '" alt="No image"/>'
                + '</div>';
        }

        $('#load-portfolio').html(html);
    });
})();