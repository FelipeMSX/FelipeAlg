package _abstract;

import _interfaces.Common;
import exception.ElementNotFoundException;
import exception.EmptyCollectionException;
import exception.FullCollectionException;

/**
 * 	- Estrutura usada para cria��o das estrutura de dados com base em um vetor.Possui um valor padr�o inicial de 100,
 * 	quando esse valor atingir o limite � incrementado de acordo com o resizeValue. Por padr�o, a estrutura irar crescer
 * 	dinamicamente, mas, o usu�rio pode limitar isso.
 */

public abstract class StaticStruct<E extends Comparable<E>> implements Common<E> {

	/**
	 * Vetor onde ser�o armazenados os objetos gen�ricos.
	 */
	protected E[] vector;
	/**
	 * Indica o atual tamanho da cole��o.
	 */
	protected int currentSize;
	/**
	 *  Quantidade de elementos que a cole��o pode conter.
	 */
	protected int maxSize;
	/**
	 * valor que ser� incrementado a cole��o quando o seu valor m�ximo for atingido.
	 */
	protected int resizeValue;
	/**
	 * Ao atingir o m�ximo a cole��o pode tentar crescer, se for TRUE isso ser� feito, caso contr�rio, ao atingir o
	 * m�ximo ser� lan�ado uma exce��o.
	 */
	protected boolean isResizable;

	/**
	 * Construtor padr�o que inicializa a lista com o valor padr�o de 100. Por padr�o cresce din�micamente.
	 */
	public StaticStruct()
	{
		maxSize 	= 100;
		resizeValue = 100;
		vector 		= (E[]) new Comparable[maxSize];
		isResizable = true;
	}

	/**
	 * @param maxSize Tamanho m�ximo que a cole��o pode atingir.
	 */
	public StaticStruct(int maxSize)
	{
		this.maxSize 		= maxSize;
		resizeValue 		= 100;
		vector 				= (E[]) new Comparable[maxSize];
		isResizable 		= true;
	}

	/**
	 * @param maxSize Tamanho m�ximo que a cole��o pode atingir.
	 * @param isResizable Indica se a cole��o pode crescer dinamicamente.
	 */
	public StaticStruct(int maxSize, boolean isResizable)
	{
		this.maxSize 		= maxSize;
		this.isResizable 	= isResizable;
		resizeValue	 		= 100;
		vector 				= (E[]) new Comparable[maxSize];

	}

	/**
	 * @return TRUE se a lista estiver vazia, caso contr�rio, ser� FALSE.
	 */
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Se a cole��o estiver vazia lan�a uma exce��o, caso contr�rio, ir� tentar recuperar o primeiro elemento da cole��o.
	 * @return Obt�m o primeiro elemento da cole��o.
	 */
	@Override
	public E getFirst() {
		if(isEmpty())
			throw new EmptyCollectionException();
		else
			return vector[0];
	}

	/**
	 * Se a cole��o estier vazia lan�a uma exce��o, caso contr�rio, ir� tentar recuperar o �ltimo elemento da cole��o.
	 * @return Obt�m o �ltimo elemento da cole��o.
	 */
	@Override
	public E getLast() {
		if(isEmpty())
			throw new EmptyCollectionException();
		else
			return vector[currentSize -1];
	}

	@Override
	public int getCurrentSize()
	{
		return currentSize;
	}

	/**
	 * Ir� apagar a cole��o por completo.
	 */
	@Override
	public void disposeAll() {
		vector = (E[]) new Comparable[maxSize];
		currentSize = 0;
	}

	/**
	 * Se a cole��o for vazia lan�ar� uma exce��o, caso contr�rio, ir� procurar o elemento na lista de acordo com o
	 * par�metro, se ainda assim nenhum objeto for encontrado tamb�m lan�ar� uma exce��o.
	 * @param obj Usado como par�metro de compara��o.
	 * @return Retorna o objeto com todos os dados.
	 */
	@Override
	public E retrieve(E obj) {
		if(isEmpty()){
			throw new EmptyCollectionException();
		}else{
			for(int i =0; i < currentSize ;i++)
				if(vector[i].compareTo(obj) == 0)
					return vector[i];

			throw new ElementNotFoundException();
		}
	}

	/**
	 * @return TRUE se a cole��o estiver com a capacidade m�xima. FALSE se n�o estiver cheia.
	 */
	public boolean isFull() {
		return currentSize == maxSize;
	}


	/**
	 *  � verificado antes de tentar crescer a cole��o se sua propriedade "isResizable", se for FALSE lan�ar� uma exce��o,
	 *  Se for TRUE ir� incrementar o tamanho atual da cole��o com o "resizeValue".
	 */
	protected void doubleCapacity() {
		if (!isResizable){
			throw new FullCollectionException();
		}

		if(currentSize == maxSize )
		{
			maxSize +=resizeValue;
			E[] temp = (E[])new Comparable[maxSize];

			for(int i = 0; i < currentSize; i++)
			{
				temp[i] = vector[i];
			}
			vector = temp;
		}
	}

	public int getMaxSize()
	{
		return maxSize;
	}

	public void setAutoRezise(int value){
		resizeValue = value;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public void setResizable(boolean resizable) {
		isResizable = resizable;
	}
}
