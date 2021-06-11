package recipe.refactoring;

import recipe.refactoring.cooker.Cooker;
import recipe.refactoring.cooker.CookerAction;

public class MessyRecipe {

  public static final String SM = "some milk";
  public static final String M = "melted";
  public static final String S = "scrambled";
  public static final double Q = 0.20;
  private final Ingredient f;
  private final Ingredient e;
  private final Ingredient m;
  private final Ingredient sa;
  private final Ingredient su;
  private final Ingredient b;

  private final Utensil bb;
  private final Utensil sb;
  private final Utensil w;
  private final Utensil mw;

  private final Cooker c;

  public MessyRecipe(int personNumber, Cooker cooker) {
    f = new Ingredient("flour", 65 * personNumber);
    e = new Ingredient("egg", personNumber);
    m = new Ingredient("milk", 125 * personNumber);
    sa = new Ingredient("salt", 2 * personNumber);
    su = new Ingredient("sugar", 2 * personNumber);
    b = new Ingredient("butter", 50 * personNumber);

    bb = new Utensil("4L mixing bowl");
    sb = new Utensil("1L mixing bowl");
    w = new Utensil("whisk");
    mw = new Utensil("microwave oven");

    this.c = cooker;
  }

  /**
   * This method has been copied from another recipe
   */
  public void doRecipe() {
    // étaler la pâte dans un moule
    moveArmToLeft();
    closeHand(f);
    moveArmToRight();
    openHand(f, bb);
    // cuire les lardons
    moveArmToLeft();
    closeHand(sa);
    moveArmToRight();
    openHand(sa, bb);
    // ouvrir le pot de crème fraîche
    moveArmToLeft();
    closeHand(su);
    moveArmToRight();
    openHand(su, bb);
    // verser le pot de crème dans le bol
    moveArmToLeft();
    closeHand(b);
    moveArmToRight();
    openHand(b, mw);
    b.s = M;
    // casser les oeufs dans le bol
    moveArmToLeft();
    closeHand(b);
    moveArmToRight();
    openHand(b, bb);
    // ajouter la muscade
    moveArmToLeft();
    closeHand(e);
    moveArmToRight();
    openHand(e, sb);
    // mélanger les oeufs et la crème
    switchUtensilTo(w);
    turnHand(bb);
    switchUtensilTo(null);
    e.s = S;
    // verser le mélange sur la pâte
    moveArmToLeft();
    closeHand(e);
    moveArmToRight();
    openHand(e, bb);
    double r = m.q;

    // ajouter le gruyère jusqu’à ce que le mélange en soit recouvert
    while (r > 0) {
      double mq = m.q * Q;
      Ingredient cheese = new Ingredient(SM, mq);
      moveArmToLeft();
      closeHand(cheese);
      moveArmToRight();
      openHand(cheese, bb);
      r -= mq;
      switchUtensilTo(w);
      turnHand(sb);
      switchUtensilTo(null);
    }
    // cuire pendant 60 minutes
    cookDuring(60);
  }

  private void cookDuring(int minutes) {
    c.doAction(new CookerAction(String.format("chillin during %s minutes", minutes)));
  }

  private void turnHand(Utensil utensil) {
    c.doAction(new CookerAction("turn hand above " + utensil));
  }

  private void switchUtensilTo(Utensil utensil) {
    c.doAction(new CookerAction("change utensil to " + (utensil == null ? "nothing" : utensil)));
  }

  private void openHand(Ingredient ingredient, Utensil utensil) {
    c.doAction(new CookerAction(String.format("open hand to release %s in %s", ingredient, utensil)));
  }

  private void moveArmToRight() {
    c.doAction(new CookerAction("move arm to right"));
  }

  private void closeHand(Ingredient ingredient) {
    c.doAction(new CookerAction("close hand to take " + ingredient));
  }

  private void moveArmToLeft() {
    c.doAction(new CookerAction("move arm to left"));
  }

}
