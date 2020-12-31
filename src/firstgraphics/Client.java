package firstgraphics;

import static firstgraphics.window4.container;
import static firstgraphics.window4.scene;
import java.io.*;
import java.net.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

class Receiver implements Runnable {

    DatagramPacket packet;
    DatagramSocket socket;
    Thread th;
    Stage stage;
    boolean flag = true;
    String string;

    public Receiver(DatagramSocket sock, Stage stage, window1 wc) {
        Thread th = new Thread(this);
        this.socket = sock;
        this.stage = stage;
        string = new String();

        th.start();// Start the thread  

    }

    public void run() {
        while (true) {
            try {
                System.out.println("waiting");
                byte data[] = new byte[1024];
                packet = new DatagramPacket(data, data.length);
                System.out.println("" + socket.getLocalPort());

                socket.receive(packet);

                String rc = new String(packet.getData(), 0, packet.getLength());
                System.out.println(rc);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}

class sender implements Runnable {

    DatagramSocket sk;
    DatagramPacket pk;
    Thread th;
    //int portnumber;

    sender(DatagramPacket pk) {
        this.pk = pk;
        th = new Thread(this);
        th.start();

    }

    public void run() {

        try {
            {
                sk = new DatagramSocket();
                sk.send(pk);
            }
        } catch (Exception e) {
            System.out.println("error");

        }
    }
}

public class Client {

    String username;
    String password;
    String servername;
    int clportnumber;
    String serveraddress;
    int serverport;
    String clientport;
    DatagramSocket sk;
    DatagramSocket sock;
    //byte[] b;
    Stage stage;
    window1 w1;

    public Client(String clientportnumber, String Servername, String Serveraddress, int Serverport, Stage stage, window1 w1) throws Exception {
        int f1 = 0;
        // b = new byte[1024];
        this.w1 = w1;
        servername = Servername;
        serveraddress = Serveraddress;
        clientport = clientportnumber;
        this.stage = stage;
        System.out.println(clientport);
        int clportnumber = Integer.parseInt(clientport);
        sk = new DatagramSocket();
        sock = new DatagramSocket(clportnumber);

        serverport = Serverport;
    }

    void register(String clientname, String clientpassword) throws Exception {
        try {
            username = clientname;
            password = clientpassword;
            byte[] b;

            String rmsg = "Via: " + servername + "\n" + "password: " + password + "\n" + "register: " + username + "\n" + "Port: " + clientport;
            System.out.println(rmsg);
            b = rmsg.getBytes();

            DatagramPacket pk;
            pk = new DatagramPacket(b, b.length, InetAddress.getByName(serveraddress), serverport);

            sk.send(pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void receive(String clientname) throws Exception {
        System.out.println("waiting");
        byte data[] = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        System.out.println("" + sock.getLocalPort());
        sock.receive(packet);
        String rc = new String(packet.getData(), 0, packet.getLength());
        System.out.println("" + rc);
        //Receiver rc = new Receiver(sock, stage,wc);
        System.out.println("reached now");
        if (rc.contains("registration") == true) {
            String listr = rc.substring(rc.indexOf(";") + 1, rc.length());
            System.out.println(" " + clientport);
            window2 windowrc = new window2(clientname, listr, stage, this);
            Scene scenerc = windowrc.getscene();
            stage.setScene(scenerc);
            stage.show();

        } else if (rc.equals("Usernsme already used") == true) {
            System.out.println("warning e dhukse");
            registerwindow.warning.setText(rc);

        }

        if (rc.contains("login") == true) {
            System.out.println("login e asce");
            System.out.println(" " + clientport);
            System.out.println(rc);
            if (rc.contains("successful") == true) {
                System.out.println("login hoise");
                String list = rc.substring(rc.indexOf(";") + 1, rc.length());
                System.out.print(list + 45);
                window2 scene2 = new window2(clientname, list, stage, this);
                Scene scenewc2 = scene2.getscene();
                stage.setScene(scenewc2);
                stage.show();
            } else {
                w1.loginwarning.setText(rc);
//                Scene scenewc1 = w1.getscene();
//                stage.setScene(scenewc1);
//                stage.show();
//           
            }

        }
    }

    void receive2() throws Exception {
        byte[] recvBuf = new byte[2048];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
        sock.receive(packet);
        int byteCount = packet.getLength();
        System.out.println("" + byteCount + " " + recvBuf.length);
        //matter m = new matter ()

    }

    void login(String clientname, String clientpassword) throws Exception {
        username = clientname;
        password = clientpassword;
        byte[] b;

        String rmsg = "Via: " + servername + "\n" + "password: " + password + "\n" + "login: " + username + "\n" + "Port: " + clientport;
        b = rmsg.getBytes();
        DatagramPacket pk;
        pk = new DatagramPacket(b, b.length, InetAddress.getByName(serveraddress), serverport);
        sk.send(pk);

    }

    void sendprize(String s) throws Exception {
        String nmsg = "Via: " + servername + "\n" + "From: " + username + "\n" + "Prize:" + s + "\n" + "Port: " + clientport;
        byte[] c = new byte[1024];
        c = nmsg.getBytes();
        DatagramPacket pak;
        //System.out.println("fello");
        pak = new DatagramPacket(c, c.length, InetAddress.getByName(serveraddress), serverport);
        //System.out.println("gello");

        sender send = new sender(pak);
        byte[] rc = new byte[1024];
        DatagramPacket rpak = new DatagramPacket(rc, rc.length);
    }

    void send(String s) throws Exception {
        String msg = s;
        int x = 0;
        //x = msg.indexOf(";");
        //String recepient = msg.substring(0, x);
        //String body = msg.substring(x + 1, msg.length());
        String nmsg = "Via: " + servername + "\n" + "From: " + username + "\n" + "Body:;" + msg + "\n" + "Port: " + clientport;
        byte[] c = new byte[1024];
        c = nmsg.getBytes();
        DatagramPacket pak;
        //System.out.println("fello");
        pak = new DatagramPacket(c, c.length, InetAddress.getByName(serveraddress), serverport);
        //System.out.println("gello");

        sender send = new sender(pak);
        byte[] rc = new byte[1024];
        DatagramPacket rpak = new DatagramPacket(rc, rc.length);
//        sk.receive(rpak);
//        String showprize = new String(rpak.getData(), 0, rpak.getLength());
//        System.out.println(2+showprize+3);
    }

    void mybid() {
        try {

            String s = "Via: " + servername + "\n" + "From: " + username + "\n" + "mybids" + "\n" + "Port: " + clientport;
            byte[] bt = new byte[1028];
            bt = s.getBytes();
            DatagramPacket pak = new DatagramPacket(bt, bt.length, InetAddress.getByName(serveraddress), serverport);
            sk.send(pak);

        } catch (Exception e) {
            System.out.println("error in mybid method");
        }
    }

    void recievemybids() {
        try {
            byte[] rc2 = new byte[1024];
            DatagramPacket rpak = new DatagramPacket(rc2, rc2.length);
            sock.receive(rpak);
            String showprize = new String(rpak.getData(), 0, rpak.getLength());
            System.out.println("my won bids: " + showprize);
            Mybidwindow mybidw = new Mybidwindow(showprize, stage);
            Scene scene3 = mybidw.getscene();
            stage.setScene(scene3);
            stage.show();

        } catch (Exception e) {
            System.out.println("error in recienvemybids method");
        }
    }

    void sendreq() {

        try {
            String s = "Via: " + servername + "\n" + "From: " + username + "\n" + "Update" + "\n" + "Port: " + clientport;
            byte[] bt = new byte[1028];
            bt = s.getBytes();
            DatagramPacket pak = new DatagramPacket(bt, bt.length, InetAddress.getByName(serveraddress), serverport);

            sk.send(pak);

        } catch (Exception e) {
            System.out.println("error in sendreq");
        }
    }

    void receiverefresh() {
        try {
            byte[] rc2 = new byte[1024];
            DatagramPacket rpak = new DatagramPacket(rc2, rc2.length);
            sock.receive(rpak);
            String showprize = new String(rpak.getData(), 0, rpak.getLength());
            window3 w3 = new window3(showprize, this, stage);
            Scene scene3 = w3.getscene();
            stage.setScene(scene3);
            stage.show();

        } catch (Exception e) {
            System.out.println("error in receiverefresh method");
        }
    }

    void receive3(window3 wc, String name) throws Exception {
        try {
            byte[] rc2 = new byte[1024];
            DatagramPacket rpak = new DatagramPacket(rc2, rc2.length);
            sock.receive(rpak);
            if (rpak==null)
            {
                System.out.println("null msg in recive3");
            }
            else{
            String showprize = new String(rpak.getData(), 0, rpak.getLength());
            System.out.println(2 + showprize + 3);
            window4 w4 = new window4(this, showprize, wc, stage, name);
            Scene scene4 = w4.getscene();
            stage.setScene(scene4);
            window4.service.start();
            // window4.service1.start();
            //window4.service1.start();
            stage.show();
            }

        } catch (Exception e) {
            System.out.println("error in recive3");
            e.printStackTrace();
        }
    }

    String receive5(window3 wc, String name) throws Exception {
        try {
            byte[] rc2 = new byte[1024];
            DatagramPacket rpak = new DatagramPacket(rc2, rc2.length);
            sock.receive(rpak);
            if (rpak==null)
            {
                System.out.println("null packet");
            }
            else{
            String showprize = new String(rpak.getData(), 0, rpak.getLength());
            System.out.println(2 + showprize + 3);
            return showprize;
            }
//            window4 w4 = new window4(this, showprize, wc, stage, name);
//            Scene scene4 = w4.getscene();
//            stage.setScene(scene4);
//            window4.service.start();
//            window4.service1.start();
//            // window4.service1.start();
//            stage.show();

        } catch (Exception e) {
            System.out.println("error in recieve 5");
            e.printStackTrace();
        }
        
        return "Not received";
    }
}
