package com.huang.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by User on 2019/8/28.
 */
public class TimeClient {
    private static final Logger log = LoggerFactory.getLogger(TimeServer.class);

    public static void main(String[] args) {
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            socket = new Socket("127.0.0.1", 1122);
            in = socket.getInputStream();// 获取输入流
            out = socket.getOutputStream();//获取输出流
            //to server
            PrintWriter printWriter = new PrintWriter(out,true);
            printWriter.println("to server: " + new Date().getTime());

            //from server
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(in));
            while (true){
                String body =  bufferedReader.readLine();
                if(body == null){ break;}
                log.info("from server message : " + body);
            }
        } catch (Exception e) {
            //不做处理
        } finally {
            // 关闭资源
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    log.error("", e1);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    log.error("", e1);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    log.error("", e1);
                }
            }
        }


    }

}
