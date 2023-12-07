package schule;

public class AncTree {
    private BinaryTree<Ancestor> jacquelineBouvier = new BinaryTree<>(new Ancestor("Jacqueline","Bouvier",'f'));
    private BinaryTree<Ancestor> clancyBouvier = new BinaryTree<>(new Ancestor("Clancy","Bouvier",'m'));
    private BinaryTree<Ancestor> monaSimpson = new BinaryTree<>(new Ancestor("Mona","Simpson",'f'));
    private BinaryTree<Ancestor> abrahamSimpson = new BinaryTree<>(new Ancestor("Abraham","Simpson",'m'));
    private BinaryTree<Ancestor> margeSimpson = new BinaryTree<>(new Ancestor("Marge","Simpson",'f'), jacquelineBouvier, clancyBouvier);
    private BinaryTree<Ancestor> homerSimpson = new BinaryTree<>(new Ancestor("Homer","Simpson",'m'), monaSimpson, abrahamSimpson);
    private BinaryTree<Ancestor> lisaSimpson = new BinaryTree<>(new Ancestor("Lisa","Simpson",'f'), margeSimpson, homerSimpson);
}
