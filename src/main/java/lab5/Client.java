package lab5;

import lab1.Lab1;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.Scanner;

public class Client extends Lab1 {
    private BigInteger с;

    @Getter
    private BigInteger d;

    @Getter
    private BigInteger n;

    private BigInteger r;

    private final Bulletin bulletin;

    private String message;

    public Client(Bulletin bulletin) {
        this.bulletin = bulletin;
        RSA rsa = new RSA();
        с = new BigInteger(String.valueOf(rsa.getC()));
        d = new BigInteger(String.valueOf(rsa.getD()));
        n = new BigInteger(String.valueOf(rsa.getN()));
        System.out.println("C = " + rsa.getC() + " D = " + rsa.getD() + " N = " + rsa.getN());
    }

    public String answerQuestion() {
        System.out.println(bulletin.getFullQuestion());
        for (int i = 0; i < bulletin.getPossibleAnswer().length; i++) {
            System.out.println(i + 1 + "." + bulletin.getPossibleAnswer()[i] + " ");
        }
        Scanner in = new Scanner(System.in);
        System.out.print("-> ");
        int choice = in.nextInt();
        message = bulletin.getFullQuestion() + " - " + bulletin.getPossibleAnswer()[choice - 1];
        return message;
    }

    public BigInteger hashOfAnswer() {
        String checksumMD5 = DigestUtils.md5Hex(message);
        BigInteger hash = new BigInteger(checksumMD5, 16);
        return hash.mod(new BigInteger(String.valueOf(n)));
    }

    public long getSignBulletin(BigInteger hash) {
        return powMod(hash.longValue(), с.longValue(), n.longValue());
    }
}

