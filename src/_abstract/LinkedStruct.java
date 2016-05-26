package _abstract;

import _interfaces.Common;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import nodes.LinkedNode;

import java.util.Iterator;


/**
 * Classe abstrata usada para construção das estruturas linkadas. Possui um iterator por padrão.
 */


public abstract class LinkedStruct <E extends  Comparable<E>,T extends LinkedNode<E>> implements Common<E>,Iterable<E> {

    /**
     * Ponteiro para a construção da coleção, não possui dados é somente um ponteiro.
     */
    protected T head;
    /**
     * Indica o atual tamanho da coleção.
     */
    protected int currentSize;


    public abstract void insert(E obj);
    //Remover um específico elemento da lista;
    public abstract E remove(E obj);
    //Remove o primeiro elemento da coleção a partir da raiz;
    public abstract E remove();

    /**
     * Construtor padrão para inicializar os componentes.
     */
    public LinkedStruct()
    {
        head 	    = (T) new LinkedNode<E>();
        currentSize = 0;
    }


    /**
     * @return Informa se a lista no seu estado atual está vazia.
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Se a lista estiver vazia lança uma exceção, caso contrário, retorna o objeto.
     * @return Retorna sem remover o primeiro elemento na coleção, que está localizado após a raiz.
     *
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            return head.getNext().getObject();
        }
    }

    /**
     * Verifica se a coleção está vazia, se sim, lançará uma exceção. Se tudo ocerror normalmente irá percorrer toda a
     * coleção até chegar no fim.
     * @return Elemento localizado no final da coleção.
     */
    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {
            T temp = (T) head.getNext();

            while (temp.hasNextNode()){
                temp = (T) temp.getNext();
            }
            return temp.getObject();
        }
    }

    @Override
    public int getCurrentSize()
    {
        return currentSize;
    }

    /**
     * Apaga toda a coleção e reseta para a configuração padrão, o procedimento feito para isso é tirar a referência
     * para a coleção, com isso ao executar o coletor de lixo removerá os elementos sem referência.
     */
    @Override
    public void disposeAll() {
        head.setNext(null);
        currentSize = 0;
    }

    /**
     * O parâmetro não pode ser nulo, é verificado se a coleção está vazia. Se passar pela checagem inicial irá percorrer
     * toda a coleção até encontrar o elemento, se nenhum objeto for encontrado é lançada uma exceção.
     * @param obj Usado como parâmetro de comparação para encontrar o objeto na coleção.
     * @return Retorna um objeto completo com todas as suas informações associadas.
     */
    @Override
    public E retrieve(E obj) {
        if(obj == null){
            throw new NullObjectException();
        }
        else
        if (isEmpty()) {
            throw new EmptyCollectionException();
        } else {

            T temp = (T) head.getNext();

            while (temp != null){
                if(temp.getObject().compareTo(obj) == 0)
                    return temp.getObject();
                else
                    temp = (T) temp.getNext();
            }
            throw new ElementNotFoundException();
        }
    }

    /**
     * @return Retorna um iterator que é definido na própria função.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            T next = (T) head.getNext();
            @Override
            public boolean hasNext() {
                if(next == null)
                    return false;
                else{
                    return next.hasNextNode();
                }
            }

            @Override
            public E next() {

                E object = next.getObject();
                next = (T) next.getNext();
                return object;
            }
        };
    }
}