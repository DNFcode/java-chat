/**
 * Created by dnf on 12.05.15.
 */
$(document).ready(function() {
    $('#a').click(function(){
        $.ajax({
            url: '/hello?username=1234',
            type: 'GET',
            dataType: 'json',
            success: function(data, textStatus, xhr) {
                console.log(xhr.status);
            },
            complete: function(xhr, textStatus) {
                console.log(xhr.status);
            },
            error: function(xhr,textStatus, errorThrown ){
                console.log(xhr.status);
            }
        })
    })
})
