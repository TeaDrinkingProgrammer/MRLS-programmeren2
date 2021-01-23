package CCStatistics.Logic;

import java.util.ArrayList;
import CCStatistics.Domain.DatabaseEntity;

public interface Logic {
    ArrayList<DatabaseEntity> getAll();

    void create(DatabaseEntity databaseEntity);

    void update(DatabaseEntity databaseEntity);

    void delete(DatabaseEntity databaseEntity);
}
