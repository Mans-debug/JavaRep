public enum ReturnEnum
{
    IntegerType, StringType;

    @SuppressWarnings("unchecked")
    public <T> T comeback(String value) {
        switch (this) {
            case IntegerType:
                return (T) Integer.valueOf(value);
            case StringType:
                return (T) String.valueOf(value);
            default:
                return null;
        }
    }
}
