/**
 * Created by dnf on 12.05.15.
 */

var users = [];

function updateUsers(){
    $.ajax({
        url: '/users',
        type: 'GET',
        dataType: 'json',
        success: function(data, textStatus, xhr) {
            users = data.sort(function(a,b){return a.name.localeCompare(b.name)});
        }
    });
    $('#users-list').html('');
    if($('#online-switch').hasClass('online-switch-on')){
        users.forEach(function(user){
            if(user.isOnline){
                $('#users-list').html($('#users-list').html()+
                '<div class="user">'+
                    '<span class="'+(user.isOnline?'online':'offline')+'">&#9679;</span>'+
                    '<span>'+user.name+'</span>'+
                '</div>');
            }
        })
    }else{
        users.forEach(function(user){
            $('#users-list').html($('#users-list').html()+
            '<div class="user">'+
                '<span class="'+(user.isOnline?'online':'offline')+'">&#9679;</span>'+
                '<span>'+user.name+'</span>'+
            '</div>');
        })
    }
}

function updateMessages(date){
    var messages = [];
    var username = getUsername();
    $.ajax({
        url: '/message/get?date='+date,
        type: 'GET',
        dataType: 'json',
        success: function(data, textStatus, xhr) {
            messages = data;
        }
    });
    messages.forEach(function(message){

    });
}

function getUsername(){
    return "123" //TODO: get username
}

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

    $('#send-button').click(function(){
        $.ajax({
            url: '/message/send',
            type: 'POST',
            success: function(data, textStatus, xhr) {

            }
        })
    });

    //------------------Список юзеров----------------------
    $('#online-switch').click(function(){
        if($(this).hasClass('online-switch-off')){
            $(this).removeClass('online-switch-off');
            $(this).addClass('online-switch-on');
            if($('#all-switch').hasClass('all-switch-on')){
                $('#all-switch').removeClass('all-switch-on');
                $('#all-switch').addClass('all-switch-off');
            }
            if($('#users-list').hasClass('users-list-all')){
                $('#users-list').removeClass('users-list-all');
                $('#users-list').addClass('users-list-online');
            }
            updateUsers();
        }
    });

    $('#all-switch').click(function(){
        if($(this).hasClass('all-switch-off')){
            $(this).removeClass('all-switch-off');
            $(this).addClass('all-switch-on');
            if($('#online-switch').hasClass('online-switch-on')){
                $('#online-switch').removeClass('online-switch-on');
                $('#online-switch').addClass('online-switch-off');
            }
            if($('#users-list').hasClass('users-list-online')){
                $('#users-list').removeClass('users-list-online');
                $('#users-list').addClass('users-list-all');
            }
            updateUsers();
        }
    });
    //-----------------------------------------------------

    $()
});
