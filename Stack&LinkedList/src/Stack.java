import java.util.Iterator;

public class Stack<T> implements Iterable<T>
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

    private Node<T> head;



    public void push(T value)
    {

        Node temp = head;
        head = new Node(value);
        head.next = temp;

    }

    public T pop() throws Exception
    {
        if(head != null)
        {
            Node temp = head;
            head = head.next;
            return (T) temp.value;
        }
        else
            throw new Exception("The head is null");
    }

    public T peek() throws Exception
    {
        if(head != null)
        return head.value;
        else
            throw new Exception("The head is null");
    }

    public void add(T value)
    {
        Node temp = head;

        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = new Node(value);
    }

    public Iterator<T> iterator()
    {

        return new Iterator<T>()
        {
            int counter = 0;
            Node<T> node = head;
            @Override
            public boolean hasNext()
            {
                if(node == head)
                    counter++;
                if(counter == 2)
                {
                    counter = 0;
                    return false;
                }
                return node!=null;
            }

            @Override
            public T next()
            {

                Node<T> temp = node;
                node = temp.next;
                if(hasNext() == false)
                    node = head;
                return temp.value;
            }
        };
    }

    public void print()
    {
        Node temp = head;
        System.out.println(temp.value);
        while (temp.next != null)
        {
            temp = temp.next;
            System.out.println(temp.value);
        }
    }


}

