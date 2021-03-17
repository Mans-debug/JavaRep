public abstract class Shape
{
    private double height;
    private double width;
    Shape()
    {

    }
    Shape(double h, double width)
    {
        height = h;
        width = width;
    }
    Shape(double height)
    {
        this.height = height;
    }
    abstract void showArea();

}
