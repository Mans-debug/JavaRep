import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductList implements FileHandler {
    File file;

    public ProductList(File file) {
        this.file = file;
        List<Product> productList = new ArrayList<>();
        Gson gson = new Gson();
        if (getList() == null) {

            try {
                new FileWriter(file, false).close();// deletes the contents of the file
                FileWriter writer = new FileWriter(file);
                String str = gson.toJson(productList);
                writer.append(str);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /*private void rewrite(ArrayList<Product> arrayList) {
        Gson gson = new Gson();
        try {
            new FileWriter(file, false).close();// deletes the contents of the file
            FileWriter writer = new FileWriter(file);
            String str = gson.toJson(arrayList);
            writer.append(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void rewrite(ArrayList list) {
        Gson gson = new Gson();
        try {
            new FileWriter(file, false).close();// deletes the contents of the file
            FileWriter writer = new FileWriter(file);
            String str = gson.toJson(list);
            writer.append(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getList() //method to get an ArrayList from json file
    {
        Gson gson = new Gson();
        ArrayList<Product> arrayList = null;
        // we need this to convert json to arrayList correctly
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        try {
            arrayList = (ArrayList<Product>) gson.fromJson(new FileReader(file), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void add(Product product) {
        ArrayList<Product> arrayList = getList();
        if (arrayList == null) {
            System.out.println("Array list is null");
            return;
        }
        Boolean flag = false;
        for (Product i : arrayList) {
            if (i.equals(product)) {
                i.incQuant(product.getQuantity());
                flag = true;
            }
        }
        if (!flag) {
            arrayList.add(product);
        }
        rewrite(arrayList);
    }

    public boolean changeQuant(String name, int quantity) {
        ArrayList<Product> arrayList = getList();
        if (arrayList == null) {
            System.out.println("Array list is null");
            return false;
        }
        Boolean flag = false;
        for (Product i : arrayList) {
            if (i.equals(name)) {
                i.incQuant(quantity);
                flag = true;
            }
        }
        if (!flag)
            return flag;
        rewrite(arrayList);
        return flag;
    }

    public String toString() {
        ArrayList<Product> arrayList = getList();
        String res = "";
        for (Product i : arrayList) {

            res += i + ", ";

        }
        return ('{' + res + '}').replace(", }", "}");
    }

    public boolean remove(String name) {
        ArrayList<Product> arrayList = getList();
        if (arrayList == null) {
            System.out.println("List is null");
            return false;
        }
        Boolean flag = false;
        Product rmProduct = null;
        for (Product i : arrayList) {
            if (i.equals(name)) {
                flag = true;
                rmProduct = i;
            }
        }
        if (!flag)
            return flag;
        arrayList.remove(rmProduct);
        rewrite(arrayList);
        return flag;
    }

    public boolean remove(int index) {
        ArrayList<Product> arrayList = getList();
        if (arrayList == null) {
            System.out.println("List is null");
            return false;
        }
        Boolean flag = false;
        if(index >= arrayList.size())
            return false;

            flag = true;
            arrayList.remove(index);

        if (!flag)
            return flag;
        rewrite(arrayList);
        return flag;
    }

    public Product search(String prodName)
    {
        ArrayList<Product> productArrayList = getList();

        for(Product pr : productArrayList)
        {
            if(pr.getName().equals(prodName))
                return pr;
        }
        return null;
    }


}
