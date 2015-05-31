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
        }
    });
    messages.forEach(function(message){
        var messageDiv = "<div class='message' msgdate='"+message.date+"'><span "+
            (message.username == username ? "class='user-message'" : "")+
            ">"+message.username+": </span>" +
            message.message + "</div>";

        $('#messages').append(messageDiv);
    });
}

function getUsername(){
    return $('#username').text().substr(4);
}

function getLastMessageDate(){
    return parseInt($("#messages:last-child").attr("msgdate"));
}


//короче бывает все не так просто... бывает что js начинает отрабатывать раньше
//чем отрисовывается html ... вооот. Так вот. та хрень внизу
// начинает отрабатывать когда страница отрисовалась
$(document).ready(function() {

    $('#a').click(function() {
        $.ajax({
            url: '/test',
            type: 'GET',
            dataType: 'json'
        });
    });

    //классные комменты  тут D: понятные очень
    //----------------РђРІС‚РѕРјР°С‚РёС‡РµСЃРєРёРµ РѕР±РЅРѕРІР»РµРЅРёСЏ------------
    setInterval(function(){
        //updateMessages(getLastMessageDate());
        updateMessages('2015-05-31');
        updateUsers();
    }, 2000);
    //-----------------------------------------------------

    //-----------------РћС‚РїСЂР°РІРєР° СЃРѕРѕР±С‰РµРЅРёР№------------------
    function sendMessage(){
        $('#message-input').val('');
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

    //------------------РЎРїРёСЃРѕРє СЋР·РµСЂРѕРІ----------------------
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
