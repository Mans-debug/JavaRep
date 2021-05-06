import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ProductListTEST {
    ProductList productList;
    ArrayList<Product> productArrayList;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void createSamples() throws IOException {

        productList = new ProductList(temporaryFolder.newFile());

        Product product1 = new Product("phone", 20);
        Product product2 = new Product("tablet", 15);
        Product product3 = new Product("cake", 4);

        productArrayList =
                new ArrayList<>(Arrays.asList(product1, product2, product3));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }

    /*@After
    public void emptyProductList() {
        productList = new ProductList(file);
    }*/

    @Test
    public void rewrite_test_expectedTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, NoSuchFieldException {
        Method rewrite = productList.getClass().getDeclaredMethod("rewrite", ArrayList.class);
        rewrite.setAccessible(true);
        rewrite.invoke(productList, productArrayList);
        Gson gson = new Gson();

        Field getFile = ProductList.class.getDeclaredField("file");
        getFile.setAccessible(true);

        File file = (File) getFile.get(productList);

        String resFromRewriteMethod = Files
                .readAllLines(Path.of(file.toURI()))
                .stream().collect(Collectors.joining());
        assertEquals(resFromRewriteMethod, gson.toJson(productArrayList));


    }

    @Test
    public void getList_test_expectedTrue() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Gson gson = new Gson();

        File tempFile = temporaryFolder.newFile();

        FileWriter fileWriter = new FileWriter(tempFile);
        fileWriter.write(gson.toJson(productArrayList));
        fileWriter.close();

        Field getFile = ProductList.class.getDeclaredField("file");
        getFile.setAccessible(true);

        File file = (File) getFile.get(productList);

        ProductList tempProductList = new ProductList(file);
        Method getList = productList.getClass().getDeclaredMethod("getList");
        getList.setAccessible(true);

        ArrayList<Product> assertionArrList = (ArrayList<Product>) getList.invoke(tempProductList);
        assertEquals(productArrayList.get(1).getName(), assertionArrList.get(1).getName());
    }

    @Test
    public void add_test_expectedTrue() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File file = temporaryFolder.newFile();

        ProductList listForTheTest = new ProductList(file);
        Product product = new Product("phone", 2);

        Method getList = ProductList.class.getDeclaredMethod("getList");
        getList.setAccessible(true);

        listForTheTest.add(product);
        ArrayList<Product> arrayList = (ArrayList<Product>) getList.invoke(listForTheTest);

        assertEquals(product.getName(), arrayList.get(0).getName());
    }

    @Test
    public void remove_ExistingProductTest_expectedTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getList = ProductList.class.getDeclaredMethod("getList");
        getList.setAccessible(true);

        assertTrue(productList.remove(1));

        ArrayList<Product> tempArrListOfProd = (ArrayList<Product>) getList.invoke(productList);
        assertNotEquals(productArrayList.size(), tempArrListOfProd.size());

        assertNotEquals(productArrayList.get(1).getName(), tempArrListOfProd.get(1).getName());
    }

    @Test
    public void remove_NonExistingProductTest_expectedFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getList = ProductList.class.getDeclaredMethod("getList");
        getList.setAccessible(true);

        assertFalse(productList.remove(6));
        assertFalse(productList.remove("table"));

        ArrayList<Product> tempArrListOfProd = (ArrayList<Product>) getList.invoke(productList);
        assertEquals(productArrayList.size(), tempArrListOfProd.size());

        assertEquals(productArrayList.get(1).getName(), tempArrListOfProd.get(1).getName());
        String expected = productArrayList.get(1).getName();
        String actual = tempArrListOfProd.get(1).getName();
        boolean resTest = expected.equals(actual);

        assertTrue(resTest);

    }

    @Test
    public void changeQuant_ExistingProductTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getList = ProductList.class.getDeclaredMethod("getList");
        getList.setAccessible(true);

        assertTrue(productList.changeQuant("tablet", 3));
        ArrayList<Product> arrayList = (ArrayList<Product>) getList.invoke(productList);
            assertTrue(productArrayList.get(1).getQuantity() != arrayList.get(1).getQuantity());
    }

    @Test
    public void changeQuant_NonExistingProductTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getList = ProductList.class.getDeclaredMethod("getList");
        getList.setAccessible(true);

        assertFalse(productList.changeQuant("bowl", 3));
        ArrayList<Product> arrayList = (ArrayList<Product>) getList.invoke(productList);
        assertEquals(productArrayList.get(1).getQuantity(),arrayList.get(1).getQuantity() );
    }

    @Test
    public void search_ExistingProduct()
    {
        //our productList contains the product below
        Product product = productArrayList.get(1);
        Product searchProd = productList.search(product.getName());
        assertNotNull(searchProd);
        assertEquals(product, searchProd);
    }
    @Test
    public void search_NonExistingProduct()
    {
        //our productList contains the product below
        Product product = productArrayList.get(1);
        Product nonExisting = new Product("fork", 4);
        Product searchProd = productList.search(nonExisting.getName());

        assertNull(searchProd);
    }
}
