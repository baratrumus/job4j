package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * контейнер на базе связанного списка
 */
public class DynamicLinkedList<E> implements Iterable<E> {
    protected Node<E> first;
    protected Node<E> last;
    protected int size = 0;
    private int modCount = 0;

    public DynamicLinkedList() {
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> pointer = first;
            Node<E> current;
            int ind = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return ind < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Лист закончился");
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Недопустимые изменения");
                }
                ind++;
                current = pointer;
                pointer =  pointer.next;
                return current.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    /**
     * добавляет  элемент  в конец списка
     */
    public  void add(E value) {
        final Node<E> tmp = last;
        final Node<E> newNode = new Node<>(value);
        if (size == 0) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public Node<E> get(int ind) {
        if ((ind >= size) || (ind < 0)) {
            throw new IndexOutOfBoundsException("Такого индекса нет");
        }
        Node<E> result = this.first;
        for (int i = 0; i < ind; i++) {
            result = result.next;
        }
        return result;
    }


    public E getData(Node<E> node) {
        return node.data;
    }


    /**
     * Метод удаляет последнюю ноду в списке и возвращает значение
     */
     public E removeLast() {
         if (size == 0) {
             return null;
         }
         E res = last.data;
         if (size > 1) {
             last.data = null;
             Node<E> previous = get(size - 2);
             previous.next = null;
             last = previous;
         } else if (size == 1) {
             last = null;
             first = null;
         }
         modCount++;
         size--;
         return res;
     }


    /**
     * Класс предназначен для хранения данных.
     */
    protected static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }
}
