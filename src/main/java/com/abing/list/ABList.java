package com.abing.list;

public interface ABList<E> {

    public void add(E e);

    public E get(int index);

    public int getSize();

    public E remove(int index);

    public boolean remove(E e);

    public void add(int index,E e);
}
