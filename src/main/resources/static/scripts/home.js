/**
 * Created by HKoehler on 2/25/17.
 */
(function(){
    'use strict';

    console.log('JS is Working!');

    var images = [
        '../img/block-background.jpg',
        '../img/astronaut-containers.jpeg',
        '../img/female-sculpture.jpeg',
        '../img/paint-palette.jpg',
        '../img/painting.jpeg',
        '../img/streetart-nightwalking.jpeg'
    ];

    var randomNumber = Math.floor(Math.random() * images.length);
    var backgroundImage = 'url(' + images[randomNumber] + ')';

    $('#homepage-header-block').css('background-image', backgroundImage);

    // call
    var request = $.ajax({
        url: '/home.json'
    });

    // populate featured art div
    request.done(function (project){
        var i, html = '';

        for (i = 0; i < 6; i++) {
            html += '<div>'
                + '<a href="/view/' + project[i].id + '"><img src="/uploads/' + project[i].img_url + '" alt="No image"/></a>'
                + '</div>';
        }

        $('#homepage-load-artwork').html(html);
    })

})();