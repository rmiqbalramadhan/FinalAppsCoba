package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSpeech {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String pesan;
    private static int port = 4444;
    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /IM ";

    public static int itungKata(String str){
        int awal = 1;
        for (int i=0; i<=str.length()-1; i++){
            if(str.charAt(i) == ' ' && str.charAt(i+1)!= ' '){
                awal++;
            }
        }
        return awal;
    }
    public static boolean isProcessRunning(String serviceName) throws Exception {

        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {

            System.out.println(line);
            if (line.contains(serviceName)) {
                return true;
            }
        }

        return false;

    }

    public static void killProcess(String serviceName) throws Exception {

        Runtime.getRuntime().exec(KILL + serviceName);

    }
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.print("Tidak bisa Connect ke port");
        }

        System.out.print("Server Berjalan. Listening ke port: " + port);

        while (true) {
            try {
                clientSocket = serverSocket.accept(); //accept client connection
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                pesan = bufferedReader.readLine();

                System.out.println(pesan);
                String kalimat = pesan.toLowerCase();
                int hasil = itungKata(kalimat);
                System.out.println(hasil);
                String[] tangkapPerintah = kalimat.split(" ", hasil);
                String perintah = tangkapPerintah[0];
                String program = tangkapPerintah[1];
                //String program2 = tangkapPerintah[2];
                //String program3 = tangkapPerintah[3];
                System.out.println(perintah);
                System.out.println(program);
                System.out.println("a");

                if (perintah.equals("buka")) {
                    if (program.equals("notepad")) {
                        Runtime runtime = Runtime.getRuntime();
                        try {
                            System.out.println("b");
                            runtime.exec("notepad");
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }

                    if (program.equals("google")){
                        Runtime runtime = Runtime.getRuntime();
                        String[] s = new String[]{"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "http://facebook.com/"};
                        try {
                            runtime.exec(s);
                        } catch (Exception ex){
                            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                    else {
                        Runtime runtime = Runtime.getRuntime();
                        String perintah3 = tangkapPerintah[2];
                        String perintah4 = tangkapPerintah[3];
                        try {
                            if(perintah3.equals("bab")){
                                String[] s = new String[]{"C:\\Program Files\\Microsoft Office\\Office15\\POWERPNT.exe", "F:\\Coba\\"+perintah3+" "+perintah4+".pptx"};
                                System.out.println("c");
                                runtime.exec(s);
                            }
                        } catch (Exception ex) {
                            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }

                else if (perintah.equals("matikan")){
                    Runtime runtime = Runtime.getRuntime();
                    Process proc = runtime.exec("shutdown -s -t 0");
                }
                else if (perintah.equals("tutup")){
                    try {
                        if (isProcessRunning(program)) {
                            killProcess(program);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("nang kene");
                }
                /*
                int hasil = itungKata(kalimat);
                String[] tangkapPerintah = kalimat.split(" ", hasil);
                for (int i = 0; i<hasil; i++){
                    String perintah = tangkapPerintah[i];
                    System.out.println(perintah);
                    //System.out.println("a");
                    if (perintah.equals("buka")) {
                        if (perintah.equals("notepad")) {
                            Runtime runtime = Runtime.getRuntime();
                            try {
                                //System.out.println("b");
                                runtime.exec("notepad");
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        if (perintah.equals("ppt") || perintah.equals("presentasi")) {
                            Runtime runtime = Runtime.getRuntime();
                            String[] s = new String[]{"C:\\Program Files\\Microsoft Office\\Office15\\POWERPNT.exe"};
                            try {
                                //System.out.println("b");
                                runtime.exec(s);
                            } catch (Exception ex) {
                                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        if (perintah.equals("google")){
                            Runtime runtime = Runtime.getRuntime();
                            String[] s = new String[]{"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "http://facebook.com/"};
                            try {
                                runtime.exec(s);
                            } catch (Exception ex){
                                javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                    }
                    else if (perintah.equals("matikan")){
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec("shutdown -s -t 0");
                    }
                    else if (perintah.equals("tutup")){

                    }
                    else {
                        System.out.println("nang kene");
                    }
                }*/
                inputStreamReader.close();
                clientSocket.close();

            } catch (IOException e) {
                System.out.print("Problem in message reading");
            }
        }
    }
}
