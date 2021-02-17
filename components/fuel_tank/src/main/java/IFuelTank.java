public interface IFuelTank
{
   public  String version();
   public int takeOut(int amount);
   public int refill();
   public int refill(int refill);
}
