package lab3;

import lab1.Lab1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.SignatureException;

@EqualsAndHashCode(callSuper = true)
@Data
public class RSA extends Lab1 {

    private long N;
    private long c;
    private long d;

    public void createKey() throws IOException, SignatureException {

        long p = getLongPrime(4);
        long q = getLongPrime(4);
        N = p * q;

        long fn = (p - 1) * (q - 1);

        d = genLongLimit(fn - 1);
        while (gcd(d, fn)[0] != 1) {
            d -= 1;
        }

        c = gcd(fn, d)[2];
        if (c < 0) {
            c += fn;
        }
    }

    private void saveKey(long S) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("RSAkey.txt"));
        pw.println("s:" + S);
        pw.println("d:" + d);
        pw.println("n:" + N);
        pw.close();
    }

    public long signatureFileMD5(String pathFile, long c, long n) throws IOException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(n)));

        long S = powMod(hash.longValue(), c, n);
        saveKey(S);
        return S;
    }

    public void checkSignature(String pathFile, long s, long d, long n) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(n)));

        long w = powMod(s, d, n);

        if (w != hash.longValue()) {
            throw new SignatureException("digital signature RSA is invalid");
        }
    }
}
