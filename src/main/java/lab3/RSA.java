package lab3;

import lab1.Lab1;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SignatureException;

public class RSA extends Lab1 {

    public void test(String inputFile) throws IOException, SignatureException {

        long p,q,N;
        do {
            p = getLongPrime(4);
            q = getLongPrime(4);
            N = p * q;
        } while (128 > N);

        long fn = (p - 1) * (q - 1);

        long d = genLongLimit(fn - 1);
        while (gcd(d, fn)[0] != 1){
            d -= 1;
        }

        long c = gcd(fn, d)[2];
        if(c < 0){
            c += fn;
        }

        long s = signFileMD5(inputFile, c, N);
        checkSign(inputFile, s, d, N);
    }

    public long signFileMD5(String pathFile, long c, long n) throws IOException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(n)));

        long S = powMod(hash.longValue(), c, n);
        return S;
    }

    public void checkSign(String pathFile, long s, long d, long n) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(n)));

        long w = powMod(s, d, n);

        System.out.println("message = "+hash+" check = "+w);

        if (w != hash.longValue()) {
            throw new SignatureException("digital signature RSA is invalid");
        }
    }
}
