package CCStatistics.Logic;

import java.util.ArrayList;

public interface LogicTwoParam<T> {
    ArrayList<T> getAll();

    void create(T object, String string);

    void update(T object, String string);

    void delete(T object, String string);
}
