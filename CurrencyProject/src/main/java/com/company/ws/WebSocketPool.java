package com.company.ws;

import com.company.controller.websocket.WebSocketController;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.util.*;

/**
 * Created by YZW on 2017/10/31.
 */
public class WebSocketPool {

        private static final Map<String, WebSocketController> wsUserMap = new HashMap<String, WebSocketController>();

        /**
         * 通过websocket连接获取其对应的用户
         *
         * @param user
         *
         * @return
         */
        public static WebSocketController getUserByWs(String user) {
            return wsUserMap.get(user);
        }

        /**
         * 根据userName获取WebSocket,这是一个list,此处取第一个
         * 因为有可能多个websocket对应一个userName（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
         *
         * @param userName
         */
        /*public static WebSocket getWsByUser(String userName) {
            Set<WebSocket> keySet = wsUserMap.keySet();
            synchronized (keySet) {
                for (WebSocket conn : keySet) {
                    String cuser = wsUserMap.get(conn);
                    if (cuser.equals(userName)) {
                        return conn;
                    }
                }
            }
            return null;
        }*/

        /**
         * 向连接池中添加连接
         *@param conn
         * @param userName
         */
        public static void addUser(WebSocketController conn, String userName) {
            wsUserMap.put(userName, conn); // 添加连接
        }

        /**
         * 获取所有连接池中的用户，因为set是不允许重复的，所以可以得到无重复的user数组
         *
         * @return
         */
        /*public static Collection<String> getOnlineUser() {
            List<String> setUsers = new ArrayList<String>();
            Collection<String> setUser = wsUserMap.values();
            for (String u : setUser) {
                setUsers.add(u);
            }
            return setUsers;
        }*/

        /**
         * 移除连接池中的连接
         *
         * @param user
         */
        public static boolean removeUser(String user) {
            if (wsUserMap.containsKey(user)) {
                wsUserMap.remove(user); // 移除连接
                return true;
            } else {
                return false;
            }
        }

        /**
         * 向特定的用户发送数据
         *
         * @param conn
         * @param message
         */
        public static void sendMessageToUser(WebSocket conn, String message) {
            if (null != conn && null != wsUserMap.get(conn)) {
                conn.send(message);
            }
        }

        /**
         * 向所有的用户发送消息
         *
         * @param message
         */
        public static void sendMessageToAll(String message) {
            Set<String> keySet = wsUserMap.keySet();
            synchronized (keySet) {
                for (String user : keySet) {
                    WebSocketController conn = wsUserMap.get(user);
                    if (conn != null) {
                        try {
                            conn.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
            }
        }
}
