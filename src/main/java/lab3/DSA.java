package lab3;

import lab1.Lab1;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Random;

public class DSA {
    private static BigInteger P;
    private static BigInteger Q;
    private static BigInteger A;

    private BigInteger x;
    @Getter
    private BigInteger y;

    @Getter
    private BigInteger r;
    @Getter
    private BigInteger s;

    private void saveKey() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("DSAkey.txt"));
        pw.println("r:" + r);
        pw.println("s:" + s);
        pw.println("y:" + y);
        pw.close();
    }

    public static void generateParameters() {
        BigInteger b, temp_P;
        do {
            do {
                Q = getRandomBigInteger(16);
            } while (!Q.isProbablePrime(100));
            do {
                P = getRandomBigInteger(16);
            } while (!P.isProbablePrime(100));

            b = P.subtract(BigInteger.valueOf(1)).divide(Q);
            temp_P = b.multiply(Q).add(BigInteger.valueOf(1));
        } while (!temp_P.equals(P));

        do {
            A = getRandomBigInteger(16);
        } while (!A.modPow(Q, P).equals(BigInteger.ONE) || A.equals(BigInteger.ONE));
    }

    public DSA() {
        SecureRandom sRand = new SecureRandom();
        sRand.setSeed(System.currentTimeMillis());
        do {
            x = new BigInteger(String.valueOf(sRand.nextInt(Q.intValue()) + 1));
            y = A.modPow(x, P);
        } while (y.compareTo(BigInteger.valueOf(1)) <= 0);
    }

    public void signatureFile(String pathFile) throws IOException {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(Q)));
        long k;
        do {
            k = srand.nextInt(Q.intValue());
            r = A.modPow(BigInteger.valueOf(k), P).mod(Q);
            s = x.multiply(r).add(hash.multiply(BigInteger.valueOf(k))).mod(Q);
        } while (r.equals(BigInteger.ZERO) || s.equals(BigInteger.ZERO));
        saveKey();
    }

    public void checkSignature(String pathFile, BigInteger r_sign, BigInteger s_sign, BigInteger y_open) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(Q)));

        if (r_sign.compareTo(Q) > 0 || s_sign.compareTo(Q) > 0) {
            throw new SignatureException("digital signature DSA is invalid");
        }
        long[] EuclidResult;
        BigInteger h_1;

        do {
            EuclidResult = Lab1.gcd(hash.longValue(), Q.longValue());
            h_1 = new BigInteger(String.valueOf(EuclidResult[2] + Q.longValue()));
        } while (!hash.multiply(h_1).mod(Q).equals(BigInteger.ONE));

        BigInteger u1 = s_sign.multiply(h_1).mod(Q);
        BigInteger u2 = r_sign.negate().multiply(h_1).mod(Q);

        BigInteger v = (A.pow(u1.intValue()).multiply(y_open.pow(u2.intValue())).mod(P)).mod(Q);

        if (v.compareTo(r_sign) != 0) {
            throw new SignatureException("digital signature DSA is invalid");
        }
    }

    public static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = new BigInteger(numBits, new Random()); //Give you a number between 0 and 2^numBits - 1
        return number.setBit(0);
    }
}