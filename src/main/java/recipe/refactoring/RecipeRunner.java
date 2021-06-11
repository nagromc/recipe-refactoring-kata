package recipe.refactoring;

import recipe.refactoring.cooker.TheUltimateAutocooker3000Cooker;

public class RecipeRunner {
  public static void main(String[] args) {
    TheUltimateAutocooker3000Cooker cooker = new TheUltimateAutocooker3000Cooker();

    runMessyRecipe(cooker);
//    runNiceRecipe(cooker);

    cooker.getActions().stream()
      .map(cookerAction -> cookerAction.name)
      .forEachOrdered(System.out::println);
  }

  private static void runMessyRecipe(TheUltimateAutocooker3000Cooker cooker) {
    MessyRecipe recipe = new MessyRecipe(4, cooker);
    recipe.doRecipe();
  }

  private static void runNiceRecipe(TheUltimateAutocooker3000Cooker cooker) {
    NiceRecipe recipe = new NiceRecipe(4, cooker);
    recipe.doRecipe();
  }
}
