package schule;

public class BubbleSort {
    public void bubbleSort(int[] a) {
        int n = a.length;
        boolean vertauschungen = true;
        while (vertauschungen && n > 1) {
            vertauschungen = false;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int x = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = x;
                    vertauschungen = true;
                }
            }
            n--;
        }
    }

    public int fortnite(int start1, int start2){
        int result = 0;
        for (int i = 1; i <= start1+start2; i++){
            if (i % 2 == 0) result += (start1 - start2);
            else result += (start1 + start2);
            }
        return result;
    }
}

