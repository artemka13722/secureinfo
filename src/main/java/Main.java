import lab3.RSA;

public class Main {

    public static void main(String[] args) throws Exception {
        /*lab1.Lab1 lab1 = new lab1.Lab1();

        int pow = 9;
        int powDh = 9;
        long[] out;

        System.out.printf("PowMods %d\n", lab1.powMod(lab1.genLong(pow),lab1.genLong(pow),lab1.genLong(pow)));

        out = lab1.gcd(lab1.genLong(9),lab1.genLong(pow));
        System.out.printf("NOD: %d X: %d Y: %d\n", out[0], out[1], out[2]);

        out = lab1.dh(powDh);
        System.out.printf("DH Zab: %d Zba: %d\n", out[0], out[1]);

        try {
            System.out.printf("BSGS %d\n", lab1.bsgs(lab1.genLong(pow), lab1.genLong(pow), lab1.getLongPrime(pow)));
        } catch (RuntimeException e){
            System.out.println(e.toString());
        }*/

/*        lab2.Elgmal test = new lab2.Elgmal();
        test.getEncryptFile("download.jpeg","ElgamEnc.jpeg");
        test.getDecryptFile("ElgamEnc.jpeg", "ElgamDec.jpeg");

        lab2.Shamira shamira = new lab2.Shamira();
        shamira.test("download.jpeg", "ShamiraDec.jpeg");

        lab2.Verman verman = new lab2.Verman();
        verman.test("download.jpeg", "VernamDec.jpeg");

        lab2.RSA rsa = new lab2.RSA();
        rsa.test("download.jpeg", "RSADec.jpeg");


        lab2.Elgmal elgmal2 = new lab2.Elgmal();
        elgmal2.getEncryptFile("music.mp3","ElgamEnc.mp3");
        elgmal2.getDecryptFile("ElgamEnc.mp3", "ElgamDec.mp3");

        lab2.Shamira shamira2 = new lab2.Shamira();
        shamira2.test("music.mp3", "ShamiraDec.mp3");

        lab2.Verman verman2 = new lab2.Verman();
        verman2.test("music.mp3", "VernamDec.mp3");

        lab2.RSA rsa2 = new lab2.RSA();
        rsa2.test("music.mp3", "RSADec.mp3");*/

        RSA rsa = new RSA();
        rsa.test("music.mp3");

/*        ElGamal elGamal = new ElGamal();
        elGamal.test("test.txt");*/

/*        DSA.generateParameters();

        DSA A = new DSA();
        DSA B = new DSA();

        A.signFile("music.mp3");
        B.checkSign("music.mp3", A.getR(), A.getS(), A.getY());*/
    }
}
