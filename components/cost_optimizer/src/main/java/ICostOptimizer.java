public interface ICostOptimizer {

    String version();

    boolean on();

    int add(CheckPoint checkPoint);

    int remove(int checkPoint);

    //ArrayList??

    boolean validate(int costIndex);

    boolean off();
    
}
