public class Aphabet {
    private char alphabet;
    private int number_alphabet;

    public int getNumber_alphabet() {
        return number_alphabet;
    }

    public void setNumber_alphabet(int number_alphabet) {
        this.number_alphabet = number_alphabet;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(char alphabet) {
        this.alphabet = alphabet;
    }

    public void calculate_alphabet(int number_alphabet) {
        this.number_alphabet = number_alphabet+1;
    }


    public Aphabet(int number_alphabet, char alphabet) {
        this.number_alphabet = number_alphabet;
        this.alphabet = alphabet;
    }
}
