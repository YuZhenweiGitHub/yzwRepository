var websocket = null;

var IM = {
        initWs : function () {
            //判断当前浏览器是否支持WebSocket
            if('WebSocket' in window){
                IM.setMessageInnerHTML("正在初始化连接...");
                var wsUrl = "ws://"+ document.domain +":8090/websocket/sdasdsa";

                websocket = new WebSocket(wsUrl);

                //连接成功建立的回调方法
                websocket.onopen = function(event){
                    IM.setMessageInnerHTML("连接已打开");
                }

                //连接发生错误的回调方法
                websocket.onerror = function(){
                    IM.setMessageInnerHTML("连接错误");
                };

                //接收到消息的回调方法
                websocket.onmessage = function(obj){
                    IM.setMessageInnerHTML(obj.data);
                }

                //连接关闭的回调方法
                websocket.onclose = function(){
                    IM.setMessageInnerHTML("连接已关闭");
                }

                //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = function(){
                    IM.closeWebSocket();
                }
        } else {
            alter('不支持WebSocket');
        }
    },
    //将消息显示在网页上
    setMessageInnerHTML : function (innerHTML){
        document.getElementById('MessageContent').innerHTML += innerHTML + '<br/>';
    },
    //关闭连接
    closeWebSocket : function (){
        websocket.close();
    },
    //发送消息
    send : function (){
        var sendObj = document.getElementById('sendText');
        var message = sendObj.value;
        websocket.send(message);
        sendObj.value = "";
    },
    sendAll : function (){
        var sendObj = document.getElementById('sendText');
        var message = sendObj.value;
        $.post("sendMsg",{msg:message},function(){

        });
    }
};