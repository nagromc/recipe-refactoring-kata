package recipe.refactoring.cooker;

import java.util.ArrayList;
import java.util.List;

public class TheUltimateAutocooker3000Cooker implements Cooker {

  private final List<CookerAction> actions;

  public TheUltimateAutocooker3000Cooker() {
    actions = new ArrayList<>();
  }

  @Override
  public void doAction(CookerAction action) {
    actions.add(action);
  }

  public List<CookerAction> getActions() {
    return actions;
  }

}
