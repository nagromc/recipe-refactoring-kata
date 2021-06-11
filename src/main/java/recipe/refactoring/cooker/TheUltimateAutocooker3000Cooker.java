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
    // let’s just pretend that the action is a very complex sequence of things the robot needs to do
    actions.add(action);
  }

  public List<CookerAction> getActions() {
    return actions;
  }

}
