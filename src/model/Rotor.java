package model;

import java.util.HashSet;
import java.util.Set;

public class Rotor {

    private String name;
    private int[] forwardWiring;
    private int[] backwardWiring;
    private int rotorPosition;
    private int ringSetting;
    private Set<Integer> notchPosition = new HashSet<Integer>();
    private int offset;

    public int forward(int c) {
        return encipher(c, this.rotorPosition, this.ringSetting, this.forwardWiring);
    }

    public int backward(int c) {
        return encipher(c, this.rotorPosition, this.ringSetting, this.backwardWiring);
    }

    public boolean isAtNotch() {
        return this.notchPosition.contains(this.rotorPosition);
    }

    public void turnover() {
        this.rotorPosition = (this.rotorPosition + 1) % 26;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return rotorPosition;
    }

    public int getRingSetting() {
        return ringSetting;
    }

    public int getOffset() {
        return offset;
    }

    public Set<Integer> getNotch() {
        return this.notchPosition;
    }

    private static int[] decodeWiring(String encoding) {
        char[] charWiring = encoding.toCharArray();
        int[] wiring = new int[charWiring.length];
        for (int i = 0; i < charWiring.length; i++) {
            wiring[i] = charWiring[i] - 65;
        }
        return wiring;
    }

    private static int[] inverseWiring(int[] wiring) {
        int[] inverse = new int[wiring.length];
        for (int i = 0; i < wiring.length; i++) {
            int forward = wiring[i];
            inverse[forward] = i;
        }
        return inverse;
    }

    private static int encipher(int k, int pos, int ring, int[] mapping) {
        int shift = pos - ring;
        return (mapping[(k + shift + 26) % 26] - shift + 26) % 26;
    }

    public static Rotor Create(String name, int rotorPosition, int ringSetting) {
        switch (name) {
            case "I": 
                return new Rotor("I",   "EKMFLGDQVZNTOWYHXUSPAIBRCJ", rotorPosition, ringSetting, 16);
            case "II":
                return new Rotor("II",  "AJDKSIRUXBLHWTMCQGZNPYFVOE", rotorPosition, ringSetting, 4);
            case "III":
                return new Rotor("III", "BDFHJLCPRTXVZNYEIWGAKMUSQO", rotorPosition, ringSetting, 21);
            case "IV":
                return new Rotor("IV",  "ESOVPZJAYQUIRHXLNFTGKDCMWB", rotorPosition, ringSetting, 9);
            case "V":
                return new Rotor("V",   "VZBRGITYUPSDNHLXAWMJQOFECK", rotorPosition, ringSetting, 25);
            case "VI":
                return new Rotor("VI",  "JPGVOUMFYQBENHZRDKASXLICTW", rotorPosition, ringSetting, 12, 25);
            case "VII":
                return new Rotor("VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT", rotorPosition, ringSetting, 12, 25);
            case "VIII":
                return new Rotor("VIII","FKQHTLXOCBJSPDZRAMEWNIUYGV", rotorPosition, ringSetting, 12, 25);
            default:
                return new Rotor("Iden","ABCDEFGHIJKLMNOPQRSTUVWXYZ", rotorPosition, ringSetting, 0);
        }
    }

    public String getInfo() {
        return "Name: " + this.getName() + " Position: " + this.getPosition() + " Ring: " + this.ringSetting + " Notch: " + this.notchPosition;
    }

    public Rotor(String name, String encoding, int rotorPosition, int ringSetting, int ... notchPosition) {
        this.name = name;
        this.forwardWiring = decodeWiring(encoding);
        this.backwardWiring = inverseWiring(this.forwardWiring);
        this.rotorPosition = rotorPosition;
        this.ringSetting = ringSetting;
        for (int notch : notchPosition) {
            this.notchPosition.add(notch);
        }
        this.offset = this.ringSetting - this.rotorPosition;
    }

}
