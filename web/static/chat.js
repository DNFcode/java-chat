/**
 * Created by dnf on 12.05.15.
 */
$(document).ready(function() {
    $('#a').click(function(){
        $.ajax({
            url: '/hello?username=1234',
            type: 'GET',
            success: function(){
                alert('bitcj')
            }
        })
    })
})
