import java.util.Iterator;

public class LinkedList <T>
{
    static class Node<T>
    {
        private T value;
        private Node<T> next;

        private Node(T value)
        {
            this.value = value;
        }
    }

    private Node<T> root;

    void add(T value)
    {
        Node<T> node = new Node<>(value);
        if(root == null)
        {
            root = node;
            return;
        }
        Node temp = root;
        while (temp.next!=null)
        {
            temp = temp.next;
        }
        temp.next = node;
    }
    int size()
    {
        int counter = 0;
        Node temp = root;
        while (temp.next!=null)
        {
            counter++;
            temp = temp.next;

        }
        return counter;
    }
    T get(int i)
    {
        int counter = 0;
        Node<T> temp = root;
        while (counter != i)
        {
            counter++;
            temp = temp.next;
        }
        return temp.value;
    }
    void remove(int i)
    {
        
    }
    void printList()
    {
        Node<T> temp = root;
        while (temp!=null)
        {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}