import java.util.HashMap;

public class Lab1 {

    //1) y = a^x (mod p)
    public long generator(int pow) {
        long leftLimit = 1L;
        long rightLimit = (long) Math.pow(10, pow);
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public long powMod(long a, long x, long p) {
        long res = 1;
        while (x != 0) {
            if (x % 2 > 0) {
                res = (res * a) % p;
            }
            a = (a * a) % p;
            x >>= 1;
        }
        return res;
    }


    //2) ax + by = gcd(a, b)
    public long[] gcd(long a, long b) {

        long[] u = {a, 1, 0};
        long[] v = {b, 0, 1};

        while (v[0] != 0) {
            long q = u[0] / v[0];
            long[] t = {u[0] % v[0], u[1] - q * v[1], u[2] - q * v[2]};
            u = v;
            v = t;
        }
        return u;
    }

    //3) Diffie-Hellman Key Exchange
    public long dh(long public_num, long private_num, long mod) {
        if (public_num >= mod) {
            throw new RuntimeException("Public key must be less than mod");
        }
        return powMod(public_num, private_num, mod);
    }

    //4) Baby-step Giant-step (a, y, p); y = a^x (mod p)  =>  x = log_a(y) mod p;
    public long bsgs(long a, long y, long p) {
        long n = (long) Math.ceil(Math.sqrt(p - 1));
        long nRound = Math.round(n);

        HashMap<Long, Long> table = new HashMap();

        for (long i = 0; i < nRound; i++) {
            table.put(powMod(a, i, p), i);
        }

        long c = powMod(a, n * (p - 2), p);

        for (long i = 0; i < nRound; i++) {
            long res = (y * powMod(c, i, p)) % p;
            if (table.get(res) != null) {
                return i * n + table.get(res);
            }
        }
        return 0;
    }
}
