package org.example;

public class SecretGenerator {


    public long generateNewSecret(long secret) {
        //System.out.println(this.prune(100000000));
        //System.out.println(this.mix(42L, 15L));
        long secretMultiplied = secret * 64;
        secret = this.mix(secretMultiplied, secret);
        secret = this.prune(secret);
        long secretDivided = secret / 32;
        secret = this.mix(secretDivided, secret);
        secret = this.prune(secret);
        long secretMultipliedSecond = secret * 2048;
        secret = this.mix(secretMultipliedSecond, secret);
        secret = this.prune(secret);
        return secret;
    }

    public long prune (long num) {
        return num % 16777216;
    }

    public long mix (long givenNumber, long secretNumber) {
        return givenNumber ^ secretNumber;
    }

}
