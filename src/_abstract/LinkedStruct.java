package _abstract;

import _interfaces.Common;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.NullObjectException;
import nodes.LinkedNode;

import java.util.Iterator;


/**
 * Classe abstrata usada para constru��o das estruturas linkadas. Possui um iterator por padr�o.
 */


public abstract class LinkedStruct <E extends  Comparable<E>,T extends LinkedNode<E>> implements Common<E>,Iterable<E> {

    /**
     * Ponteiro para a constru��o da cole��o, n�o possui dados � somente um ponteiro.
     */
    protected T head;
    /**
     * Indica o atual tamanho da cole��o.
     */
    protected int currentSize;


    public abstract void insert(E obj);
    //Remover um espec�fico elemento da lista;
    public abstract E remove(E obj);
    //Remove o primeiro elemento da cole��o a partir da raiz;
    public abstract E remove();

    /**
     * Construtor padr�o para inicializar os componentes.
     */
    public LinkedStruct()
    {
        head 	    = (T) new LinkedNode<E>();
        currentSize = 0;
    }


    /**
     * @return Informa se a lista no seu estado atual est� vazia.
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Se a lista estiver vazia lan�a uma exce��o, caso contr�rio, retorna o objeto.
     * @return Retorna sem remover o primeiro elemento na cole��o, que est� localizado ap�s a raiz.
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
     * Verifica se a cole��o est� vazia, se sim, lan�ar� uma exce��o. Se tudo ocerror normalmente ir� percorrer toda a
     * cole��o at� chegar no fim.
     * @return Elemento localizado no final da cole��o.
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
     * Apaga toda a cole��o e reseta para a configura��o padr�o, o procedimento feito para isso � tirar a refer�ncia
     * para a cole��o, com isso ao executar o coletor de lixo remover� os elementos sem refer�ncia.
     */
    @Override
    public void disposeAll() {
        head.setNext(null);
        currentSize = 0;
    }

    /**
     * O par�metro n�o pode ser nulo, � verificado se a cole��o est� vazia. Se passar pela checagem inicial ir� percorrer
     * toda a cole��o at� encontrar o elemento, se nenhum objeto for encontrado � lan�ada uma exce��o.
     * @param obj Usado como par�metro de compara��o para encontrar o objeto na cole��o.
     * @return Retorna um objeto completo com todas as suas informa��es associadas.
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
     * @return Retorna um iterator que � definido na pr�pria fun��o.
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