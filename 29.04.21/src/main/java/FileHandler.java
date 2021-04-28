import java.util.ArrayList;

public interface FileHandler<T> {
    <T> void rewrite(ArrayList<T> list);
    ArrayList<T> getList();
}
