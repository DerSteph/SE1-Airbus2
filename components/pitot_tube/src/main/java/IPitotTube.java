public interface IPitotTube
{
    public String version();
    public int measureStaticPressure();
    public int measureTotalPressure();
    public int measureVelocity();
    public void clean();
}
