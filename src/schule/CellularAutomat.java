package schule;

public class CellularAutomat {
    int[] generation = new int[25];

    public CellularAutomat() {
        // Array zufällig befüllen
        for (int i = 0; i < generation.length; i++) {
            generation[i] = (int) Math.round(Math.random());
        }
    }

    private int nextCell(int cellIndex) {
        int left = 0;
        int right = 0;
        int nextCell=0;
        if(cellIndex-1 >= 0){
            if(generation[(cellIndex-1)] == 1) left = 1;
        }
        if(cellIndex+1 < generation.length){
            if(generation[(cellIndex+1)] == 1)  right = 1;
        }
        if(left == 1 && right == 0) nextCell = 1;
        if(left == 0 && right == 1) nextCell = 1;
        return nextCell;
    }

    public void nextGeneration() {
        int [] next = new int [25]; 
        for (int i = 0; i<generation.length;i++){
            next[i] = nextCell(i);
        }
        for (int j = 0; j < next.length; j++) {
            System.out.print(next[j] + " ");
        }
        generation = next;
    }

    public void printGeneration(int count) {
        int anzahl = 0;
        for (int j = 0; j < generation.length; j++) {
            System.out.print(generation[j] + " ");
        }
        while(anzahl < count) {
        System.out.println();
        nextGeneration();
        anzahl += 1;
        }
    }
}