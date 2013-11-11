package imran.convert;

public interface Converter<T, U> {

    U convert(T in);

    T transform(U in);
}
