package _abstract;

import _interfaces.Common;
import exception.EmptyCollectionException;
import exception.FullCollectionException;

/**
 * Created by Felipe on 06/10/2015.
 */
/*
	Descrição:
		- Estrutura usada para criação das estrutura de dados com base em um vetor.
		- Possui um valor padrão inicial de 100, quando esse valor atingir o limite é incrementado de acordo com o
			resizeValue.
		- Por padrão, a estrutura irar crescer dinamicamente, mas, o usuário pode limitar isso.

 */
public abstract class StaticStruct<E extends  Comparable<E>> implements Common<E> {

	protected E[] vector;
	protected int currentSize;
	protected int maxSize;
	//Valor usado quando coleção aumenta sua capacidade, esse valor é incremenado ao atual tamanho do vetor.
	protected int resizeValue;
	protected boolean isResizable;

	public StaticStruct()
	{
		maxSize 	= 100;
		resizeValue = 100;
		vector 		= (E[]) new Comparable[maxSize];
		isResizable = true;
	}

	public StaticStruct(int maxSize)
	{
		this.maxSize 		= maxSize;
		resizeValue 		= 100;
		vector 				= (E[]) new Comparable[maxSize];
		isResizable 		= true;
	}

	public StaticStruct(int maxSize, boolean isResizable)
	{
		this.maxSize 		= maxSize;
		this.isResizable 	= isResizable;
		resizeValue	 		= 100;
		vector 				= (E[]) new Comparable[maxSize];

	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public E getFirst() {
		if(!isEmpty())
			return vector[0];
		else
			throw new EmptyCollectionException();
	}

	@Override
	public E getLast() {
		if(!isEmpty())
			return vector[currentSize -1];
		else
			throw new EmptyCollectionException();
	}

	@Override
	public int getCurrentSize()
	{
		return currentSize;
	}

	@Override
	public void disposeAll() {
		vector = null;
		currentSize = 0;
	}

	@Override
	public E retrieve(E obj) {
		if(!isEmpty()){

			for(int i =0; i < currentSize ;i++)
				if(vector[i].compareTo(obj) == 0)
					return vector[i];

			return null;
		}else{
			throw new EmptyCollectionException();
		}
	}

	public boolean isFull()
	{
		return currentSize == maxSize;
	}

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
