package de.exxcellent.challenge.dataparsers;

// Simple interface to parse a string representing one entity into a POJO.

public interface IParseElements<T> {
    public T parseToDataElement(String element);
}
