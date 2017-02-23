/**
 * Created by Brian on 2/23/17.
 */
(function () {
    var request = $.ajax({
        url: '/gallery.json'
    });

    request.done(function (ads) {  // the HTTP response -> an array of JSON objects -> ads
        var i, html = '';

        for (i = 0; i < ads.length; i++) {
            html += '<div><h2>'
                + ads[i].title + '</h2><p>'
                + ads[i].description +  '</p>'
                + '<img src="/ads/image/' + ads[i].image + '" alt="No image"/>'
                + '</div>';
        }

        $('#load-ads').html(html);
    });
})();