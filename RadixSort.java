public class RadixSort {

    public static int c = 0;

    private static int getMax(int a[]) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        return max; //maximum element from the array
    }

    private static void countingSort(int a[], int place) { // function to implement counting
        int[] output = new int[a.length + 1];
        int[] count = new int[10];

        // Calculate count of elements
        for (int i = 0; i < a.length; i++) {
            count[(a[i] / place) % 10]++;
        }

        // Calculate cumulative frequency
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Place the elements in sorted order
        for (int i = a.length - 1; i >= 0; i--) {
            output[count[(a[i] / place) % 10] - 1] = a[i];
            count[(a[i] / place) % 10]--;
            c++;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = output[i];
            c++;
        }

    }

    // function to implement radix sort
    public static int sort(int a[]) {

        // get maximum element from array
        int max = getMax(a);

        // Apply counting sort to sort elements based on place value
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(a, place);
        }
        return c;
    }
}