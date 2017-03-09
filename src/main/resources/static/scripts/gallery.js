/**
 * Created by Brian on 3/8/17.
 */
(function(){
    'use strict';

    console.log('JS is Working!');

    var html = $('#gallery-load-artwork').html().trim();
    if (html !== '') {
        return;
    }


    var request = $.ajax({
        url: '/gallery.json'
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

        for ( var i = 0; i < x.length; i++) {

            html += '<div>'
                + '<a href="/view/' + project[i].id + '"><img class = "gallery-image" src="/uploads/' + project[i].img_url + '" alt="No image"/></a>'
                + '</div>';

        }

        $('#gallery-load-artwork').html(html);
    });

    //-------------------------------------

    // $("#register-button") .click(function(event){
    //     if()
    //     event.preventDefault();
    // });

})();