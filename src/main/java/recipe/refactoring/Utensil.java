package recipe.refactoring;

public class Utensil {
  public final String name;

  public Utensil(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
