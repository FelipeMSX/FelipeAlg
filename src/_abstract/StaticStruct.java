package _abstract;

import _interfaces.Common;
import exception.*;

import java.util.Comparator;

/**
 * 	- Estrutura usada para criação das estrutura de dados com base em um vetor.Possui um valor padrão inicial de 100,
 * 	quando esse valor atingir o limite é incrementado de acordo com o resizeValue. Por padrão, a estrutura irar crescer
 * 	dinamicamente, mas, o usuário pode limitar isso.
 */
@SuppressWarnings("unchecked")
public abstract class StaticStruct<E> implements Common<E> {

	private Comparator<E> comparator;

	/**
	 * Vetor onde serão armazenados os objetos genéricos.
	 */
	protected E[] vector;
	/**
	 * Indica o atual tamanho da coleção.
	 */
	protected int currentSize;
	/**
	 *  Quantidade de elementos que a coleção pode conter.
	 */
	protected int maxSize;
	/**
	 * valor que será incrementado a coleção quando o seu valor máximo for atingido.
	 */
	protected int resizeValue;
	/**
	 * Ao atingir o máximo a coleção pode tentar crescer, se for TRUE isso será feito, caso contrário, ao atingir o
	 * máximo será lançado uma exceção.
	 */
	protected boolean isResizable;

	/**
	 * Construtor padrão que inicializa a lista com o valor padrão de 100. Por padrão cresce dinâmicamente.
	 */
	public StaticStruct()
	{
		maxSize 	= 100;
		resizeValue = 100;
		vector 		= (E[]) new Comparable[maxSize];
		isResizable = true;
	}

	/**
	 * @param maxSize Tamanho máximo que a coleção pode atingir.
	 */
	public StaticStruct(int maxSize)
	{
		this.maxSize 		= maxSize;
		resizeValue 		= 100;
		vector 				= (E[]) new Comparable[maxSize];
		isResizable 		= true;
	}

	/**
	 * @param maxSize Tamanho máximo que a coleção pode atingir.
	 * @param isResizable Indica se a coleção pode crescer dinamicamente.
	 */
	public StaticStruct(int maxSize, boolean isResizable)
	{
		this.maxSize 		= maxSize;
		this.isResizable 	= isResizable;
		resizeValue	 		= 100;
		vector 				= (E[]) new Comparable[maxSize];

	}

	/**
	 * @param maxSize Tamanho máximo que a coleção pode atingir.
	 */
	public StaticStruct(int maxSize, Comparator<E> comparator)
	{
		this.maxSize 		= maxSize;
		this.comparator 	= comparator;
		resizeValue 		= 100;
		vector 				= (E[]) new Comparable[maxSize];
		isResizable 		= true;
	}

	/**
	 * @param maxSize Tamanho máximo que a coleção pode atingir.
	 * @param isResizable Indica se a coleção pode crescer dinamicamente.
	 */
	public StaticStruct(int maxSize, boolean isResizable, Comparator<E> comparator)
	{
		this.maxSize 		= maxSize;
		this.isResizable 	= isResizable;
		this.comparator		= comparator;
		resizeValue	 		= 100;
		vector 				= (E[]) new Comparable[maxSize];

	}

	/**
	 * @return TRUE se a lista estiver vazia, caso contrário, será FALSE.
	 */
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Se a coleção estiver vazia lança uma exceção, caso contrário, irá tentar recuperar o primeiro elemento da coleção.
	 * @return Obtém o primeiro elemento da coleção.
	 */
	@Override
	public E getFirst() {
		if(isEmpty())
			throw new EmptyCollectionException();
		else
			return vector[0];
	}

	/**
	 * Se a coleção estier vazia lança uma exceção, caso contrário, irá tentar recuperar o último elemento da coleção.
	 * @return Obtém o último elemento da coleção.
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
	 * Irá apagar a coleção por completo.
	 */
	@Override
	public void disposeAll() {
		vector = (E[]) new Comparable[maxSize];
		currentSize = 0;
	}

	/**
	 * Se a coleção for vazia lançará uma exceção, caso contrário, irá procurar o elemento na lista de acordo com o
	 * parâmetro, se ainda assim nenhum objeto for encontrado também lançará uma exceção.
	 * @param obj Usado como parâmetro de comparação.
	 * @return Retorna o objeto com todos os dados.
	 */
	@Override
	public E retrieve(E obj) {
		if(isEmpty()){
			throw new EmptyCollectionException();
		}else{
			if(comparator == null)
				throw new ComparerNotSetException();
			for(int i =0; i < currentSize ;i++)
				if(comparator.compare(vector[i],obj) == 0)
					return vector[i];

			throw new ElementNotFoundException();
		}
	}

	/**
	 * @return TRUE se a coleção estiver com a capacidade máxima. FALSE se não estiver cheia.
	 */
	public boolean isFull() {
		return currentSize == maxSize;
	}


	/**
	 *  É verificado antes de tentar crescer a coleção se sua propriedade "isResizable", se for FALSE lançará uma exceção,
	 *  Se for TRUE irá incrementar o tamanho atual da coleção com o "resizeValue".
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

	public int compareTo(E o1, E o2) {
		if (comparator == null)
			throw new ComparerNotSetException();
		return comparator.compare(o1, o2);
	}

	public Comparator<E> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<E> comparator) {
		this.comparator = comparator;
	}
}
