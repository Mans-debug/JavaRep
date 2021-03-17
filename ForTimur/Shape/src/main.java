import com.google.gson.Gson;

public class main
{
    public static void main(String[] args)
    {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Triangle(4, 5);
        shapes[1]  = new Rectangle(23, 7);
        shapes[2]  = new Circle(8);
        shapes[3]  = new Circle(54);
       /* for (int i = 0; i < shapes.length; i++)
        {
            shapes[i].showArea();
        }*/
        Gson gson = new Gson();
        for (int i = 0; i < shapes.length; ++i)
        {
            System.out.println(gson.toJson(shapes[i]));
        }

    }
}
