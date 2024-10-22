import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {



//----------------------------------------------------------------affine

//        encrypt_affine(9,4);
//        decrypt_affine(9,4);
        System.out.println(" ");
        char[] two = new char[2];
        two = two_most_frequent_letters();
        System.out.println("two most frequent letters : "+two[0]+","+two[1]);
        int[] momo = new int[2];
        momo = affine_key(two[0],two[1]);
        System.out.println("affine key : ("+momo[0]+","+momo[1]+")");
        System.out.println(" ");
        affine(momo[0],momo[1]);
//----------------------------------------------------------------vegenere
        System.out.println("================================================================vegenere");
        vegenereEncrypt("noureddine","haithem");
        System.out.println(" ");
        vegenereDec("uoailhppnmqebj","haithem");




    }


    private static void vegenereDec(String t, String k) {

        char[] alph = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '.', ' '
        };



        ArrayList<String> textlist = new ArrayList<>();
        for (int i = 0; i < t.length(); i=i+k.length()) {
            textlist.add(t.substring(i,i+k.length()));
        }
        char[] key = new char[k.length()];
        char[] text = new char[k.length()];

        key = k.toCharArray();
        int j=0;
        while (j < textlist.size()){
            text = textlist.get(j).toCharArray();
            for (int i=0; i<k.length(); i++){
                System.out.print(alph[((((text[i]-'a')-(key[i]-'a'))%28 + 28) % 28) % alph.length]);
            }
            j++;
        }

    }

    private static void vegenereEncrypt(String t, String k) {

        char[] alph = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '.', ' '
        };

        if (t.length() % k.length() != 0) {
            int extraSize = k.length() - (t.length() % k.length());
            String extraText="";
            for (int i=0; i<extraSize; i++) {
                extraText = extraText + "z";
            }
            t = t + extraText;

        }

        ArrayList<String> textlist = new ArrayList<>();
        for (int i = 0; i < t.length(); i=i+k.length()) {
            textlist.add(t.substring(i,i+k.length()));
        }
        char[] key = new char[k.length()];
        char[] text = new char[k.length()];

        key = k.toCharArray();
        int j=0;
        while (j < textlist.size()){
            text = textlist.get(j).toCharArray();
            for (int i=0; i<k.length(); i++){
                System.out.print(alph[((((text[i]-'a')+(key[i]-'a'))%28 + 28) % 28) % alph.length]);
            }
            j++;
        }

    }

    public static void affineDecrypt(int k1, int k2) {
        char[] alph = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '.', ' '
        };

        int k1Inverse = modInverse(k1, alph.length);
        k2 = alph.length - k2;

        String filePath = "/home/haithem/Desktop/AC/tp1part1/src/affine.txt";

        try (FileReader fr = new FileReader(filePath)) {
            int character;
            while ((character = fr.read()) != -1) {
                char currentChar = (char) character;
                int index = indexOf(currentChar, alph);

                if (index != -1) {
                    int i = (k1Inverse * (index + k2) % alph.length + alph.length) % alph.length;
                    System.out.print(alph[i]);
                } else {
                    System.out.print(currentChar);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }


    public static int indexOf(char c, char[] alph) {
        for (int i = 0; i < alph.length; i++) {
            if (alph[i] == c) {
                return i;
            }
        }
        return -1;
    }
    public static void affine (int k1, int k2){
        char alph[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '.', ' ' };


        int k1Inverse = modInverse(k1, alph.length);

        k2 = alph.length - k2;

        String filePath = "/home/haithem/Desktop/AC/tp1part1/src/affine.txt";

        try (FileReader fr = new FileReader(filePath)) {
            int character;
            while ((character = fr.read()) != -1) {
                char currentChar = (char) character;
                boolean found = false;

                for (int j = 0; j < alph.length; j++) {
                    if (currentChar == alph[j]) {
                        // Use the decryption formula
                        int newIndex = (k1Inverse * ((j + k2 + alph.length) % alph.length)) % alph.length;
                        System.out.print(alph[newIndex]);
                        found = true;
                        break;
                    }
                }


                if (!found) {
                    System.out.print(currentChar);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] affine_key(char e1,char e2){
        int x = Character.getNumericValue(e1)-10;
        int y = Character.getNumericValue(e2)-10;
        int[] aff = new int[2];

        aff[0] = (((x - y) * 15) % 28 + 28) % 28;
        aff[1] = ((y - 4 * aff[0]) % 28 + 28) % 28;
        return aff;

    }
    private static char[] two_most_frequent_letters(){
        String filePath = "/home/haithem/Desktop/AC/tp1part1/src/affine.txt";
        Aphabet[] alph = new Aphabet[]{
                new Aphabet(0, 'a'), new Aphabet(0, 'b'), new Aphabet(0, 'c'), new Aphabet(0, 'd'),
                new Aphabet(0, 'e'), new Aphabet(0, 'f'), new Aphabet(0, 'g'), new Aphabet(0, 'h'),
                new Aphabet(0, 'i'), new Aphabet(0, 'j'), new Aphabet(0, 'k'), new Aphabet(0, 'l'),
                new Aphabet(0, 'm'), new Aphabet(0, 'n'), new Aphabet(0, 'o'), new Aphabet(0, 'p'),
                new Aphabet(0, 'q'), new Aphabet(0, 'r'), new Aphabet(0, 's'), new Aphabet(0, 't'),
                new Aphabet(0, 'u'), new Aphabet(0, 'v'), new Aphabet(0, 'w'), new Aphabet(0, 'x'),
                new Aphabet(0, 'y'), new Aphabet(0, 'z')
        };

        try (FileReader fr = new FileReader(filePath)) {
            int character;
            while ((character = fr.read()) != -1) {


                for (int j = 0; j < alph.length; j++) {
                    if (alph[j].getAlphabet() == (char) character) {
                        alph[j].calculate_alphabet(alph[j].getNumber_alphabet());
                        break;
                    }
                }
                int n = alph.length;
                boolean swapped;

                // Repeat until no swaps are made
                for (int i = 0; i < n - 1; i++) {
                    swapped = false;
                    // Go through the array and compare adjacent elements
                    for (int j = 0; j < n - 1 - i; j++) {
                        if (alph[j].getNumber_alphabet() > alph[j + 1].getNumber_alphabet()) {
                            // Swap the elements if they are in the wrong order
                            Aphabet temp = alph[j];
                            alph[j] = alph[j + 1];
                            alph[j + 1] = temp;
                            swapped = true;
                        }
                    }

                    if (!swapped) {
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] table = new char[2];
        table[0] = alph[24].getAlphabet();
        table[1] = alph[25].getAlphabet();
        return table;
    }
}

