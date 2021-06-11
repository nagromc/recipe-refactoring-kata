package recipe.refactoring;

import recipe.refactoring.cooker.Cooker;
import recipe.refactoring.cooker.CookerAction;

public class NiceRecipe {

  private final Ingredient flour;
  private final Ingredient eggs;
  private final Ingredient milk;
  private final Ingredient salt;
  private final Ingredient sugar;
  private final Ingredient butter;

  private final Utensil bigMixingBowl;
  private final Utensil smallMixingBowl;
  private final Utensil whisk;
  private final Utensil microwave;

  private final Cooker cooker;

  public NiceRecipe(int personNumber, Cooker cooker) {
    flour = new Ingredient("flour", 65 * personNumber);
    eggs = new Ingredient("egg", personNumber);
    milk = new Ingredient("milk", 125 * personNumber);
    salt = new Ingredient("salt", 2 * personNumber);
    sugar = new Ingredient("sugar", 2 * personNumber);
    butter = new Ingredient("butter", 50 * personNumber);

    bigMixingBowl = new Utensil("4L mixing bowl");
    smallMixingBowl = new Utensil("1L mixing bowl");
    whisk = new Utensil("whisk");
    microwave = new Utensil("microwave oven");

    this.cooker = cooker;
  }

  public void doRecipe() {
    putFlourInBigMixingBowl();
    putSaltInBigMixingBowl();
    putSugarInBigMixingBowl();
    meltButter();
    putButterInBigMixingBowl();
    scrambleEggs();
    putEggsInBigMixingBowl();
    slowlyPutMilkInMixingBowl();
    letRest();
  }

  private void putFlourInBigMixingBowl() {
    putIn(flour, bigMixingBowl);
  }

  private void putSaltInBigMixingBowl() {
    putIn(salt, bigMixingBowl);
  }

  private void putSugarInBigMixingBowl() {
    putIn(sugar, bigMixingBowl);
  }

  private void meltButter() {
    moveArmToLeft();
    closeHand(butter);
    moveArmToRight();
    openHand(butter, microwave);
    butter.s = "melted";
  }

  private void putButterInBigMixingBowl() {
    putIn(butter, bigMixingBowl);
  }

  private void scrambleEggs() {
    moveArmToLeft();
    closeHand(eggs);
    moveArmToRight();
    openHand(eggs, smallMixingBowl);
    mixSmallMixingBowl();
    eggs.s = "scrambled";
  }

  private void putEggsInBigMixingBowl() {
    putIn(eggs, bigMixingBowl);
  }

  private void slowlyPutMilkInMixingBowl() {
    double remainingQtyOfMilkToMix = milk.q;

    while (remainingQtyOfMilkToMix > 0) {
      double someMilkQty = milk.q * 0.20;
      putSomeMilkInBigMixingBowl(someMilkQty);
      remainingQtyOfMilkToMix -= someMilkQty;
      mixBigBowl();
    }
  }

  private void putSomeMilkInBigMixingBowl(double someMilkQty) {
    Ingredient someMilk = new Ingredient("some milk", someMilkQty);
    putIn(someMilk, bigMixingBowl);
  }

  private void mixBigBowl() {
    switchUtensilTo(whisk);
    turnHand(smallMixingBowl);
    switchUtensilTo(null);
  }

  private void putIn(Ingredient ingredient, Utensil bowl) {
    takeIngredient(ingredient);
    releaseIngredientInBowl(ingredient, bowl);
  }

  private void takeIngredient(Ingredient ingredient) {
    moveArmToLeft();
    closeHand(ingredient);
  }

  private void releaseIngredientInBowl(Ingredient ingredient, Utensil bowl) {
    moveArmToRight();
    openHand(ingredient, bowl);
  }

  private void letRest() {
    takeABreakDuring(60);
  }

  private void moveArmToLeft() {
    cooker.doAction(new CookerAction("move arm to left"));
  }

  private void closeHand(Ingredient ingredient) {
    cooker.doAction(new CookerAction("close hand to take " + ingredient));
  }

  private void moveArmToRight() {
    cooker.doAction(new CookerAction("move arm to right"));
  }

  private void openHand(Ingredient ingredient, Utensil utensil) {
    cooker.doAction(new CookerAction(String.format("open hand to release %s in %s", ingredient, utensil)));
  }

  private void mixSmallMixingBowl() {
    switchUtensilTo(whisk);
    turnHand(bigMixingBowl);
    switchUtensilTo(null);
  }

  private void turnHand(Utensil utensil) {
    cooker.doAction(new CookerAction("turn hand above " + utensil));
  }

  private void switchUtensilTo(Utensil utensil) {
    cooker.doAction(new CookerAction("change utensil to " + (utensil == null ? "nothing" : utensil)));
  }

  private void takeABreakDuring(int minutes) {
    cooker.doAction(new CookerAction(String.format("chillin during %s minutes", minutes)));
  }

}
