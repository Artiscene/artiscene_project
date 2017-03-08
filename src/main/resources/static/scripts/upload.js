/**
 * Created by HKoehler on 2/22/17.
 */
(function(){
    'use strict';


    console.log('JS is working');

    $('#for-sale-checkbox').click(function() {
        if( $(this).is(':checked')) {
            $('#price-div').css('display', 'inline');
        } else {
            $('#price-div').css('display', 'none');
        }
    });

    $('#add-artwork-btn').click(function(){
        $("#upload-div").clone().appendTo('#upload-div');
    });

    $("#tag-select").select2();

        $(".select2-selection").css("width", "400px");

    $(window).resize(function() {
        if ($(window).width() < 500) {
            $('.select2-selection').css("width", "200px");
        }
    });

})();

