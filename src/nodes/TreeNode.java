package nodes;

import _abstract.Node;
import _abstract.Tree;
import _enum.Node_Identifier;
import structs.LinkedList;

/**
 * Created by Felipe on 02/06/2016.
 */
public class TreeNode <E> extends Node<E> implements Comparable<TreeNode<E>> {
    private TreeNode<E> father;
    private Node_Identifier identifier;

    private LinkedList<TreeNode<E>> nodes;

    public TreeNode(Node_Identifier identifier){
        super();
        this.identifier = identifier;
        nodes = new LinkedList<>();
    }

    public TreeNode(Node_Identifier identifier, E obj){
        super(obj);
        this.identifier = identifier;
        nodes = new LinkedList<>();
    }

    public boolean hasFather(){
        return (father != null);
    }

    public boolean hasChildren(){
        return !nodes.isEmpty();
    }

    public void putChild(TreeNode<E> node){
        nodes.insert(node);
    }

    public void removeChild(TreeNode<E> node){
        nodes.remove(node);
    }

    public LinkedList<TreeNode<E>> retrieveSpecificChildren(Node_Identifier identifier){
        LinkedList<TreeNode<E>> list = new LinkedList<>();
        for (TreeNode Tnod: nodes) {
            if(Tnod.getIdentifier() == identifier)
               list.insert(Tnod);
        }
        return list;
    }

    public LinkedList<TreeNode<E>> children(){
        return nodes;
    }

    @Override
    public int compareTo(TreeNode<E> o) {
        if(identifier.ordinal() > o.identifier.ordinal())
            return 1;
        else
        if(identifier.ordinal() < o.identifier.ordinal())
            return -1;
        else
            return 0;
    }

    public TreeNode<E> getFather() {
        return father;
    }

    public void setFather(TreeNode<E> father) {
        this.father = father;
    }
    public LinkedList<TreeNode<E>> getNodes() {
        return nodes;
    }

    public Node_Identifier getIdentifier() {
        return identifier;
    }
}
