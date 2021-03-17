public class Rectangle extends Shape
{
    private double area;
    public Rectangle(double a, double b)
    {
       super(a, b);
        area = a * b;
    }
    public Rectangle(double a)
    {
        super(a);
        area = a * a;
    }

    @Override
    void showArea()
    {
        System.out.println("Area of the rectangle equals " + area);
    }
}
