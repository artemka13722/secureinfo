package lab3;

import lab1.Lab1;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class RSA2 extends Lab1 {
    public void test(String inputFile) throws IOException {
        byte[] fileByte = getFIleBytes(inputFile);

        long p,q,N;
        do {
            p = getLongPrime(4);
            q = getLongPrime(4);
            N = p * q;
        } while (128 > N);

        long fn = (p - 1) * (q - 1);

        //d взаимнопростое с fn
        long d = genLongLimit(fn - 1);
        while (gcd(d, fn)[0] != 1){
            d -= 1;
        }

        long c = gcd(fn, d)[2];
        if(c < 0){
            c += fn;
        }

        String md5 = DigestUtils.md5Hex(fileByte);
        long y = md5.hashCode();

        long s = powMod(y, c, N);
        System.out.println(y);

        long w = powMod(s, d, N);
        System.out.println(w);

    }
}
