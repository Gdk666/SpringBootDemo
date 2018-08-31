package Guoz.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import static com.guoz.framework.commons.utils.WebSocketUtils.SESSION_CACHE;
import static com.guoz.framework.commons.utils.WebSocketUtils.sendMessage;
import static com.guoz.framework.commons.utils.WebSocketUtils.sendMessageAll;


/**
 * @description:
 * @Data 10:14
 * @Version 1.0
 * @author: Guoz
 **/
@RestController
@ServerEndpoint("/chat-room/{userName}")
public class ChatRoomServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    @OnOpen
    public void openSession(@PathParam("userName")String userName, Session session){
        SESSION_CACHE.put(userName,session);
        String message = "欢迎用户【"+userName+"】来聊天";
        log.debug(message);
        sendMessageAll(message);
    }
    @OnMessage
    public void onMessage(@PathParam("userName")String userName,String message){
        String str = "用户【"+userName+"】:"+message;
        sendMessageAll(str);
    }
    @OnClose
    public void onClose(@PathParam("userName")String userName,Session session){
        //当关闭聊天 移除session
        SESSION_CACHE.remove(userName);
        sendMessageAll("用户【"+userName+"】离开");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnError
    public void onError(Session session,Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
    @GetMapping("/chat-room/{sender}/to/{receive}")
    public void onMessage(@PathVariable("sender") String sender, @PathVariable("receive") String receive, String message) {
        sendMessage(SESSION_CACHE.get(receive), "[" + sender + "]" + "-> [" + receive + "] : " + message);
    }
    @GetMapping("/room")
    public ModelAndView chatRoom(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("chat-room");
        return mav;
    }

}
