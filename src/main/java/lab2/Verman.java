package lab2;

import lab1.Lab1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Verman extends Lab1 {
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void test(String inputFile, String outfutFile) throws IOException {
        byte[] fileByte = getFIleBytes(inputFile);
        char[] achText = new char[fileByte.length];

        for (int i = 0; i < fileByte.length; i++) {
            achText[i] = (char) fileByte[i];
        }

        char[] achKey = new char[achText.length];
        char[] achResult = new char[achText.length];
        Random random = new Random();
        for (int i = 0; i < achText.length; i++) {
            achKey[i] = (char) random.nextInt(Character.MAX_VALUE);
            achResult[i] = (char) (achText[i] ^ achKey[i]);
        }

        byte[] shifr = new byte[fileByte.length];
        for (int i = 0; i < shifr.length; i++) {
            shifr[i] = (byte) achResult[i];
        }
        getFileByBytes("encVernam.jpeg", shifr);

        PrintWriter pw = new PrintWriter(new FileOutputStream("varmanKey.txt"));
        pw.println(String.valueOf(achKey));
        pw.close();

        char[] achDecrypt = new char[achText.length];
        for (int i = 0; i < achText.length; i++) {
            achDecrypt[i] = (char) (achResult[i] ^ achKey[i]);
        }

        byte[] dehifr = new byte[fileByte.length];
        for (int i = 0; i < achDecrypt.length; i++) {
            dehifr[i] = (byte)achDecrypt[i];
        }

        getFileByBytes(outfutFile, dehifr);
    }

    /*public void getEncFile(String input, String output) throws IOException {
        byte[] fileByte = getFIleBytes(input);

        setLength(fileByte.length);

        char[] achText = new char[getLength()];

        for (int i = 0; i < getLength(); i++) {
            achText[i] = (char) fileByte[i];
        }

        char[] achKey = new char[getLength()];
        char[] achResult = new char[getLength()];
        Random random = new Random();
        for (int i = 0; i < getLength(); i++) {
            achKey[i] = (char) random.nextInt(Character.MAX_VALUE);
            achResult[i] = (char) (achText[i] ^ achKey[i]);
        }

        byte[] shifr = new byte[getLength()];
        for (int i = 0; i < getLength(); i++) {
            shifr[i] = (byte) achResult[i];
        }
        getFileByBytes(output, shifr);

        PrintWriter pw = new PrintWriter(new FileOutputStream("varmanKey.txt"));
        pw.println(String.valueOf(achKey));
        pw.close();
    }*/

    /*public void getDecFile(String input, String output) throws IOException {
        byte[] fileByte = getFIleBytes(input);
        char[] achResult = new char[getLength()];

        File file = new File("varmanKey.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        br.close();
        char[] achKey = line.toCharArray();

        for (int i = 0; i < getLength(); i++) {
            achResult[i] = (char) fileByte[i];
        }

        char[] achDecrypt = new char[getLength()];
        for (int i = 0; i < getLength(); i++) {
            achDecrypt[i] = (char) (achResult[i] ^ achKey[i]);
        }

        byte[] dehifr = new byte[getLength()];
        for (int i = 0; i < getLength(); i++) {
            dehifr[i] = (byte)achDecrypt[i];
        }

        getFileByBytes(output, dehifr);
    }*/
}
