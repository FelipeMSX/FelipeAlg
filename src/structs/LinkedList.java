package structs;

import _abstract.LinkedStruct;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import nodes.LinkedNode;
/**
 *  Possui um iterator por padr�o.A lista pode expandir
 * at� o limite de mem�ria do computador.
 */


public class LinkedList<E extends Comparable<E>> extends LinkedStruct<E,LinkedNode<E>> {

    /**
     * Insere um novo elemento n�o nulo na cole��o, as inser��es s�o sempre feitas no final.
     * @param obj O objeto a ser incluido na cole��o
     */
    @Override
    public void insert(E obj) {
        if(obj == null)
            throw new NullObjectException();

        LinkedNode<E> node = new LinkedNode(obj);
        if(isEmpty()){
            head.setNext(node);
        }else{
            LinkedNode<E> searchNode = head.getNext();

            while(searchNode.hasNextNode()){
                searchNode = searchNode.getNext();
            }
            searchNode.setNext(node);
        }
        currentSize++;
    }

    /**
     * N�o � permitido passar como par�metro um objeto nulo, caso a lista estiver vazia lan�a uma exce��o,
     * caso contr�rio, percorrer� a lista encadeada at� encontrar o objeto caso ele exista, n�o encontrando
     * o objeto tamb�m ser� lan�ada uma exce��o.
     *
     * @param obj Objeto usado como par�metro normalmente a(s) key(s) inseridas no objeto.
     * @return Retorna o objeto encontrado a partir da key definida pela interface Comparable.
     */
    @Override
    public E remove(E obj) {
        if(obj == null)
            throw new NullObjectException();
        else
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        else{
            //Caso a cole��o contenha somente um elemento.
            if(head.getNext().getObject().compareTo(obj) == 0){
                LinkedNode<E>  temp = head.getNext();
                head.setNext(temp.getNext());
                temp.setNext(null);
                currentSize--;
                return temp.getObject();
            //Caso quando a cole��o possui v�rios elementos e � preciso procurar o elemento.
            }else{
                LinkedNode<E> search = head.getNext();
                LinkedNode<E> previous = head.getNext();

                while (previous.hasNextNode()){
                    if(search.getObject().compareTo(obj) == 0) {
                        previous.setNext(search.getNext());
                        search.setNext(null);
                        currentSize--;
                        return search.getObject();
                    }
                    previous = search;
                    search = search.getNext();
                }
                throw new ElementNotFoundException();
            }
        }
    }

    /**
     * Se a lista estiver vazia lan�a uma exce��o, caso contr�rio, remover� o elemento da raiz.
     *
     * @return Objeto que est� na cabe�a da cole��o.
     */
    @Override
    public E remove() {
        if(isEmpty()){
            throw new EmptyCollectionException();
        }
        else{
            LinkedNode<E> temp = head.getNext();
            head.setNext(temp.getNext());
            temp.setNext(null);
            currentSize--;
            return temp.getObject();
        }
    }
}