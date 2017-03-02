/**
 * Created by HKoehler on 2/25/17.
 */
(function(){
    'use strict';

    console.log('JS is Working!');


    // randomize background image -------------------------------------------------------
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

    // populate featured art div --------------------------------------------------------

    var request = $.ajax({
        url: '/home.json'
    });

    request.done(function (project){
        var i, html = '';

        for (i = 0; i < project.length; i++) {
            html += '<div>'
                + '<a href="/view/' + project[i].id + '"><img src="/uploads/' + project[i].img_url + '" alt="No image"/></a>'
                + '</div>';
        }

        $('#homepage-load-artwork').html(html);
    });

    // random profile button -----------------------------------------------------------

    var usersRequest = $.ajax({
        url: '/portfolio.json'
    });

    usersRequest.done(function (users){

        var i;
        var randomPortfolio = Math.floor(Math.random() * users.length);

        $('#random-portfolio-btn').onclick(function(){

        });
    });

})();