package com.huang.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by User on 2019/8/28.
 */
public class TimeServer {

    private static final Logger log = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String[] args) {
        log.info("server is starting ....");
        try {
            ServerSocket serverSocket = new ServerSocket(1122);
            Socket socket = serverSocket.accept();
            new Thread(new TimeServerHandler(socket)).start();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    private static class TimeServerHandler implements Runnable {

        private Socket socket = null;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            // 处理连接
            InputStream in = null;
            OutputStream out = null;
            try {
                in = this.socket.getInputStream();// 获取输入流
                out = this.socket.getOutputStream();// 获取输出流
                //客户端传来的消息
                BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(in));
                while (true){
                    String body =  bufferedReader.readLine();
                    if(body == null){ break;}
                    System.out.println("the time server receive order : " + body);
                }

                //传输给客户端
                PrintWriter printWriter = new PrintWriter(out,true);
                printWriter.println(new Date().getTime());
            } catch (IOException e) {
                log.error("", e);
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        log.error("",e1);
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        log.error("",e1);
                    }
                }
                if(this.socket != null){
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        log.error("",e1);
                    }
                }
                this.socket = null;
            }
        }
    }
}
