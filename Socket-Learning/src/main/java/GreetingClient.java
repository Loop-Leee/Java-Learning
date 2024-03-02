import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PrivilegedAction;

/**
 * ClassName: GreetingClient
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/2 16:51
 * @Version 1.0
 */
public class GreetingClient extends Thread{
    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            System.out.println("连接到主机:" + serverName + "，端口号:" + port);
            Socket client = new Socket(serverName,port);
            System.out.println("远程主机地址:" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from" + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应:" + in.readUTF());
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
