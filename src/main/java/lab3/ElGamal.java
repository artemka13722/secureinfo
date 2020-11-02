package lab3;

import lab1.Lab1;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class ElGamal extends Lab1 {

    public void test(String inputFile) throws IOException {
        byte[] fileByte = getFIleBytes(inputFile);

        long p;
        long maxM = 128;

        do {
            p = getLongPrime(6);
        } while (maxM >= p);

        long g = getPRoot(p);

        long x = genLongLimit(p - 1);
        long y = powMod(g, x, p);

        String md5 = DigestUtils.md5Hex(fileByte);
        long h = md5.hashCode();
        if (h > p) {
            System.out.println("error");
        }

        long k = getPRoot(p - 1);

        long r = powMod(g, k, p);
        long u = powMod((h - x * r), 1, p - 1);
        long s = 1 / k * powMod(u, 1, p - 1); // тут 100 процентов ошибка
    }
}
