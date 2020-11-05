package lab3;

import lab1.Lab1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SignatureException;

@EqualsAndHashCode(callSuper = true)
@Data
public class ElGamal extends Lab1 {

    BigInteger S;
    long r;
    long p;
    long g;
    long x;
    long y;

    public void createKey() {
        p = getLongPrime(5);
        g = getPRoot(p);

        x = genLongLimit(p - 1);
        y = powMod(g, x, p);
    }

    public void signatureFile(String pathFile, long p, long g, long x) throws IOException {
        long k, k_1;

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(p)));

        long[] EuclidResult;
        do {
            do {
                k = genLongLimit(p - 1);
                EuclidResult = gcd(k, p - 1);
            } while (EuclidResult[0] != 1);
            k_1 = EuclidResult[2] + (p - 1);
        } while (k * k_1 % (p - 1) != 1);

        r = powMod(g, k, p);
        BigInteger u = hash.subtract(BigInteger.valueOf(x * r)).mod(BigInteger.valueOf(p - 1));
        S = u.multiply(BigInteger.valueOf(k_1)).mod(BigInteger.valueOf(p - 1));
    }

    public void checkSignature(String pathFile, long Y_open, long r_open, BigInteger S, long p, long g) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(p)));

        BigInteger Y_R = new BigInteger(String.valueOf(BigInteger.valueOf(Y_open)));
        Y_R = Y_R.modPow(BigInteger.valueOf(r_open), BigInteger.valueOf(p));

        BigInteger R_S = new BigInteger(String.valueOf(r_open));
        R_S = R_S.modPow(S, BigInteger.valueOf(p));

        BigInteger check = Y_R.multiply(R_S).mod(BigInteger.valueOf(p));

        long message = powMod(g, hash.longValue(), p);
        if (check.longValue() != message) {
            throw new SignatureException("digital signature ElGamal is invalid");
        }
    }
}
