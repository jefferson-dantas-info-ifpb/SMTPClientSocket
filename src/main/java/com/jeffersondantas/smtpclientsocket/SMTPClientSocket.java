package com.jeffersondantas.smtpclientsocket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class SMTPClientSocket {

    public static void main(String[] args) {
        String host = "sandbox.smtp.mailtrap.io";
        int port = 25;
        String username = "fddacfacede0c6";
        String password = "2f363083e871cf";
        String from = "from@email.com";
        String to = "to@email.com";

        try (Socket socket = new Socket(host, port)) {
//            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

//            http://www.java2s.com/Tutorials/Java/Socket/How_to_use_Java_Socket_class_to_create_a_SMTP_Client.htm
//            https://www.afternerd.com/blog/wp-content/uploads/2017/11/SMTP-sequence-diagram.png
//https://en.wikipedia.org/wiki/SMTP_Authentication
//S: 220 smtp.example.com ESMTP Server
//C: EHLO client.example.com
//S: 250-smtp.example.com Hello client.example.com
//S: 250-AUTH GSSAPI DIGEST-MD5
//S: 250-ENHANCEDSTATUSCODES
//S: 250 STARTTLS
//C: STARTTLS
//S: 220 Ready to start TLS
//    ... TLS negotiation proceeds. 
//     Further commands protected by TLS layer ...
//C: EHLO client.example.com
//S: 250-smtp.example.com Hello client.example.com
//S: 250 AUTH GSSAPI DIGEST-MD5 PLAIN
//C: AUTH PLAIN aWxvdmV3aWtpcGVkaWE=
//S: 235 2.7.0 Authentication successful
            System.out.println(inputStream.readLine());
            outputStream.println("EHLO DESKTOP-USQK6PJ");
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
            System.out.println(inputStream.readLine());
//            outputStream.println("STARTTLS");

//            outputStream.println("MAIL FROM:" + from);
//            System.out.println(inputStream.readLine());
//            outputStream.println("EHLO DESKTOP-USQK6PJ");
//System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());
//            System.out.println(inputStream.readLine());

            outputStream.println("AUTH LOGIN");

            System.out.println(inputStream.readLine());
            outputStream.println(new String(Base64.getEncoder().encode(username.getBytes())));
            System.out.println(inputStream.readLine());
            outputStream.println(new String(Base64.getEncoder().encode(password.getBytes())));
            System.out.println(inputStream.readLine());
            outputStream.println("MAIL FROM:<" + from +">");
            System.out.println(inputStream.readLine());
            outputStream.println("RCPT TO:<" + to +">");
            System.out.println(inputStream.readLine());
            outputStream.println("DATA");
            System.out.println(inputStream.readLine());
            outputStream.println("From: "+ from);
            outputStream.println("To: "+ to);
            outputStream.println("Subject: "+ "Assunto");
            outputStream.println("");
            outputStream.println("Oiii");
            outputStream.println(".");
            System.out.println(inputStream.readLine());
            outputStream.println("QUIT");
            System.out.println(inputStream.readLine());

//Date: Mon, 9 Dec 2024 19:30:33 -0300 (GMT-03:00)
//From: de@exemplo.com
//To: para@exemplo.com
//Message-ID: <1156060786.0.1733783433889@DESKTOP-USQK6PJ>
//Subject: Teste SMTP em Java
//MIME-Version: 1.0
//Content-Type: text/plain; charset=UTF-8
//Content-Transfer-Encoding: quoted-printable
//
//Este =C3=A9 um e-mail de teste enviado pelo protocolo SMTP em Java!
//.

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
