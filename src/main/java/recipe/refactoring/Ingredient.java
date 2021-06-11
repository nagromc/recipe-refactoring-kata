package recipe.refactoring;

public class Ingredient {
  public final String name;
  public final double q;
  public String s;

  public Ingredient(String name, double quantity) {
    this.name = name;
    this.q = quantity;
  }

  @Override
  public String toString() {
    return name;
  }
}
