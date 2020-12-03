package lab5;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger hash;
        long clientSignature;

        Bulletin bulletin = new Bulletin();

        Client client = new Client(bulletin);
        System.out.println("Answer = " + client.answerQuestion());
        hash = client.hashOfAnswer();
        clientSignature = client.getSignBulletin(hash);
        System.out.println("Client signature = " + clientSignature);

        Server server = new Server();
        System.out.println(server.checkSignature(hash, clientSignature, client.getD(), client.getN()));
    }
}