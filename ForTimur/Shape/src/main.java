import com.google.gson.Gson;

import java.io.*;

public class main
{
    public static void main(String[] args)
    {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Triangle(4, 5);
        shapes[1]  = new Rectangle(23, 7);
        shapes[2]  = new Circle(8);
        shapes[3]  = new Circle(54);

       try
       {
           File file = new File(".\\yourlife.txt");
           FileOutputStream fileOutputStream
                   = new FileOutputStream(file);
           ObjectOutputStream objectOutputStream
                   = new ObjectOutputStream(fileOutputStream);
           objectOutputStream.writeObject(shapes);
           objectOutputStream.flush();
           objectOutputStream.close();
       }
       catch (Exception e)
       {
           System.out.println("Ты долбоеб");
           return;
       }

    }
}
