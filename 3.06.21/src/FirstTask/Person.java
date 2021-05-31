package FirstTask;

import java.io.Serializable;

record Person(String name, int age, Countries country) implements Serializable {}