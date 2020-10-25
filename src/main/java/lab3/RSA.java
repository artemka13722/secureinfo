package lab3;

import lab1.Lab1;

import java.io.IOException;

public class RSA extends Lab1 {

    public void test() throws IOException {
        long p = getLongPrime(4);
        long q = getLongPrime(4);

        long n = p * q;
        long f = (p - 1) * (q - 1);

        long d = genLongLimit(f - 1);
        while (gcd(d, f)[0] != 1) {
            d -= 1;
        }

        long c = gcd(f, d)[2];
        if (c < 0) {
            c += f;
        }

        long h = hashy("test.txt");

        long s = powMod(h, c, n) % f;

        long w = powMod(s,d,n) % f;

        if(w == h){
            System.out.println("Signature is correct");
        }

    }

    public long hashy(String filename) throws IOException {
        byte[] fileBytes = getFIleBytes(filename);

        long res = 0, p = 1;

        for (int i = 0; i < fileBytes.length; i++, p *= 3733) {
            res += fileBytes[i] * p % 2 ^ 63;
        }
        return res;
    }
}
