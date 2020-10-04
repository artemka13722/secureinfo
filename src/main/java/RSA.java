public class RSA extends Lab1 {

    // в 1 лабе эт есть, но пока пусть будь так

    public void test() {
        long p = getLongPrime(4);
        long q = getLongPrime(4);

        long n = p * q; // module

        long FiN = (p - 1) * (q - 1);

        long d;
        long[] out;
        long c;

        do {
            do {
                d = genLongLimit(FiN);
            } while (gcd(d, FiN)[0] == 1);


            //понять что нужно отправить в алгоритм евклида чтобы получить с
            out = gcd(FiN, d);
            c = out[1];
        } while (powMod(c * d, 1, FiN) != 1);


        System.out.println("чудо");
    }
}
