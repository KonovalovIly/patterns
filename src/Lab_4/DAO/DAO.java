package Lab_4.DAO;

public interface DAO<T> {

    T read();

    void write(T entity);
}
