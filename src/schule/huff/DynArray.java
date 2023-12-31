package schule.huff;

public class DynArray<T>{
  private T inhalt;
  private DynArray<T> nextNode;
  
  public DynArray(){
    inhalt = null;
    nextNode = null;
  }
    
  public boolean isEmpty(){
    return inhalt==null;
  }   
  
  public T getItem(int index) {
    if (index==0) {
      return inhalt;
    } else {
      return nextNode.getItem(index-1);
      //return nextNode.getItem(index);
    }
  }
  
  public void append(T neuInhalt){
    if(isEmpty()){
      //DynArray ist ganz leer
      inhalt = neuInhalt;
    } else if (nextNode==null) {
        // Ende des DynArray gefunden
        nextNode = new DynArray<>();
        nextNode.append(neuInhalt);
      } else { 
        // Weiter auf der Suche nach dem Ende der Schlange  
        nextNode.append(neuInhalt);  
      }
  }
  
  public void insertAt(int index, T neuInhalt){
   // index -= index;
      
      if(isEmpty()){
      //DynArray ist ganz leer
      inhalt = neuInhalt;
    } else if (nextNode==null) {
        // Ende des DynArray gefunden
        nextNode = new DynArray<>();
        nextNode.append(neuInhalt);
      } else if (index>0) {
          nextNode.insertAt(index-1, neuInhalt);   
          //nextNode.insertAt(index-1, neuInhalt);    
        } else { 
          // Richtige Position gefunden.
          // neuer Knoten    
          DynArray<T> tmp = new DynArray<>();
          // weitere Nodes an tmp anhängen 
          tmp.nextNode= nextNode;
          tmp.setItem(0,neuInhalt);
          // tmp an die bisherige Liste hängen             
          nextNode= tmp;     
        }
  }
          
  public void setItem (int index, T neuInhalt) {
    if (index==0) {
      inhalt = neuInhalt;
    } else {
        //nextNode.setItem(index, neuInhalt);
      nextNode.setItem(index-1, neuInhalt);
    }
  }    
         
  public void delete(int index){
    if(index == 0){
      if (nextNode==null) {
        inhalt=null;
      } else {
        inhalt = nextNode.inhalt;
        nextNode = nextNode.nextNode;
      }
    } else {
       // nextNode.delete(index);
     nextNode.delete(index-1);
    }
    
  }
                     
  public int getLength() {
    if (isEmpty()) {
      //Abbruchbedingung 1. Kann auftreten, wenn der Stapel leer ist.
      return 0;
    }  else if (nextNode==null) {
        // Abbruchbedingung 2. Regelfall, wenn ein Element ohne darunterliegendes
        // gefunden wird.
        return 1;
      } else {
        // rekursiver Aufruf
        return 1+ nextNode.getLength();
      }
  }
}        