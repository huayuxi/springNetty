<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String wsPath = request.getServerName();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>WebSocket Chat</title>
</head>
<body>
<script type="text/javascript">
  var socket;
  if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
  }
  if (window.WebSocket) {
    socket = new WebSocket("ws://<%=wsPath %>:5888/ws");
    socket.onmessage = function(event) {
      var ta = document.getElementById('responseText');
      ta.value = ta.value + '\n' + event.data
    };
    socket.onopen = function(event) {
      var ta = document.getElementById('responseText');
      ta.value = "连接开启!";


    };
    socket.onclose = function(event) {
      var ta = document.getElementById('responseText');
      ta.value = ta.value + "连接被关闭";
    };
  } else {
    alert("你的浏览器不支持 WebSocket！");
  }

  function send(cmd,message) {
    if (!window.WebSocket) {
      return;
    }
    if (socket.readyState == WebSocket.OPEN) {
      var msgJson = { "cmd": cmd, "data": message };
      socket.send(JSON.stringify(msgJson));
    } else {
      alert("连接没有开启.");
    }
  }
</script>
<form onsubmit="return false;">
  <span>设备UID：</span><input type="text" name="uid"/>
  <input type="button" value="监听" onclick="send('webLoginActor',this.form.uid.value)">
  <h3>设备监听日志：</h3>
  <textarea id="responseText" style="width: 500px; height: 300px;"></textarea>
  <br>
  <input type="text" name="message"  style="width: 300px">
  <input type="button" value="发送消息" onclick="send('sendDataActor',this.form.message.value)">
  <input type="button" onclick="javascript:document.getElementById('responseText').value=''" value="清空聊天记录">
</form>
<br>
<br>
</body>
</html>
