public interface IPortableWaterTank {
    String version();
    boolean lock();
    boolean unlock();
    int takeOut(int amount);
    int refill();
    int refill(int amount);
}
