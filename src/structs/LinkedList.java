package structs;

import _abstract.LinkedStruct;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import nodes.LinkedNode;
/**
 * Classe abstrata usada para construção das estruturas linkadas. Possui um iterator por padrão.A lista pode expandir
 * até o limite de memória do computador.
 */


public class LinkedList<E extends Comparable<E>> extends LinkedStruct<E,LinkedNode<E>> {

    /**
     * Insere um novo elemento não nulo na coleção, as inserções são sempre feitas na cabeça.
     * @param obj O objeto a ser incluido na coleção
     */
    @Override
    public void insert(E obj) {
        if(obj == null)
            throw new NullObjectException();

        LinkedNode<E> node = new LinkedNode(obj);
        if(isEmpty()){
            head.setNext(node);
        }else{
            node.setNext(head.getNext());
            head.setNext(node);
        }
        currentSize++;
    }

    /**
     * Não é permitido passar como parâmetro um objeto nulo, caso a lista estiver vazia lança uma exceção,
     * caso contrário, percorrerá a lista encadeada até encontrar o objeto caso ele exista, não encontrando
     * o objeto também será lançada uma exceção.
     *
     * @param obj Objeto usado como parâmetro normalmente a(s) key(s) inseridas no objeto.
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
            //Caso a coleção contenha somente um elemento.
            if(currentSize == 1){
                if(head.getNext().getObject().compareTo(obj) == 0) {
                    E temp = head.getNext().getObject();
                    head.setNext(null);
                    currentSize--;
                    return temp;
                }else{
                    throw new ElementNotFoundException();
                }
            //Caso quando a coleção possui vários elementos e é preciso procurar o elemento.
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
     * Se a lista estiver vazia lança uma exceção, caso contrário, removerá o elemento da raiz.
     *
     * @return Objeto que está na cabeça da coleção.
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