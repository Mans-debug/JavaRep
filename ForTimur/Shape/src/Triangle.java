public class Triangle extends Shape
{
   /* private double h;
    private double w;*/
    private double area;

    public Triangle(double h, double w)
    {
        super(h, w);
        area = h * w / 2;
    }
    @Override
    void showArea()
    {
        System.out.println("Area of the triangle is " + area);
    }
}
