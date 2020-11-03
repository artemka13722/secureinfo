package lab1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Lab1 {

    public byte[] getFIleBytes(String filePath) throws IOException {
        File file = new File(filePath);
        return FileUtils.readFileToByteArray(file);
    }

    public void getFileByBytes(String filePath, byte[] fileBytes) throws IOException {
        FileUtils.writeByteArrayToFile(new File(filePath), fileBytes);
    }

    public static long getPRoot(long p) {
        for (long i = 0; i < p; i++)
            if (isPRoot(p, i))
                return i;
        return 0;
    }

    public static boolean isPRoot(long p, long a) {
        if (a == 0 || a == 1)
            return false;
        long last = 1;

        Set<Long> set = new HashSet<>();
        for (long i = 0; i < p - 1; i++) {
            last = (last * a) % p;
            if (set.contains(last)) // Если повтор
                return false;
            set.add(last);
        }
        return true;
    }

    protected boolean isPrime(long p) {
        if (p <= 1) return false;

        long b = (long) Math.pow(p, 0.5);

        for (int i = 2; i <= b; i++) {
            if ((p % i) == 0) {
                return true;
            }
        }
        return false;
    }

    public long getLongPrime(long pow) {
        long leftLimit = 1L;
        long rightLimit = (long) Math.pow(10, pow);
        long rand;
        do {
            rand = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        } while (isPrime(rand));
        return rand;
    }

    public long genLong(int pow) {
        long leftLimit = 1L;
        long rightLimit = (long) Math.pow(10, pow);
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public long genLongLimit(long limit) {
        long leftLimit = 1L;
        return leftLimit + (long) (Math.random() * (limit - leftLimit));
    }

    //1) y = a^x (mod p)
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
    public static long[] gcd(long a, long b) {
        if (a < b) {
            long temp;
            temp = a;
            a = b;
            b = temp;
        }

        long[] u = {a, 1, 0};
        long[] v = {b, 0, 1};

        while (v[0] != 0) {
            long q = u[0] / v[0];
            long[] t = {u[0] % v[0], u[1] - q * v[1], u[2] - q * v[2]};
            u = v;
            v = t;
        }
        if((a*u[1] + b*u[2]) != u[0]){
            throw new RuntimeException("Error euclid algorithm");
        }
        return u;
    }

    //3) Diffie-Hellman Key Exchange
    public long[] dh(long pow) {
        long q;
        long p;
        long Xa;
        long Xb;
        long g;

        do {
            do {
                q = getLongPrime(pow);
                p = 2 * q + 1;
            } while (isPrime(p));

            Xa = genLongLimit(p);
            Xb = genLongLimit(p);

            g = genLongLimit(p - 1);
        } while (powMod(g, q, p) != 1);

        long Ya = powMod(g, Xa, p);
        long Yb = powMod(g, Xb, p);

        long Zab = powMod(Yb, Xa, p);
        long Zba = powMod(Ya, Xb, p);

        //обратить внимание на ключи

        return new long[]{Zab, Zba};
    }

    //4) Baby-step Giant-step (a, y, p);
    // y = a^x (mod p)  =>  x = log_a(y) mod p;
    public long bsgs(long a, long y, long p) {
        if(y >= p){
            throw new RuntimeException("error");
        }

        long n = (long) Math.ceil(Math.sqrt(p));

        HashMap<Long, Long> table = new HashMap();

        //map of pairs a^{1...m} (mod p), baby step
        for (long i = 0; i < n - 1; i++) {
            table.put(powMod(a, i, p), i);
        }
        //Fermat's Little Theorem
        long c = powMod(a, n * (p - 2), p);

        //giant step
        for (long i = 1; i < n; i++) {
            long res = (y * powMod(c, i, p)) % p;
            if (table.get(res) != null) {

                /*System.out.println("a");
                System.out.println(a);
                System.out.println("y");
                System.out.println(y);
                System.out.println("p");
                System.out.println(p);

                System.out.println("a^x%p = y");
                System.out.println(powMod(a, i * n + table.get(res), p));*/
                long check = powMod(a, i * n + table.get(res), p);

                if(check != y){
                    throw new RuntimeException("это провал века");
                }
                return i * n + table.get(res);
            }
        }
        throw new RuntimeException("нет ответа");
    }
}
