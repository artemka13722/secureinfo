public class Main {

    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();

        int pow = 9;
        int powDh = 9;
        long out[];

        System.out.printf("PowMods %d\n", lab1.powMod(lab1.genLong(pow),lab1.genLong(pow),lab1.genLong(pow)));

        out = lab1.gcd(lab1.genLong(9),lab1.genLong(pow));
        System.out.printf("NOD: %d X: %d Y: %d\n", out[0], out[1], out[2]);

        out = lab1.dh(powDh);
        System.out.printf("DH Zab: %d Zba: %d\n", out[0], out[1]);

        try {
            System.out.printf("BSGS %d\n", lab1.bsgs(lab1.genLong(pow), lab1.genLong(pow), lab1.getLongPrime(pow)));
        } catch (RuntimeException e){
            System.out.println("BSGS нет ответа");
        }

    }
}
