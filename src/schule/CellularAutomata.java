package schule;

/**
 * CellularAutomata
 *
 * @author Lasse Oost
 */
public class CellularAutomata {
    int[] generation = new int[25];

    public CellularAutomata() {
        // Array zufällig befüllen
        for (int i = 0; i < generation.length; i++) {
            generation[i] = (int) Math.round(Math.random());
        }
    }

    public int nextCell(int cellIndex) {
        // Berechnen der nächsten Zelle basierend auf den Nachbarn und aufgestellten Regeln
        int ruck = 1;
        if(generation[cellIndex - 1] == generation[cellIndex + 1]) {
            ruck = 0;
        }
        return ruck;
    }

    public void nextGeneration(int durch) {
        // Berechnen des gesammten Arrays
        for(int i = 0; i<durch; i++){
            int[] newgeneration = new int[25];
            if(generation[1] == 0){
                newgeneration[0] = 0;
            }
            else{
                newgeneration[0] = 1;
            }
            for (int stelle = 1 ; stelle < generation.length -  2; stelle++){
                newgeneration[stelle]= nextCell(stelle);
            }
            if(newgeneration[newgeneration.length - 2] == 0){
                newgeneration[newgeneration.length - 1] = 0;
            }
            else{
                newgeneration[newgeneration.length - 1] = 1;
            }
            generation = newgeneration;
            printGeneration();

        }
    }

    public void printGeneration() {
        // Hilfsfunktion zur Ausgabe des Arrays
        for (int j = 0; j < generation.length; j++) {
            System.out.print(generation[j] + " ");
        }
        System.out.println();
    }
    int[][] zellen = {
            {0,0,0,0,0,0,0,0}, //keine Zeile
            {0,0,0,0,0,0,0,0}, //1 Zeile
            {0,0,0,1,1,0,0,0},
            {0,0,1,0,0,1,0,0},
            {0,0,1,0,0,1,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };
    public int countNeighbors(int zeile, int spalte) {
        int count = 0;
        for (int i = zeile - 1; i< zeile + 2 ; i++){
            for(int j = spalte -1 ; j< spalte + 2; j++){

                count+= zellen[i][j];

            }
        }
        count-= zellen[zeile][spalte];
        return count;
    }

    public void generationneu(){
        int[][] zellenneu = {
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0}
        };
        for (int i = 1; i<7; i++){
            for (int j = 1; j<7; j++){
                if(countNeighbors(i,j) % 2 == 0){
                    zellenneu[i-1][j-1] = 0;
                }
                else{
                    zellenneu[i-1][j-1] = 1;
                }
            }
        }
        for (int k = 0; k<6; k++){
            for (int l = 0; l<6; l++){
                zellen [k+1][l+1] = zellenneu[k][l];
            }
        }
        printarray();

    }
    public void printarray(){
        for (int i = 1; i<7; i++){
            for (int j = 1; j<7; j++){
                System.out.print(zellen[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

    }
}