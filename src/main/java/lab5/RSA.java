package lab5;

import lab1.Lab1;
import lombok.Getter;

@Getter
public class RSA extends Lab1 {

    private long c;
    private long d;
    private long n;

    public RSA() {
        long p, q;

        p = getLongPrime(4);
        q = getLongPrime(4);
        n = p * q;

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
}
