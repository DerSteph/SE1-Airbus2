import java.util.List;

public class Trolley {
    private int numberOfBeverages;
    private int numberOfMeals;
    private double totalWeightBeveragesMeals;

    public void fillBeverages(List<Beverage> beverages){
        System.out.println("Trolley Beverages filled");
    }

    public void fillMeals(List<Meal> meals){
        System.out.println("Trolley Meals filled");
    }
}
