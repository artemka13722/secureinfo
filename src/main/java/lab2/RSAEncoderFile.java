package lab2;

import lab1.Lab1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RSAEncoderFile extends Lab1 {

    private byte[] fileByte;
    private final List<Long> longList;

    public RSAEncoderFile() {
        this.longList = new ArrayList<>();
    }

    public void test(String inputFile, String outputFile) throws IOException {
        fileByte = getFIleBytes(inputFile);

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

        PrintWriter pw = new PrintWriter(new FileOutputStream("RSAd.txt"));
        pw.println(d);
        pw.close();

        pw = new PrintWriter(new FileOutputStream("RSAc.txt"));
        pw.println(c);
        pw.close();

        for (int i = 0; i < fileByte.length; i++) {
            long e = powMod(fileByte[i], d, N);
            longList.add(e);
        }

        pw = new PrintWriter(new FileOutputStream("RSAenc.jpeg"));
        for (int i = 0; i < fileByte.length; i++) {
            pw.print(longList.get(i) + "\n");
        }
        pw.close();

        byte[] deShift = new byte[fileByte.length];
        for (int i = 0; i < fileByte.length; i++) {
            deShift[i] = (byte) powMod(longList.get(i), c, N);
        }
        getFileByBytes(outputFile, deShift);
    }
}
