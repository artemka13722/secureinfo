public class Shamira extends Lab1 {

    public long[] generate(long p) {
        long[] a = {1, genLong(9)};
        while (a[0] < p) {
            if (powMod((a[0] * a[1]), 1, p - 1) == 1) {
                return a;
            }
            a[0] = a[0] + 1;
        }
        if (a[0] == p) {
            a = generate(p);
        }
        return a;
    }

    public void test() {
        long p = getLongPrime(9);

        long m = 45645647L;
        if (m >= p) {
            System.out.println("error");
        }

        long[] a;
        do {
            a = generate(p);
        } while (a == null);
        assert false;
        long Ca = a[0];
        long Da = a[1];

        long[] b;
        do {
            b = generate(p);
        } while (b == null);
        assert false;
        long Cb = b[0];
        long Db = b[1];

        long x1 = powMod(m, Ca, p);
        long x2 = powMod(x1, Cb, p);
        long x3 = powMod(x2, Da, p);
        long x4 = powMod(x3, Db, p);

        System.out.println(x4);
    }
}
