package ru.zdoher.hw03;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private int size;
    private boolean debug;
    private T[] objects;

    public DIYarrayList() {
        this.createObjectArray(0, false);
    }

    public DIYarrayList(int size) {
        this.createObjectArray(size, false);
        this.size = size;
    }

    public DIYarrayList(boolean debug) {
        this.createObjectArray(0, debug);
    }

    public DIYarrayList(int size, boolean debug) {
        createObjectArray(size, debug);
    }

    private void createObjectArray(int size, boolean debug) {
        if (size < 0) throw new IllegalArgumentException();

        this.size = size;
        objects = (T[]) new Object[this.size];
        this.debug = debug;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.objects, size);
    }

    private T[] grow() {
        return Arrays.copyOf(objects, newSize());
    }

    private int newSize() {
        int newSize = objects.length * 2 + 1;
        if (newSize <= 0) newSize = Integer.MAX_VALUE;
        if (debug) {
            System.out.println("old size: " + size);
            System.out.println("new size: " + newSize);
        }
        return newSize;
    }

    @Override
    public boolean add(T t) {
        if (size == objects.length) {
            if (debug) System.out.println("!!!array need to grow!!!");
            objects = this.grow();
        }

        objects[size] = t;
        size += 1;

        return true;
    }

    @Override
    public T get(int i) {
        if (i < size && i >= 0) {
            return objects[i];
        }
        throw new ArrayIndexOutOfBoundsException("array index out of bounds exception");
    }

    @Override
    public void add(int i, T t) {

    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYarrayList.ListItr(0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(this.get(i)).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private class ListItr extends DIYarrayList<T>.Itr  implements ListIterator<T> {

        ListItr(int index) {
            super();
            this.cursor = index;
        }

        public int nextIndex() {
            return this.cursor;
        }

        public void set(T t) {
            if(cursor < 0) {
                throw new IllegalStateException();
            } else {
                objects[cursor - 1] = t;
            }
        }

        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T previous() {
            throw new UnsupportedOperationException();
        }

        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    private class Itr implements Iterator<T> {
        int cursor;
        @Override
        public boolean hasNext() {
            return this.cursor != DIYarrayList.this.size;
        }
        @Override
        public T next() {
            if(cursor >= size) {
                throw new NoSuchElementException("No more elements");
            } else {
                cursor++;
                return objects[cursor - 1];
            }
        }
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public T set(int i, T t) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException("a");
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException("a");
    }
}
