/**
 * Created by dn5f on 12.05.15.
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
            messages.forEach(function(message){
                var messageDiv = "<div class='message' msgdate='"+message.longDate+"'>" +
                    "<div class='message-text'><span "+
                    (message.username == username ? "class='user-message'" : "")+
                    ">"+message.username+": </span>" +
                    message.message + "</div>" +
                    "<div class='message-date'>"+message.stringDate+"</div></div>";

                $('#messages').append(messageDiv);
            });
        }
    });
}

function getUsername(){
    return $('#username').text().substr(4);
}

function getLastMessageDate(){
    if($('.message').length > 0) {
        return parseInt($(".message:last").attr("msgdate"));
    }else{
        return $('#startDate').text();
    }
}


$(document).ready(function() {

    updateMessages(getLastMessageDate());
    updateUsers();

    //----------------Автоматические обновления------------
    setInterval(function(){
        //updateMessages(getLastMessageDate());
        updateMessages(getLastMessageDate());
        updateUsers();
    }, 2000);
    //-----------------------------------------------------

    //-----------------Отправка сообщений------------------
    function sendMessage(){
        $('#send-button').attr('disabled','disabled');
        $.ajax({
            url: '/message/send',
            type: 'POST',
            data: {message: $('#message-input').val()},
            dataType: 'html',
            success: function(data, textStatus, xhr) {
                updateMessages(getLastMessageDate());
                $('#send-button').removeAttr('disabled');
            },
            error: function(xhr,textStatus, errorThrown ){
                $('#send-button').removeAttr('disabled');
            }
        })
        $('#message-input').val('');
    }

    $('#send-button').click(function(){
        sendMessage();
    });

    $('#message-input').keyup(function(e){
        if(e.keyCode == 13)
        {
            sendMessage();
        }
    });
    //-----------------------------------------------------

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
