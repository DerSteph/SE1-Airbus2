import java.util.ArrayList;

public interface ICostOptimizer {

    String version();

    boolean on();

    int add(CheckPoint checkPoint);

    int remove(int checkPoint);

    int optimize(ArrayList<CheckPoint> checkPointList);

    boolean validate(int costIndex);

    boolean off();
    
}
