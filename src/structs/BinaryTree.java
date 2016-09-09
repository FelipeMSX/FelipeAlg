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
     * De acordo com a coleção procura o lugar ideal para ser inserido o novo objeto. Ao final do processo
     * o tamanaho atual da coleção é incrementado.
     * @param obj Novo item que será incluso na coleção, se for null irá lançar uma exceção.
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
                    //Não permitido objetos com o mesmo ID.
                    throw new EqualsElementException();
                }
                currentSize++;
            }
        }
    }

    /** Tenta encontrar na coleção o objeto para ser removido, se for encontrado o remove, se a coleção estiver vazia
     * irá lançar uma exceção, da mesma forma se o objeto não existir na coleção será lançada uma exceção.
     * @param obj Utiliza sua key para encontrar o objeto na coleção, se for null é lançada uma exceção.
     * @return Se for encontrado o objeto com a key é retornado, se não for encontrado irá lançar uma exceção.
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

            //Tratar remoção com só um elemento.
            if(getCurrentSize() == 1){
                BinaryNode<E> searchNode = root.getRight();
                root.setRight(null);
                currentSize--;
                return searchNode.getObject();
            }else{
                BinaryNode<E> removeNode = findNode(obj);

                //Se não existir um objeto com a mesma key.
                if(compareTo(removeNode.getObject(),obj) != 0){
                    throw new ElementNotFoundException();
                }

                //Tratar remoçao de nó com nenhum filho.
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
                //Tratar remoção de um nó com 1 filho na direita.
                //Trata também o caso de 2 filhos.
                else
                if((removeNode.hasRight() && !removeNode.hasLeft()) || (removeNode.hasRight() && removeNode.hasLeft()) ){
                    BinaryNode<E> rightNode = removeNode.getRight();
                    E temp;
                    if(rightNode.hasLeft()){
                        /*
                        CASO REMOÇÃO 01 A DIREITA: Quando ao navegar a direita, fazer um loop até encontrar o nó mais
                        a esquerda, ao encontrar o nó ele possui filhos.
                               Representação da árvore binária:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 nó a ser navegado.
                                      / \     /
                                     2   8   15 a ser deslinkado da árvore.
                                               \
                                                16 nó a ser ajustado

                           Passo 1 - Removendo o 10,
                           Passo 2 - Avançar para o seu filho a direita.
                           Passo 3 - Desvincular o nó 15.
                           Passo 4 - Fazer com que 16 seja o novo fiho de 20, e por fim colocar o conteúdo do nó no
                                objeto a ser removido.
                        */

                        /*
                        CASO REMOÇÃO 02 A DIREITA: Quando ao navegar a direita, fazer um loop até encontrar o nó mais
                        à esquerda, ao encontrar o nó ele não possui filhos.
                               Representação da árvore binária:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 nó a ser navegado.
                                      / \     /
                                     2   8   15 a ser deslinkado da árvore.

                           Passo 1 - Removendo o 10,
                           Passo 2 - Avançar para o seu filho a direita.
                           Passo 3 - Desvincular o nó 15.
                           Passo 4 - Colocar o seu conteúdo no objeto 10.
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
                        CASO REMOÇÃO 03 A DIREITA: Quando ao navegar a direita, fazer um loop até encontrar o nó mais
                        à esquerda, ao encontrar o nó ele não possui filhos.
                               Representação da árvore binária:
                                          10 A ser removido
                                         /  \
                                        /    \
                                       5      20 conteúdo desse nó irá para o 10.
                                      / \      \
                                     2   8      25 seu nó pai será ajustado.
                                                 \
                                                  30

                           Passo 1 - Avançar para o seu filho a direita.
                           Passo 2 - Obter o nó pai do nó a ser remivido, se for o root o seu pai será "NULL".
                           Passo 3 - Descubra se o nó a ser removido veio do pai pela esquerda ou direita, feito isso
                                     ajuste o ponteiro.
                           OBS: se o nó a ser removido for o root é preciso fazer um ajuste no ponteiro do root.
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
                        //Quando entrar no caso de remover um nó com 2 filhos, como foi escolhido pra remover a direita,
                        //Ao encontrar o sucessor é preciso fazer herde os filhos da esquerda.
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
                //Tratar remoção de nó com 1 filho na esquerda.
                //Os casos de uso são os mesmos dos citados acima, a única diferença é que é feito para um filho na esquerda.
                else
                if(!removeNode.hasRight() && removeNode.hasLeft()) {
                    BinaryNode<E> leftNode = removeNode.getLeft();
                    E temp;
                    if(leftNode.hasRight()){
                        BinaryNode<E> successor = leftNode;
                        while(successor.hasRight())
                            successor = successor.getRight();

                        //Ao tentar remover o sucessor é preciso verificar se existe algum nó seu a esquerda
                        //Caso exista, os ponteiros são devidamentes ajustados.
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
     * Procura na coleção um determinado objeto na coleção, se Tentar recuperar o objeto de uma coleção vazia
     * ocorrerá uma exeção.
     * @param obj Utiliza sua key como parâmetro de comparação, se for null irá lançar uma exceção.
     * @return Se encontrar o objeto o retorna, caso contrário, lança uma exceção.
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
     * @param obj Utilizado sua key para encontrar na coleção o objeto desejado.
     * @return BinaryNode com a posição específica de acordo com a key, se o objeto informado como parâmetro
     * ainda não existir retorna a posição anterior a ele onde ele deveria ficar.
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
     * @return True se a lista estiver vazia, false se não estiver.
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }


    /**
     * @return Um inteiro representando o atual tamanho da coleção.
     */
    public int getCurrentSize(){
        return currentSize;
    }

    private int compareTo(E o1, E o2) {
        return comparator.compare(o1, o2);
    }
}
