public class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C>
{
    public final D fourth;

    public FourTuple(A f, B s, C t, D fourth)
    {
        super(f, s, t);

        this.fourth = fourth;
    }
}
