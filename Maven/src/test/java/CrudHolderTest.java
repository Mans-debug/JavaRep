import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.junit.Assert.*;
public class CrudHolderTest {
    CRUDHolder<Person> holder;
    @Before
    public void SetUp(){
        holder = new CRUDHolder<>();
        holder.create(new Person("Ivar", 25));
        holder.create(new Person("Ragnar", 47));
        holder.create(new Person("Lagertha", 40));
    }
    @After
    public void TearDown(){
        holder = null;
    }
    @Test
    public void create_true(){
        Person person = new Person("TEST", 99);
        holder.create(person);
        assertEquals(person, holder.items.toArray()[3]);
    }
    @Test
    public void create_false(){
        Person person = new Person("Ivar", 25);
        try {
            holder.create(person);
        }
        catch (Exception e){

        }
        assertNotEquals(holder.items.toArray()[holder.items.size() - 1]
                , person);
    }
    @Test
    public void read_true(){
        Person person = holder.items.stream().toList().get(0);
        assertEquals(person, holder.read(0));
    }
    @Test
    public void read_false(){
        int size = holder.items.size();
        int id = size + 1;
        try {
            holder.read(id);
            fail();
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
    @Test
    public void update_true(){
        Person before = holder.items.stream().toList().get(0);
        holder.update(new Person("Ivar", 52));
        Person after = holder.items.stream().toList().get(0);
        assertNotEquals(before, after);
    }
    @Test
    public void update_false(){
        try {
            holder.update(new Person("TEST", 99));
            fail();
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
    @Test
    public void delete_true(){
        Person before = holder.items.stream().toList().get(0);
        holder.delete(before);
        assertNotEquals(before,
                holder.items.stream().toList().get(0));
    }
    @Test
    public void delete_false(){
        Person before = holder.items.stream().toList().get(0);
        try {
            holder.delete(new Randomizer().nextRandom());
            fail();
        }
        catch (NoSuchElementException e){
            assertEquals(before,
                    holder.items.stream().toList().get(0));
        }


    }

}
