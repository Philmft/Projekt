package schule;

public class Zellvermehrung {
    int[][] zellen = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0},
            {0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 1, 0},
            {0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };



    public int countNeighbors(int zeile, int spalte) {
        int counter = 0;
        int sFrom = 0;
        int sTo = spalte;
        int zFrom = 0;
        int zTo = zeile;
        if (spalte != 0) sFrom = spalte - 1;
        if (spalte != zellen.length - 1) sTo = spalte +1;
        if (zeile!=0) zFrom = zeile - 1;
        if(zeile != zellen.length -1) zTo = zeile + 1;
        for (int i = zFrom; i <= zTo; i++) {
            for (int j = sFrom; j <= sTo; j++) {
                if (zellen[i][j] == 1) counter++;
            }
        }
        return counter-zellen[zeile][spalte];
    }
    public void nextGen (){
        int [] [] next = new int [6] [6];
        for(int i=0; i<next.length; i++){
            for(int j=0; j<next[0].length;j++) {
                next[i][j] = countNeighbors(i,j)%2 == 0 ? 0 : 1;
            }
        }
        for(int i=0; i<next.length; i++){
            for(int j=0; j<next[0].length;j++) {
                zellen[i][j] = next[i][j];
            }
        }
    }
    public void printG(){
        for(int i=0; i<zellen.length; i++){
            for(int j=0; j<zellen[0].length;j++) {
                System.out.print(zellen[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public void wiederholung(int wiederholungen){
        for (int i = 0; i <wiederholungen; i++){
            printG();
            nextGen();
        }
    }

}
