import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Elgmal extends Lab1 {

    private long tmp;
    private long p;
    private long g;

    private long c;
    private long d;

    private long k;
    private long r;

    private byte[] fileByte;
    // TODO: 01.10.2020 а нужен ли вообще в этой жизни этот лист? Может сделать обычный лонг лист и все? 
    //private final List<byte[]> listEncrypt;
    private final List<Long> longList;

    public long getR() {
        return r;
    }

    public Elgmal() {
        //this.listEncrypt = new ArrayList<>();
        this.longList = new ArrayList<>();
    }

    public byte[] getFIleBytes(String filePath) throws IOException {
        File file = new File(filePath);
        return FileUtils.readFileToByteArray(file);
    }

    public void getFileByBytes(String filePath, byte[] fileBytes) throws IOException {
        FileUtils.writeByteArrayToFile(new File(filePath), fileBytes);
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public void getEncryptFile(String inputFile, String encFile) throws IOException {
        fileByte = getFIleBytes(inputFile);
        long maxM = 128;
        do {
            p = getLongPrime(5);
        } while (maxM >= p);

        g = getPRoot(p);
        c = genLongLimit(p - 1);
        d = powMod(g, c, p);

        k = genLongLimit(p - 2);
        r = powMod(g, k, p);

        tmp = powMod(d, k, p);

        for (int i = 0; i < fileByte.length; i++) {
            long e = powMod(fileByte[i] * tmp, 1, p);
            //listEncrypt.add(longToBytes(e));
            longList.add(e);
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(encFile));
        for (int i = 0; i < fileByte.length; i++) {
//            pw.println(bytesToLong(listEncrypt.get(i)));
            pw.println(longList.get(i));
        }
        pw.close();
    }

    public void getDecryptFile(String fileEnc, String fileDec) throws IOException {
        //List<byte[]> listEncrypt = new ArrayList<>();
        List<Long> longEncrypt = new ArrayList<>();

        File file = new File(fileEnc);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            //listEncrypt.add(longToBytes(Long.parseLong(line)));
            longEncrypt.add(Long.parseLong(line));
        }
        br.close();

        tmp = powMod(r, p - 1 - c, p);
        byte[] deShift = new byte[fileByte.length];
        for (int i = 0; i < fileByte.length; i++) {
            //long tmp2 = bytesToLong(listEncrypt.get(i));
            long tmp2 = longEncrypt.get(i);
            deShift[i] = (byte) powMod(tmp * tmp2, 1, p);
        }
        getFileByBytes(fileDec, deShift);
    }
}
