package com.Connection;
 import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ServerConection {
        public static String[] consultServer(String messageToSend, int numberOfAnswers) {
            for (int j1 = 0; j1 < 3; j1 = j1 + 1) {
                String[] respuestaDelServidor = ServerConection.ConsultServerDeeper(messageToSend, numberOfAnswers);
                if (respuestaDelServidor != null) {
                    return respuestaDelServidor;
                }
            }
            return null;
        }

        private static String[] ConsultServerDeeper(String messageToSend, int numberOfAnswers) {
            Socket socket = null;
            DataOutputStream out;
            DataInputStream in;
            String[] outString = null;
            try {
                //initiate
                socket = new Socket();
                socket.connect(new InetSocketAddress(InetAddress.getByName("192.168.100.16"), 8080), 5000);
                socket.setSoTimeout(10000);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                //key to connect
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(512);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                byte[] publicKeyBytes = publicKey.getEncoded();
                out.writeInt(publicKeyBytes.length);
                out.write(publicKeyBytes);

                //connect server
                int messageLength = in.readInt();
                byte[] receivedBytes = new byte[messageLength];
                in.read(receivedBytes);
                Cipher rsa;
                rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                rsa.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] DesencriptedBytes = receivedBytes; //rsa.doFinal(receivedBytes);
                Key key = new SecretKeySpec(DesencriptedBytes, 0, DesencriptedBytes.length, "AES");
                Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
                aes.init(Cipher.ENCRYPT_MODE, key);
                ServerConection.sendMessage(messageToSend, out, aes);
                aes.init(Cipher.DECRYPT_MODE, key);
                outString = new String[numberOfAnswers];
                for (int j1 = 0; j1 < numberOfAnswers; j1 = j1 + 1) {
                    outString[j1] = ServerConection.receiveMessage(in, aes);
                }
                return outString;
            } catch (Exception e) {
                if ((outString == null) || (outString[0] == null)) {
                    return null;
                } else {
                    return outString;
                }
            } finally {
                try {
                    socket.close();
                } catch (Exception e2) {
                }
            }
        }

        private static void sendMessage(String message, DataOutputStream outStream, Cipher aes) throws Exception {
            byte[] encriptedMessage = message.getBytes(); //aes.doFinal(message.getBytes());
            outStream.writeInt(encriptedMessage.length);
            outStream.write(encriptedMessage);
        }

        private static String receiveMessage(DataInputStream inStream, Cipher aes) throws Exception {
            int inMessageLong = inStream.readInt();
            byte[] inBytes = new byte[inMessageLong];
            inStream.readFully(inBytes);
            byte[] DesenciptedBytes = aes.doFinal(inBytes);
            return new String(inBytes);//(DesenciptedBytes);
        }
}
