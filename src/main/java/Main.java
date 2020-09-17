public class Main {

    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();

        System.out.printf("PowMods %d\n", lab1.powMod(175,235,257));

        long out[] = lab1.gcd(28,19);
        System.out.printf("NOD: %d X: %d Y: %d\n", out[0], out[1], out[2]);

        System.out.printf("DH %d\n", lab1.dh(7,21,23));

        System.out.printf("BSGS %d\n", lab1.bsgs(6, 12, 229));


        System.out.println("/////////////////////////////////////////////");
        System.out.printf("PowMods %d\n", lab1.powMod(lab1.generator(18),lab1.generator(18),lab1.generator(18)));

        out = lab1.gcd(lab1.generator(9),lab1.generator(9));
        System.out.printf("NOD: %d X: %d Y: %d\n", out[0], out[1], out[2]);

        System.out.printf("DH %d\n", lab1.dh(lab1.generator(9),lab1.generator(9),lab1.generator(9)));

        System.out.printf("BSGS %d\n", lab1.bsgs(lab1.generator(9), lab1.generator(9), lab1.generator(9)));
    }
}
