package structs;

import exception.*;
import nodes.BinaryNode;

import java.util.Comparator;

/**
 * Created by Felipe on 01/06/2016.
 */
public class BinaryTree<E> {
    private BinaryNode<E> root;
    private int currentSize;

    private Comparator<E> comparator;
    BinaryTree(Comparator<E> comparator){
        this.comparator = comparator;
        if(comparator == null)
            throw new NullObjectException();
        root = new BinaryNode<>();
    }

    /**
     * De acordo com a cole��o procura o lugar ideal para ser inserido o novo objeto. Ao final do processo
     * o tamanaho atual da cole��o � incrementado.
     * @param obj Novo item que ser� incluso na cole��o, se for null ir� lan�ar uma exce��o.
     */
    public void insert(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }else{
            if(isEmpty()) {
                BinaryNode<E> node = new BinaryNode<>(obj);
                root.setRight(node);
                currentSize++;
            }else{
                BinaryNode<E> searchNode = findNode(obj);
                BinaryNode<E> newNode = new BinaryNode<>(obj);
                newNode.setFather(searchNode);

                if(compareTo(searchNode.getObject(),obj) < 0){
                    searchNode.setRight(newNode);
                }
                else
                if(compareTo(searchNode.getObject(),obj) > 0){
                    searchNode.setLeft(newNode);

                }else{
                    //N�o permitido objetos com o mesmo ID.
                    throw new EqualsElementException();
                }
                currentSize++;
            }
        }
    }

    /** Tenta encontrar na cole��o o objeto para ser removido, se for encontrado o remove, se a cole��o estiver vazia
     * ir� lan�ar uma exce��o, da mesma forma se o objeto n�o existir na cole��o ser� lan�ada uma exce��o.
     * @param obj Utiliza sua key para encontrar o objeto na cole��o, se for null � lan�ada uma exce��o.
     * @return Se for encontrado o objeto com a key � retornado, se n�o for encontrado ir� lan�ar uma exce��o.
     */
    public E remove(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }
        else
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        else{

            //Tratar remo��o com s� um elemento.
            if(getCurrentSize() == 1){
                BinaryNode<E> searchNode = root.getRight();
                root.setRight(null);
                currentSize--;
                return searchNode.getObject();
            }else{
                BinaryNode<E> removeNode = findNode(obj);

                //Se n�o existir um objeto com a mesma key.
                if(compareTo(removeNode.getObject(),obj) != 0){
                    throw new ElementNotFoundException();
                }

                //Tratar remo�ao de n� com nenhum filho.
                if(!removeNode.hasRight() && !removeNode.hasLeft()){
                    BinaryNode<E> father = removeNode.getFather();

                    if(father.hasLeft() && compareTo(father.getLeft().getObject(),obj) == 0){
                        father.setLeft(null);
                    }else{
                        father.setRight(null);
                    }
                    removeNode.setFather(null);
                    currentSize--;
                    return removeNode.getObject();
                }
                //Tratar remo��o de um n� com 1 filho na direita.
                //Trata tamb�m o caso de 2 filhos.
                else
                if((removeNode.hasRight() && !removeNode.hasLeft()) || (removeNode.hasRight() && removeNode.hasLeft()) ){
                    BinaryNode<E> rightNode = removeNode.getRight();
                    E temp;
                    if(rightNode.hasLeft()){
                        /*
                        CASO REMO��O 01 A DIREITA: Quando ao navegar a direita, fazer um loop at� encontrar o n� mais
                        a esquerda, ao encontrar o n� ele possui filhos.
                               Representa��o da �rvore bin�ria:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 n� a ser navegado.
                                      / \     /
                                     2   8   15 a ser deslinkado da �rvore.
                                               \
                                                16 n� a ser ajustado

                           Passo 1 - Removendo o 10,
                           Passo 2 - Avan�ar para o seu filho a direita.
                           Passo 3 - Desvincular o n� 15.
                           Passo 4 - Fazer com que 16 seja o novo fiho de 20, e por fim colocar o conte�do do n� no
                                objeto a ser removido.
                        */

                        /*
                        CASO REMO��O 02 A DIREITA: Quando ao navegar a direita, fazer um loop at� encontrar o n� mais
                        � esquerda, ao encontrar o n� ele n�o possui filhos.
                               Representa��o da �rvore bin�ria:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 n� a ser navegado.
                                      / \     /
                                     2   8   15 a ser deslinkado da �rvore.

                           Passo 1 - Removendo o 10,
                           Passo 2 - Avan�ar para o seu filho a direita.
                           Passo 3 - Desvincular o n� 15.
                           Passo 4 - Colocar o seu conte�do no objeto 10.
                        */
                        BinaryNode<E> successor = rightNode;
                        while(successor.hasLeft())
                            successor = successor.getLeft();

                        if(successor.hasRight()){
                            successor.getRight().setFather(successor.getFather());
                        }
                        successor.getFather().setLeft(successor.getRight());
                        successor.setFather(null);
                        successor.setRight(null);
                        temp = removeNode.getObject();
                        removeNode.setObject(successor.getObject());
                    }else{
                        /*
                        CASO REMO��O 03 A DIREITA: Quando ao navegar a direita, fazer um loop at� encontrar o n� mais
                        � esquerda, ao encontrar o n� ele n�o possui filhos.
                               Representa��o da �rvore bin�ria:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 conte�do desse n� ir� para o 10.
                                      / \      \
                                     2   8      25 seu n� pai ser� ajustado.
                                                 \
                                                  30

                           Passo 1 - Avan�ar para o seu filho a direita.
                           Passo 2 - Obter o n� pai do n� a ser remivido, se for o root o seu pai ser� "NULL".
                           Passo 3 - Descubra se o n� a ser removido veio do pai pela esquerda ou direita, feito isso
                                     ajuste o ponteiro.
                           OBS: se o n� a ser removido for o root � preciso fazer um ajuste no ponteiro do root.
                        */
                        BinaryNode<E> father = removeNode.getFather();
                        if(father != null ) {
                            if (father.hasLeft() && compareTo(father.getLeft().getObject(),obj) == 0) {
                                father.setLeft(rightNode);
                            } else {
                                father.setRight(rightNode);
                            }
                        }
                        rightNode.setFather(father);
                        //Quando entrar no caso de remover um n� com 2 filhos, como foi escolhido pra remover a direita,
                        //Ao encontrar o sucessor � preciso fazer herde os filhos da esquerda.
                        rightNode.setLeft(removeNode.getLeft());
                        temp = removeNode.getObject();

                        if(root.getRight().equals(removeNode))
                            root.setRight(rightNode);

                        removeNode.setFather(null);
                        removeNode.setRight(null);
                    }
                    currentSize--;
                    return temp;
                }
                //Tratar remo��o de n� com 1 filho na esquerda.
                //Os casos de uso s�o os mesmos dos citados acima, a �nica diferen�a � que � feito para um filho na esquerda.
                else
                if(!removeNode.hasRight() && removeNode.hasLeft()) {
                    BinaryNode<E> leftNode = removeNode.getLeft();
                    E temp;
                    if(leftNode.hasRight()){
                        BinaryNode<E> successor = leftNode;
                        while(successor.hasRight())
                            successor = successor.getRight();

                        //Ao tentar remover o sucessor � preciso verificar se existe algum n� seu a esquerda
                        //Caso exista, os ponteiros s�o devidamentes ajustados.
                        if(successor.hasLeft()){
                            successor.getLeft().setFather(successor.getFather());
                        }
                        successor.getFather().setRight(successor.getLeft());
                        successor.setFather(null);
                        successor.setLeft(null);
      
                        temp = removeNode.getObject();
                        removeNode.setObject(successor.getObject());

                    }else{
                        BinaryNode<E> father = removeNode.getFather();
                        if(father != null ) {
                            if (father.hasRight() && compareTo(father.getRight().getObject(),obj) == 0) {
                                father.setRight(leftNode);
                            } else {
                                father.setLeft(leftNode);
                            }
                        }
                        leftNode.setFather(father);
                        if(root.getRight().equals(removeNode))
                            root.setRight(leftNode);
                        removeNode.setFather(null);
                        removeNode.setLeft(null);
                        temp = removeNode.getObject();
                    }
                    currentSize--;
                    return temp;
                }
           }
        }
        return null;
    }

    /**
     * Procura na cole��o um determinado objeto na cole��o, se Tentar recuperar o objeto de uma cole��o vazia
     * ocorrer� uma exe��o.
     * @param obj Utiliza sua key como par�metro de compara��o, se for null ir� lan�ar uma exce��o.
     * @return Se encontrar o objeto o retorna, caso contr�rio, lan�a uma exce��o.
     */
    public E retrieve(E obj) {
        if (obj == null) {
            throw new NullObjectException();
        } else if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            BinaryNode<E> searchNode = findNode(obj);

            if(compareTo(searchNode.getObject(),obj) == 0){
                return searchNode.getObject();
            }else{
                throw new ElementNotFoundException();
            }
        }
    }

    /**
     * @param obj Utilizado sua key para encontrar na cole��o o objeto desejado.
     * @return BinaryNode com a posi��o espec�fica de acordo com a key, se o objeto informado como par�metro
     * ainda n�o existir retorna a posi��o anterior a ele onde ele deveria ficar.
     */
    private BinaryNode<E> findNode(E obj){
        BinaryNode<E> searchNode = root.getRight();

        while(true){
            if(compareTo(searchNode.getObject(),obj) < 0){
                if(searchNode.hasRight()) {
                    searchNode = searchNode.getRight();
                }else{
                    return searchNode;
                }
            }
            else
            if(compareTo(searchNode.getObject(),obj) > 0){
                if(searchNode.hasLeft()) {
                    searchNode = searchNode.getLeft();
                }else{
                    return searchNode;
                }
            }
            else
            if(compareTo(searchNode.getObject(),obj) == 0){
                return searchNode;
            }
        }
    }


    /**
     * @return True se a lista estiver vazia, false se n�o estiver.
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }


    /**
     * @return Um inteiro representando o atual tamanho da cole��o.
     */
    public int getCurrentSize(){
        return currentSize;
    }

    private int compareTo(E o1, E o2) {
        return comparator.compare(o1, o2);
    }
}
