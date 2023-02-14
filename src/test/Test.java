package test;

import model.Enigma;

class Test {

    // 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    // A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z

    public static void main(String[] args) {
        encryptTest();
        decryptTest();
        plugboardTest();
    }

    public static void test(String a, String b) {
        if (a.equals(b)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }

    public static void encryptTest() {
        // Basic settings
        Enigma e = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,0,0}, new int[] {0,0,0}, "");
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = "BJELRQZVJWARXSNBXORSTNCFMEYHCXTGYJFLINHNXSHIUNTHEORXOPLOVFEKAGADSPNPCMHRVZCYECDAZIHVYGPITMSRZKGGHLSRBLHL";
        String ciphertext = e.encrypt(input);
        test(ciphertext, output);

        // Varied rotors
        e = new Enigma(new String[] {"VII", "V", "IV"}, "B", new int[] {10,5,12}, new int[] {1,2,3}, "");
        ciphertext = e.encrypt(input);
        output = "FOTYBPKLBZQSGZBOPUFYPFUSETWKNQQHVNHLKJZZZKHUBEJLGVUNIOYSDTEZJQHHAOYYZSENTGXNJCHEDFHQUCGCGJBURNSEDZSEPLQP";
        test(ciphertext, output);

        // Long input
        e = new Enigma(new String[] {"III", "VI", "VIII"}, "B", new int[] {3,5,9}, new int[] {11,13,19}, "");
        char[] longInput = new char[500];
        for (int i = 0; i < 500; i++) longInput[i] = 'A';
        input = new String(longInput);
        ciphertext = e.encrypt(input);
        output = "YJKJMFQKPCUOCKTEZQVXYZJWJFROVJMWJVXRCQYFCUVBRELVHRWGPYGCHVLBVJEVTTYVMWKJFOZHLJEXYXRDBEVEHVXKQSBPYZN" +
                "IQDCBGTDDWZQWLHIBQNTYPIEBMNINNGMUPPGLSZCBRJULOLNJSOEDLOBXXGEVTKCOTTLDZPHBUFKLWSFSRKOMXKZELBDJNRUDUCO" +
                "TNCGLIKVKMHHCYDEKFNOECFBWRIEFQQUFXKKGNTSTVHVITVHDFKIJIHOGMDSQUFMZCGGFZMJUKGDNDSNSJKWKENIRQKSUUHJYMIG" +
                "WWNMIESFRCVIBFSOUCLBYEEHMESHSGFDESQZJLTORNFBIFUWIFJTOPVMFQCFCFPYZOJFQRFQZTTTOECTDOOYTGVKEWPSZGHCTQRP" +
                "GZQOVTTOIEGGHEFDOVSUQLLGNOOWGLCLOWSISUGSVIHWCMSIUUSBWQIGWEWRKQFQQRZHMQJNKQTJFDIJYHDFCWTHXUOOCVRCVYOHL";
        test(ciphertext, output);
        
    }

    public static void decryptTest() {
        // Basic settings
        Enigma e = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,0,0}, new int[] {0,0,0}, "");
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = "BJELRQZVJWARXSNBXORSTNCFMEYHCXTGYJFLINHNXSHIUNTHEORXOPLOVFEKAGADSPNPCMHRVZCYECDAZIHVYGPITMSRZKGGHLSRBLHL";
        String plaintext = e.encrypt(output);
        test(plaintext, input);

        // Varied rotors
        e = new Enigma(new String[] {"VII", "V", "IV"}, "B", new int[] {10,5,12}, new int[] {1,2,3}, "");
        output = "FOTYBPKLBZQSGZBOPUFYPFUSETWKNQQHVNHLKJZZZKHUBEJLGVUNIOYSDTEZJQHHAOYYZSENTGXNJCHEDFHQUCGCGJBURNSEDZSEPLQP";
        plaintext = e.encrypt(output);
        test(plaintext, input);
        
        // Long input
        e = new Enigma(new String[] {"III", "VI", "VIII"}, "B", new int[] {3,5,9}, new int[] {11,13,19}, "");
        char[] longInput = new char[500];
        for (int i = 0; i < 500; i++) longInput[i] = 'A';
        input = new String(longInput);
        output = "YJKJMFQKPCUOCKTEZQVXYZJWJFROVJMWJVXRCQYFCUVBRELVHRWGPYGCHVLBVJEVTTYVMWKJFOZHLJEXYXRDBEVEHVXKQSBPYZN" +
                "IQDCBGTDDWZQWLHIBQNTYPIEBMNINNGMUPPGLSZCBRJULOLNJSOEDLOBXXGEVTKCOTTLDZPHBUFKLWSFSRKOMXKZELBDJNRUDUCO" +
                "TNCGLIKVKMHHCYDEKFNOECFBWRIEFQQUFXKKGNTSTVHVITVHDFKIJIHOGMDSQUFMZCGGFZMJUKGDNDSNSJKWKENIRQKSUUHJYMIG" +
                "WWNMIESFRCVIBFSOUCLBYEEHMESHSGFDESQZJLTORNFBIFUWIFJTOPVMFQCFCFPYZOJFQRFQZTTTOECTDOOYTGVKEWPSZGHCTQRP" +
                "GZQOVTTOIEGGHEFDOVSUQLLGNOOWGLCLOWSISUGSVIHWCMSIUUSBWQIGWEWRKQFQQRZHMQJNKQTJFDIJYHDFCWTHXUOOCVRCVYOHL";
        plaintext = e.encrypt(output);
        test(plaintext, input);

    }

    public static void plugboardTest() {
        // Simple test - 4 plugs
        Enigma e = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,0,0}, new int[] {0,0,0}, "AC FG JY LW");
        String input = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String output = e.encrypt(input);
        String expectedOutput = "QREBNMCYZELKQOJCGJVIVGLYEMUPCURPVPUMDIWXPPWROOQEGI";
        test(expectedOutput, output);

        // 6 plugs
        e = new Enigma(new String[] {"IV", "VI", "III"}, "B", new int[] {0,10,6}, new int[] {0,0,0}, "BM DH RS KN GZ FQ");
        input = "WRBHFRROSFHBCHVBENQFAGNYCGCRSTQYAJNROJAKVKXAHGUZHZVKWUTDGMBMSCYQSKABUGRVMIUOWAPKCMHYCRTSDEYTNJLVWNQY";
        expectedOutput = "FYTIDQIBHDONUPAUVPNKILDHDJGCWFVMJUFNJSFYZTSPITBURMCJEEAMZAZIJMZAVFCTYTKYORHYDDSXHBLQWPJBMSSWIPSWLENZ";
        output = e.encrypt(input);
        test(expectedOutput, output);

        // 10 plugs
        e = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,1,20}, new int[] {5,5,4}, "AG HR YT KI FL WE NM SD OP QJ");
        input = "RNXYAZUYTFNQFMBOLNYNYBUYPMWJUQSBYRHPOIRKQSIKBKEKEAJUNNVGUQDODVFQZHASHMQIHSQXICTSJNAUVZYIHVBBARPJADRH";
        expectedOutput = "CFBJTPYXROYGGVTGBUTEBURBXNUZGGRALBNXIQHVBFWPLZQSCEZWTAWCKKPRSWOGNYXLCOTQAWDRRKBCADTKZGPWSTNYIJGLVIUQ";
        output = e.encrypt(input);
        test(expectedOutput, output);

    }
}
