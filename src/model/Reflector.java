package model;

public class Reflector {

    private String name;
    private int[] wiring;

    public int forward(int c) {
        return this.wiring[c];
    }

    protected static int[] decodeWiring(String encoding) {
        char[] charWiring = encoding.toCharArray();
        int[] wiring = new int[charWiring.length];
        for (int i = 0; i < charWiring.length; i++) {
            wiring[i] = charWiring[i] - 65;
        }
        return wiring;
    }

    public static Reflector Create(String name) {
        switch (name) {
            case "B":
                return new Reflector("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
            case "C":
                return new Reflector("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
            default:
                return new Reflector("A", "ZYXWVUTSRQPONMLKJIHGFEDCBA");
        }
    }

    public String getInfo() {
        return this.name;
    }

    public Reflector(String name, String encoding) {
        this.name = name;
        this.wiring = decodeWiring(encoding);
    }

}
