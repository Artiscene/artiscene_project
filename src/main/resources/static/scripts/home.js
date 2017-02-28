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

})();