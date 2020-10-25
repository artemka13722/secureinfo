package lab2;

import lab1.Lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void test(String inputFile, String outputFile) throws IOException {
        long p = getLongPrime(9);

        if (128 >= p) {
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

       /* long x1 = powMod(m, Ca, p);
        long x2 = powMod(x1, Cb, p);
        long x3 = powMod(x2, Da, p);
        long x4 = powMod(x3, Db, p);
        System.out.println(x4);*/

        PrintWriter pw = new PrintWriter(new FileOutputStream("ShamiraCa.txt"));
        pw.println(Ca);
        pw.close();

        pw = new PrintWriter(new FileOutputStream("ShamiraDa.txt"));
        pw.println(Cb);
        pw.close();

        pw = new PrintWriter(new FileOutputStream("ShamiraCb.txt"));
        pw.println(Da);
        pw.close();

        pw = new PrintWriter(new FileOutputStream("ShamiraDb.txt"));
        pw.println(Db);
        pw.close();

        x1(inputFile, "x1.jpeg", Ca, p);
        x2("x1.jpeg", "x2.jpeg", Cb, p);
        x3("x2.jpeg", "x3.jpeg", Da, p);
        x4("x3.jpeg", outputFile, Db, p);
    }

    public void x1(String inputFile, String outputFile, long Ca, long p) throws IOException {
        byte[] fileByte = getFIleBytes(inputFile);
        List<Long> x1Key = new ArrayList<>();

        for (byte b : fileByte) {
            x1Key.add(powMod(b, Ca, p));
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile));
        for (Long aLong : x1Key) {
            pw.println(aLong);
        }
        pw.close();
    }

    public void x2(String inputFile, String outputFile, long Cb, long p) throws IOException {
        List<Long> x1Key = new ArrayList<>();
        List<Long> x2Key = new ArrayList<>();

        File file = new File(inputFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            x1Key.add(Long.parseLong(line));
        }
        br.close();

        for (Long aLong : x1Key) {
            x2Key.add(powMod(aLong, Cb, p));
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile));
        for (Long aLong : x2Key) {
            pw.println(aLong);
        }
        pw.close();
    }

    public void x3(String inputFile, String outputFile, long Da, long p) throws IOException {
        List<Long> x2Key = new ArrayList<>();
        List<Long> x3Key = new ArrayList<>();

        File file = new File(inputFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            x2Key.add(Long.parseLong(line));
        }
        br.close();

        for (Long aLong : x2Key) {
            x3Key.add(powMod(aLong, Da, p));
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile));
        for (Long aLong : x3Key) {
            pw.println(aLong);
        }
        pw.close();
    }

    public void x4(String inputFile, String outputFile, long Db, long p) throws IOException {
        List<Long> x3Key = new ArrayList<>();

        File file = new File(inputFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            x3Key.add(Long.parseLong(line));
        }
        br.close();

        byte[] deShift = new byte[x3Key.size()];
        for (int i = 0; i < x3Key.size(); i++) {
            deShift[i] = (byte) powMod(x3Key.get(i), Db, p);
        }
        getFileByBytes(outputFile, deShift);
    }
}
