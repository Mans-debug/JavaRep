import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class CRUDHolder<T extends Comparable<T> & PrintableI> {
    Collection<T> items = new ArrayList<>();

    public void create(T item) throws IllegalArgumentException {
        List<T> list = new ArrayList<>(items);
        for (int i = 0; i < items.size(); i++) {
            if(item.compareTo(list.get(i)) == 0)
                throw new IllegalArgumentException();
        }
        items.add(item);
    }

    public T read(int id) throws NoSuchElementException {
        if (id >= items.size())
            throw new NoSuchElementException();
        List<T> list = new ArrayList<>(items);
        return list.get(id);
    }

    public void update(T item) throws NoSuchElementException {
        List<T> list = new ArrayList<>(items);
        for (int i = 0; i < items.size(); i++) {
            if(item.compareTo(list.get(i)) == 0){
                list.set(i, item);
                items = list;
            }
        }
        throw new NoSuchElementException();
    }

    public void delete(T item) throws NoSuchElementException {
        List<T> list = new ArrayList<>(items);
        for (int i = 0; i < items.size(); i++) {
            if(item.compareTo(list.get(i)) == 0){
                items.remove(item);
                return;
            }
        }
        throw new NoSuchElementException();
    }
}