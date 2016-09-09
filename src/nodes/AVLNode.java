package nodes;

/**
 * Created by felipemsx on 08/09/2016.
 */
public class AVLNode<E> extends BinaryNode<E> {
    private int weight = 0;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
