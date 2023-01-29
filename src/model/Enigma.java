package model;

import analysis.EnigmaKey;

public class Enigma {

    // 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    // A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z

    public Rotor leftRotor;
    public Rotor middleRotor;
    public Rotor rightRotor;
    public Reflector reflector;
    public Plugboard plugboard;

    public char encrypt(char c) {
        return (char)(this.encrypt(c - 65) + 65);
    }

    public String encrypt(String input) {
        char[] intermediate = input.toCharArray();
        char[] encrypted = new char[intermediate.length];
        for (int i = 0; i < intermediate.length; i++) {
            encrypted[i] = this.encrypt(intermediate[i]);
        }
        String output = new String(encrypted);
        return output;
    }

    private int encrypt(int c0) {
        rotate();

        // Plugboard in
        int c1 = this.plugboard.forward(c0);

        // Right to left
        int c2 = rightRotor.forward(c1);
        int c3 = middleRotor.forward(c2);
        int c4 = leftRotor.forward(c3);

        // Reflector
        int c5 = reflector.forward(c4);

        // Left to right
        int c6 = leftRotor.backward(c5);
        int c7 = middleRotor.backward(c6);
        int c8 = rightRotor.backward(c7);

        // Plugboard out
        int c9 = plugboard.forward(c8);

        return c9;
    }

    private void rotate() {
        // If the middle rotor is at notch: double-stepping
        if (middleRotor.isAtNotch()) {
            middleRotor.turnover();
            leftRotor.turnover();
        }
        // If the left rotor is at notch
        else if (rightRotor.isAtNotch()) {
            middleRotor.turnover();
        }
        // Always increment right-most rotor
        rightRotor.turnover();
    }

    public int[] getRotorPostions() {
        int leftPosition = leftRotor.getPosition();
        int middlePosition = middleRotor.getPosition();
        int rightPosition = rightRotor.getPosition();
        return new int[] {leftPosition, middlePosition, rightPosition};
    }

    public int[] getRotorOffsets() {
        int leftOffset = leftRotor.getOffset();
        int middleOffset = middleRotor.getOffset();
        int rightOffset = rightRotor.getOffset();
        return new int[] {leftOffset, middleOffset, rightOffset};
    }

    public String getInfo() {
        String toPrint = "Engima:" + "\n\t"
                       + "Reflector: " + this.reflector.getInfo() + "\n\t"
                       + "Left Motor: " + this.leftRotor.getInfo() + "\n\t"
                       + "Middle Motor: " + this.middleRotor.getInfo() + "\n\t"
                       + "Right Motor: " + this.rightRotor.getInfo() + "\n\t"
                       + "Plugboard: " + this.plugboard.getInfo();
        return toPrint;
    }

    public Enigma(String[] rotors, String reflector, int[] rotorPositions, int[] ringSettings, String plugboardConnections) {
        this.leftRotor = Rotor.Create(rotors[0], rotorPositions[0], ringSettings[0]);
        this.middleRotor = Rotor.Create(rotors[1], rotorPositions[1], ringSettings[1]);
        this.rightRotor = Rotor.Create(rotors[2], rotorPositions[2], ringSettings[2]);
        this.reflector = Reflector.Create(reflector);
        this.plugboard = new Plugboard(plugboardConnections);
    }

    public Enigma(EnigmaKey key) {
        this(key.rotors, "B", key.indicators, key.rings, key.plugboard);
    }

}
