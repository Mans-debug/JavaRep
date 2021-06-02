record Person(String name, int age) implements Comparable<Person>, PrintableI {

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}