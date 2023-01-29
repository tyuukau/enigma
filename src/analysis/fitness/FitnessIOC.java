package analysis.fitness;

public class FitnessIOC extends Fitness {

    public FitnessIOC() {
    }

    @Override
    public float score(char[] text) {
        int[] histogram = new int[26];
        for (char c : text) {
            histogram[c - 65]++;
        }

        int n = text.length;
        float total = 0.0f;

        for (int v : histogram) {
            total += (v * (v - 1));
        }

        return total / (n * (n-1));
    }
}