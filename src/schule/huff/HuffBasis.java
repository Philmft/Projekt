package schule.huff;
public class HuffBasis {
    private String text;
    private int[] anzahl = new int[26];
    private DynArray<HuffKlasse> huffObjects = new DynArray<>();
    private DynArray<BinaryTree<HuffKlasse>> treeObjects = new DynArray<>();
    private BinaryTree<HuffKlasse> baum;
    private int asciiBits = 0;
    private int huffmanBits = 0;

    public HuffBasis(String text) {
        this.text = text.toUpperCase();
        System.out.println("Text: " + this.text);

        zeichenketteEinlesen();
        treeWithStacks();
        buildTree();
        setHuffcodes(baum, "");
       preOrderAusgabe(this.baum);
       System.out.println();
        printInfo();
    }

    private void zeichenketteEinlesen() {
        for (int i = 0; i < text.length(); i++)
            anzahl[text.charAt(i) - 'A']++;

        boolean done = false;
        while (!done) {
            done = true;
            int min = Integer.MAX_VALUE;
            int pos = 0;

            for (int j = 0; j < anzahl.length; j++) {
                if (anzahl[j] > 0 && anzahl[j] < min) {
                    done = false;
                    min = anzahl[j];
                    pos = j;
                }
            }

            if (!done) {
                huffObjects.append(new HuffKlasse((char) (pos + 'A'), min));
                anzahl[pos] = 0;
            }
        }
    }

    public void treeWithStacks() {
        Stack<BinaryTree<HuffKlasse>> stackLow = new Stack<>();
        Stack<BinaryTree<HuffKlasse>> stackHigh = new Stack<>();
        BinaryTree<HuffKlasse> temp = null;
        BinaryTree<HuffKlasse> secondTemp;

        for (int i = 0; i < huffObjects.getLength(); i++) {
            stackLow.push(new BinaryTree<>(huffObjects.getItem(i)));
        }
        while (!stackLow.isEmpty()) {
            temp = stackLow.top();
            stackLow.pop();
            secondTemp = stackLow.top();
            stackLow.pop();
            temp = new BinaryTree<>(
                    new HuffKlasse(
                            temp.getContent().getAnzahl()
                                    + secondTemp.getContent().getAnzahl()
                    ),
                    temp,
                    secondTemp
            );
            while (!stackLow.isEmpty() && stackLow.top().getContent().getAnzahl() < temp.getContent().getAnzahl()) {
                stackHigh.push(stackLow.top());
                stackLow.pop();
            }
            if (!stackLow.isEmpty() || !stackHigh.isEmpty()) {
                stackLow.push(temp);
            }
            while (!stackHigh.isEmpty()) {
                stackLow.push(stackHigh.top());
                stackHigh.pop();
            }
        }
        this.baum = temp;
    }

    private void buildTree() {
        for (int i = 0; i < huffObjects.getLength(); i++)
            treeObjects.append(new BinaryTree<>(huffObjects.getItem(i)));

        while (treeObjects.getLength() != 1) {
            if (treeObjects.getLength() >= 2) {
                BinaryTree<HuffKlasse> left = treeObjects.getItem(0);
                BinaryTree<HuffKlasse> right = treeObjects.getItem(1);
                treeObjects.delete(0);
                treeObjects.delete(0);

                int anzahl = left.getContent().getAnzahl() + right.getContent().getAnzahl();
                BinaryTree<HuffKlasse> parent = new BinaryTree<>(new HuffKlasse(anzahl), left, right);
                int parentIndex = parentIndex(anzahl);
                if (parentIndex == -1) {
                    treeObjects.insertAt(0, treeObjects.getItem(0));
                    treeObjects.setItem(0, parent);
                } else
                    treeObjects.insertAt(parentIndex, parent);
            }
        }

        this.baum = treeObjects.getItem(0);
    }

    private int parentIndex(int amount) {
        for (int i = 0; i < treeObjects.getLength(); i++) {
            if (treeObjects.getItem(i).getContent().getAnzahl() >= amount)
                return i - 1;
        }
        return treeObjects.getLength();
    }

    private void setHuffcodes(BinaryTree<HuffKlasse> tree, String huffcode) {
        if (tree.getContent().istBlatt()) {
            tree.getContent().setHuffcode(huffcode);
            return;
        }

        if (!tree.getLeftTree().isEmpty())
            setHuffcodes(tree.getLeftTree(), huffcode + "0");

        if (!tree.getRightTree().isEmpty())
            setHuffcodes(tree.getRightTree(), huffcode + "1");
    }

    private void preOrderAusgabe(BinaryTree<HuffKlasse> tree) {
        System.out.println(tree.getContent());

        if (!tree.getLeftTree().isEmpty())
            preOrderAusgabe(tree.getLeftTree());

        if (!tree.getRightTree().isEmpty())
            preOrderAusgabe(tree.getRightTree());
    }

    private void printInfo() {
        asciiBits = baum.getContent().getAnzahl() * 8;
        setHuffmanBits(baum);

        System.out.println("Speicherplatz nach ASCII: " + asciiBits + "\n" +
                            "Speicherplatz nach Huffman: " + huffmanBits + "\n" +
                            "Kompressionsgrad: " + (double) huffmanBits / (double) asciiBits + "\n" +
                            "Kompressionsrate: " +  (double) asciiBits / (double) huffmanBits);

    }

    private void setHuffmanBits(BinaryTree<HuffKlasse> tree) {
        if (tree.getContent().istBlatt()) {
            huffmanBits += tree.getContent().getAnzahl() * tree.getContent().getHuffcode().length();
            return;
        }

        if (!tree.getLeftTree().isEmpty())
            setHuffmanBits(tree.getLeftTree());

        if (!tree.getRightTree().isEmpty())
            setHuffmanBits(tree.getRightTree());
    }

}
