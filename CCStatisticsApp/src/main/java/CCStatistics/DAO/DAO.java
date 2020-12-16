package CCStatistics.DAO;
import java.util.List;

public interface DAO {
    List getAll();
     
    void save();
     
    void update();
     
    void delete();
}