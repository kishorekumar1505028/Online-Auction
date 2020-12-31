package firstgraphics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
class matter implements Serializable {

    int initial_prize;
    int current_max_prize;
    String name;
    Calendar setcal;
    long set_time;
    String matter_winner;

    public matter(String name, int prize, int day, int hour, int minute, int second) {
        initial_prize = prize;
        current_max_prize = 0;
        matter_winner = new String("None");
        this.name = name;
        setcal = Calendar.getInstance();
        setcal.set(2016, 11, day, hour, minute, second);
        set_time = setcal.getTimeInMillis();
    }

    void set_max_prize(int prize, String s) {
        if (current_max_prize == 0) {
            if (prize > initial_prize) {
                current_max_prize = prize;
                matter_winner = s;
                // current_max_prize;
            }

        } else {
            if (prize > current_max_prize) {
                current_max_prize = prize;
                matter_winner = s;
                // current_max_prize;
            } else {
                //return -1;
            }

        }
    }

    long isrunnable() {
        Calendar cal = Calendar.getInstance();
        //cal.set(2016, 11, 14, 18, 00, 00);
        long current_time = cal.getTimeInMillis();
        return set_time - current_time;
    }

    @Override
    public String toString() {
        String s = "name " + name;
        return s;//return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}

public class Server {

    public static void main(String[] args) throws Exception {
        int n = 100;
        int total = 10;
        String[][] address_store = new String[100][2];
        int size = 0;
        InetAddress[] adress = new InetAddress[100];//adrs=adresses
        //String sender = args[0];//givers=senders
        String sender = "cse";
        int count = 0;
        int p[] = new int[100];//p=ports
        String[] store = new String[n];
        String[] pass = new String[n];
        int store_idx = 0;
        DatagramSocket sk = new DatagramSocket(5051);
        DatagramSocket sk2 = new DatagramSocket();
        byte dt[] = new byte[1024];
        matter[] matter_array = new matter[100];

//        File file = new File("/TextContent/user_data.txt");
//        Scanner user_info = new Scanner(file);
//        while (user_info.hasNextLine()) {
//            Scanner tmp2 = new Scanner(user_info.nextLine());
//            tmp2.useDelimiter(":|;");
//            store[store_idx] = tmp2.next();
//            pass[store_idx] = tmp2.next();
//            store_idx++;
//            tmp2.close();
//        }
//        user_info.close();
//         //File data = new File("/TextContent/input.txt");
//        Scanner in = new Scanner(data);
//        matter m[] = new matter[10];
//        matter[] matter_array = new matter[100];
//        int arasize = 0;
//        while (in.hasNextLine()) {
//
//            Scanner tmp = new Scanner(in.nextLine());
//            tmp.useDelimiter(",|;");
//            matter_array[arasize] = new matter(tmp.next(), Integer.parseInt(tmp.next()), Integer.parseInt(tmp.next()), Integer.parseInt(tmp.next()), Integer.parseInt(tmp.next()), Integer.parseInt(tmp.next()));
//            tmp.close();
//            arasize++;
//            total++;
//
//        }
//        System.out.println("total :" + total);
//        in.close();

//        for (int j = 0; j < arasize; j++) {
//            System.out.println(matter_array[j]);
//        }
    //int i;
        for (  int i = 0; i < total; i++) {
            matter_array[i] = new matter("item" + (i + 1), 100, 31, 23,51, 49 + i);
        }
        for ( int i=0;i<total;i++)
        {
            System.out.println(""+matter_array[i].name);
        }
        while (true) {
            // System.out.println("" + lists);

            byte bf[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bf, bf.length);
            int a, b, c = 0, d;
            sk.receive(packet);
            InetAddress adrs = packet.getAddress();
            //System.out.println("port of client: "+packet.getPort());
            String rc = new String(packet.getData(), 0, packet.getLength());

            System.out.println(adrs);
            System.out.println("" + rc);//rc=recieved
            if (rc.contains("Port: ") == true) {
                a = rc.indexOf("Via: ");
                b = rc.indexOf("\n");
                d = rc.indexOf("\nPort: ");
                String msn = "cse";//rc.substring(a + 5, b);
                String man = rc.substring(a + 5, b);
                String port = rc.substring(d + 7, rc.length());
                int cport = Integer.parseInt(port);
                int i;
                //msn=message server name
                System.out.println("" + man + " hi");
                System.out.println("gone");
                if (sender.equals(man) == false) {
                    System.out.println("Warning: Server name mismatch. Message dropped.");
                } else if (sender.equals(man) == true) {
                    if (rc.contains("\nregister: ") == true) {
                        c = rc.indexOf("\nregister: ");
                        String password = rc.substring(b + 11, c);
                        System.out.println(4 + port + 6);
                        String register = rc.substring(c + 11, d);
                        //int i = 0;
                        for (i = 0; i < store_idx; i++) {
                            System.out.println("" + store[i] + " 23");
                            System.out.println("" + register + " 23");
                            System.out.println("" + pass[i] + " 23");
                            System.out.println("" + password + " 23");
                            System.out.println("" + i);
                            if ((store[i].equals(register) == true)) {
                                break;
                            }
                        }
                        if (((register.equals(null))==true) || (password.equals(null))==true) {
                            System.out.println("register null");
                            String warning = new String("Enter a valid Username or Password");
                            byte[] bt = new byte[1024];
                            bt = warning.getBytes();
                            DatagramPacket msg = new DatagramPacket(bt, bt.length, adrs, cport);
                            sk2.send(msg);
                        }
                        else if ((i != store_idx && store_idx != 0)||(((register.equals(null))==true) && (password.equals(null))==true)) {
                            String warning = new String("Usernsme already used");
                            byte[] bt = new byte[1024];
                            bt = warning.getBytes();
                            DatagramPacket msg = new DatagramPacket(bt, bt.length, adrs, cport);
                            sk2.send(msg);

                        }   else {

//                            File file1 = new File("user_data.txt");
//                            //FileOutputStream fout  = new FileOutputStream(file1);
//                            FileWriter writer = new FileWriter(file1, true);
//                            String appender = register + ":" + password + ";" + "\n";
//                            writer.write(appender);
//                            writer.close();

                            store[store_idx] = register;
                            pass[store_idx] = password;
                            store_idx++;
                            adress[count] = packet.getAddress();
                            p[count] = cport;
                            //givers[count] = giver;
                            count++;
                            String matter_list = new String();
                            for (i = 0; i < total; i++) {
                                if (matter_array[i].isrunnable() > 0) {
                                    System.out.println("matter " + i + " " + matter_array[i].isrunnable());
                                    matter_list += matter_array[i].name + "\n";
                                }
                            }
                            System.out.println("matter list:" + matter_list);
                            System.out.println("initial msg from " + register);
                            String real_msgrc = "registration successful \n;" + matter_list;
                            bf = real_msgrc.getBytes();
                            System.out.println("" + adress[count - 1]);
                            System.out.println("" + p[count - 1]);
                            System.out.println("as");
                            DatagramPacket msg = new DatagramPacket(bf, bf.length, adrs, cport);
                            sk2.send(msg);
//InetAddress.getByName(address_store[0][0].substring(1,address_store[0][0].length())),Integer.parseInt(address_store[0][1])
                            System.out.println("sent");
                            System.out.println("" + p[count - 1]);
                        }
                    } else if (rc.contains("\nlogin: ") == true) {
                        //int a,b,c,d;
                        // sk.receive(packet);
                        //String rc = new String(packet.getData(), 0, packet.getLength());
                        a = rc.indexOf("Via: ");
                        b = rc.indexOf("\npassword: ");
                        c = rc.indexOf("\nlogin: ");
                        d = rc.indexOf("\nPort: ");
                        String login = rc.substring(c + 8, d);
                        //msn=message server name
                        String password = rc.substring(b + 11, c);
                        //String port = rc.substring(d + 7, d + 12);
                        //int cport = Integer.parseInt(port);
                        // else {
                        //int i;
                        //b = rc.indexOf("To: ");
                        //rpt = rc.substring(b + 4, c);
                        System.out.println("" + store_idx);
                        for (i = 0; i < store_idx; i++) {
                            System.out.println("" + store[i] + " 23");
                            System.out.println("" + login + " 23");
                            System.out.println("" + pass[i] + " 23");
                            System.out.println("" + password + " 23");
                            System.out.println("" + i);
                            if ((store[i].equals(login) == true) && (pass[i].equals(password) == true)) {
                                break;
                            }
                        }
                        System.out.println("" + store_idx);
                        String real_msg;
                        if (i == store_idx) {
                            System.out.println("Warning: Unknown recipient. Message dropped.");
                            real_msg = "invalid login";

                        } else {
                            // DatagramSocket cl = new DatagramSocket();
                            String matter_list = new String();
                            for (i = 0; i < total; i++) {
                                if (matter_array[i].isrunnable() > 0) {
                                    System.out.println("matter " + i + " " + matter_array[i].isrunnable());
                                    matter_list += matter_array[i].name + "\n";
                                }
                            }
                            System.out.println("matter list:" + matter_list);
                            real_msg = "login successful \n;" + matter_list;
                        }
                        byte[] bt = new byte[1024];
                        bt = real_msg.getBytes();
                        DatagramPacket msg = new DatagramPacket(bt, bt.length, adrs, cport);
                        sk2.send(msg);
                        //sk2.send(pak);

                    } else if (rc.contains("mybids") == true) {
                        System.out.println("mybids e dhukse");
                        int mycount = 0;
                        String user = rc.substring(rc.indexOf("From: ") + 6, rc.indexOf("\nmybids"));
                        String mylist = new String();
                        for (i = 0; i < total; i++) {
                            System.out.println("winner:" + matter_array[i].matter_winner + "{" + user + "}" + "difference:" + matter_array[i].isrunnable());
                            if ((matter_array[i].matter_winner.equals(user) == true) && (matter_array[i].isrunnable() < 0)) {
                                System.out.println();
                                mylist += matter_array[i].name + "\n";
                                mycount++;
                            }

                        }
                        System.out.println("counter:" + mycount);
                        if (mycount == 0) {
                            mylist = "Nothing to Show";
                        }
                        //else{
                        byte[] bt = new byte[1024];
                        System.out.println("mylist: " + mylist);
                        bt = mylist.getBytes();
                        DatagramPacket msg = new DatagramPacket(bt, bt.length, adrs, cport);
                        sk2.send(msg);

                        //}
                    } else if (rc.contains("\nUpdate") == true) {
                        System.out.println("update e dhukse");
                        String matter_list = new String();
                        for (i = 0; i < 10; i++) {
                            //if (matter_array[i].isrunnable() > 0) {
                                System.out.println("matter " + i + " " + matter_array[i].isrunnable());
                                matter_list += matter_array[i].name + "\n";
                            //}
                        }
                        System.out.println("matter list:" + matter_list);
                        String real_msg = matter_list;

                        byte[] bt = new byte[1024];
                        bt = real_msg.getBytes();
                        DatagramPacket msg = new DatagramPacket(bt, bt.length, adrs, cport);
                        sk2.send(msg);

                    } else if (rc.contains("Body") == true) {
                        String matter_name = rc.substring(rc.indexOf(';') + 1, rc.indexOf("\nPort"));
                        System.out.println(matter_name + "right");
                        DatagramSocket sk3 = new DatagramSocket();
                        int prize = 0, max_prize = 0;
                        long end_time = 0;
                        int t = 0;
                        for (t = 0; t < total; t++) {
                            if (matter_name.equals(matter_array[t].name) == true) {
                                prize = matter_array[t].initial_prize;
                                max_prize = matter_array[t].current_max_prize;
                                end_time = matter_array[t].set_time;
                                System.out.println(prize);
                                break;
                            }

                        }
                        String finaltime = Long.toString(end_time);
                        String initialvalue = Integer.toString(prize);
                        String maxvalue = Integer.toString(max_prize);
                        String combined = "Initial Price=" + initialvalue + "\n" + "Current Maximum Price=" + maxvalue + "matter name" + matter_array[t].name + "matter winner:{" + matter_array[t].matter_winner + "}" + ";" + finaltime;
                        System.out.println(2 + "Initial value=" + initialvalue + 3 + combined);
                        byte[] newmsg = new byte[1024];
                        newmsg = combined.getBytes();
                        DatagramPacket pack = new DatagramPacket(newmsg, newmsg.length, adrs, cport);
                        System.out.println(cport);
                        System.out.println(adrs);
                        sk3.send(pack);

                    } else if (rc.contains("Prize") == true) {
                        try {
                            String initialvalue = new String();
                            String maxvalue = new String();
                            System.out.println("ok");

                            String matter_prize = rc.substring(rc.indexOf("Prize:") + 6, rc.indexOf('&'));
                            String matter_name = rc.substring(rc.indexOf('&') + 1, rc.indexOf("{"));
                            String matter_user = rc.substring(rc.indexOf('{') + 1, rc.indexOf("\nPort"));
                            System.out.println(2 + matter_prize + 5);
                            System.out.println(7 + matter_name + 8);
                            System.out.println("winner:" + matter_user);
                            int userprize = Integer.parseInt(matter_prize);
                            int t = 0;
                            long end_time = 0;
                            for (t = 0; t < total; t++) {
                                if (matter_name.equals(matter_array[t].name) == true) {
                                    matter_array[t].set_max_prize(userprize, matter_user);
                                    initialvalue = Integer.toString(matter_array[t].initial_prize);
                                    maxvalue = Integer.toString(matter_array[t].current_max_prize);
                                    end_time = matter_array[t].set_time;
                                    // System.out.println(prize);
                                    System.out.println(userprize);
                                    break;
                                }

                            }
                            String finaltime = Long.toString(end_time);
                            System.out.println(userprize);
                            String combinedmsg = "Initial Price=" + initialvalue + "\n" + "Current Maximum Price=" + maxvalue + "matter name" + matter_array[t].name + "matter winner:{" + matter_array[t].matter_winner + "}" + ";" + finaltime;
                            System.out.println(combinedmsg);
                            byte[] updateprize = new byte[1024];

                            updateprize = combinedmsg.getBytes();
//                            for (i=0;i<size;i++)
//                            {
//                                DatagramPacket msg = new DatagramPacket(bf, bf.length,InetAddress.getByName(address_store[0][0].substring(1,address_store[i][0].length())),Integer.parseInt(address_store[i][1]));
//                                sk2.send(msg);
//                            }
                            DatagramPacket pack4 = new DatagramPacket(updateprize, updateprize.length, adrs, cport);
                            DatagramSocket sk4 = new DatagramSocket();
                            sk4.send(pack4);
                        } catch (Exception e) {
                            System.out.println("null pointer");
                        }
                    }
                }

            }

        }

    }

}
//java Client a 12345 127.0.0.1 cse
