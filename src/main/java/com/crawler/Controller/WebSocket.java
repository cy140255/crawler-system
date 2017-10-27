package com.crawler.Controller;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{param}")
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    private static Map<String,WebSocket> webSocketMap = new HashMap<>();



    @OnOpen
    public void Onopen(@PathParam(value = "param")String param, Session session){
        this.session = session;
        webSocketSet.add(this);
        webSocketMap.put(param,this);
        System.out.print(param+"连接上。");
    }

    @OnClose
    public void Onclose(Session session){
        webSocketSet.remove(this);
        try {
            this.session.close();
            System.out.print("关闭session");
        }catch (Exception e){
            System.out.print("发生了一个错误");
        }

    }


    @OnMessage
    public void OnMessage(String message,Session session){
        String username = message.split(",")[1];
        message = message.split(",")[0];
        try {
            WebSocket webSocket = webSocketMap.get(username);
            webSocket.sendMessage(message);

        }catch (Exception e){

        }

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
