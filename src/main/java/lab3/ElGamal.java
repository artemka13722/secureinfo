package lab3;

import lab1.Lab1;

import java.io.IOException;

public class ElGamal extends Lab1 {

    public void test() throws IOException {
        long p, q;
        do {
            q = getLongPrime(9);
            p = 2 * q + 1;
        } while (isPrime(p));

        long g;
        for (g = 2; g <= 10; ++g) {
            if (powMod(g, q, p) == 1) break;
        }

        long z = genLong(9) % (p - 2) + 2;
        long w = powMod(g, z, p);

        long h = hashy("test.txt");

        long k;

        //todo спиздено криво
        do {
            k = (genLong(9) % (p - 1 - 2) + 1) | 1;
        } while (gcd(p - 1, k)[2] != 1);


        long r = powMod(g, k, p);
        long u = (h - z * r) % (p - 1);
        if(u < 0){
            u += p - 1;
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
