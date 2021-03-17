public class Circle extends Shape
{
    private final double pi = 3.141592;
    private double area;

    public Circle(double r)
    {
        super(r);
        area = pi * r * r;
    }
    @Override
    void showArea()
    {
        System.out.println("Area of the circle equals " + area);
    }
}
