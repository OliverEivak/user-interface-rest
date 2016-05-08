package com.github.olivereivak.ui.sis.util;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketUtils {

    public static void sendByte(InetAddress address, int port, byte message) throws Exception {
        Socket socket = new Socket(address, port);
        OutputStream output = socket.getOutputStream();
        output.write(message);
        output.flush();
        output.close();
        socket.close();
    }
}
