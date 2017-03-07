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
        '../img/art-viewing.jpeg',
        '../img/model-hand.jpeg',
        '../img/hand-dust-flour-chalk.jpg'
    ];

    var randomNumber = Math.floor(Math.random() * images.length);
    var backgroundImage = 'url(' + images[randomNumber] + ')';

    $('#homepage-header-block').css('background-image', backgroundImage);


    // populate featured art div --------------------------------------------------------

    var request = $.ajax({
        url: '/home.json'
    });

    request.done(function (project){
        var html = '';

        function shuffle(array) {
            var currentIndex = array.length, temporaryValue, randomIndex;

            // While there remain elements to shuffle...
            while (0 !== currentIndex) {

                // Pick a remaining element...
                randomIndex = Math.floor(Math.random() * currentIndex);
                currentIndex -= 1;

                // And swap it with the current element.
                temporaryValue = array[currentIndex];
                array[currentIndex] = array[randomIndex];
                array[randomIndex] = temporaryValue;
            }

            return array;
        }
        var x = shuffle(project);
        console.log(x);

        for ( var i = 0; i < 12; i++) {

            html += '<div>'
                + '<a href="/view/' + project[i].id + '"><img src="/uploads/' + project[i].img_url + '" alt="No image"/></a>'
                + '</div>';

        }

        $('#homepage-load-artwork').html(html);
    });

})();