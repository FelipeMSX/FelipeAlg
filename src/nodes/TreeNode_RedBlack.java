package nodes;

import _abstract.TreeNode;

/**
 * Created by 3tecnos999 on 17/08/2015.
 */
public class TreeNode_RedBlack<E> extends  TreeNode<E> {
    public enum Color {
        RED(0), BLACK(1);
        private int value;

        Color(int value) {
            this.value = value;
        }
        public int getValue() {

            return value;
        }
    }


    public TreeNode_RedBlack<E> left,right;

    public TreeNode_RedBlack(){
        super();
        this.left = null;
        this.right = null;
    }

    public TreeNode_RedBlack(E object){
        super(object);
        this.left = null;
        this.right = null;
    }

    public void setLeft(TreeNode_RedBlack<E> left) {
        this.left = left;
    }

    public TreeNode_RedBlack<E>  getLeft() {
        return left;
    }

    public TreeNode_RedBlack<E> getRight() {
        return right;
    }

    public void setRight(TreeNode_RedBlack<E> right) {
        this.right = right;
    }

    public boolean hasLeftNode()
    {
        return (left != null);
    }

    public boolean hasRightNode()
    {
        return (right != null);
    }


}
