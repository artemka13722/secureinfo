package lab5;

import lab1.Lab1;

import java.math.BigInteger;

public class Server extends Lab1 {

    public boolean checkSignature(BigInteger hash, long S, BigInteger D, BigInteger N) {
        long w = powMod(S, D.longValue(), N.longValue());

        System.out.println("message hash = " + hash + " w hash = " + w);

        return w == hash.longValue();
    }
}
