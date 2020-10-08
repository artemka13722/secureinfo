import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RSA extends Lab1 {

    private byte[] fileByte;
    private final List<Long> longList;

    public RSA() {
        this.longList = new ArrayList<>();
    }

    public byte[] getFIleBytes(String filePath) throws IOException {
        File file = new File(filePath);
        return FileUtils.readFileToByteArray(file);
    }

    public void getFileByBytes(String filePath, byte[] fileBytes) throws IOException {
        FileUtils.writeByteArrayToFile(new File(filePath), fileBytes);
    }

    public void test() throws IOException {
        fileByte = getFIleBytes("download.jpeg");

        long p,q,N;
        do {
            p = getLongPrime(5);
            q = getLongPrime(5);
            N = p * q;
        } while (128 > N);

        long fn = (p - 1) * (q - 1);

        long d = genLongLimit(fn - 1);
        while (gcd(d, fn)[0] != 1){
            d -= 1;
        }

        long c = gcd(fn, d)[2];
        if(c < 0){
            c += fn;
        }

        for (int i = 0; i < fileByte.length; i++) {
            long e = powMod(fileByte[i], d, N);
            longList.add(e);
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream("RSAenc.jpeg"));
        for (int i = 0; i < fileByte.length; i++) {
            pw.println(longList.get(i));
        }
        pw.close();

        byte[] deShift = new byte[fileByte.length];
        for (int i = 0; i < fileByte.length; i++) {
            deShift[i] = (byte) powMod(longList.get(i), c, N);
        }
        getFileByBytes("RSAdec.jpeg", deShift);
    }
}
