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

    $("#tag-select").select2({
            theme: "bootstrap"
        });

})();

