import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Before;
import org.junit.Test;

public class MarbleSolitaireTextViewTest {

  MarbleSolitaireModel armThickness3;
  MarbleSolitaireModel armThickness5;
  MarbleSolitaireView viewArmThickness3;
  MarbleSolitaireView viewArmThickness5;

  @Before
  public void setUp() {
    armThickness3 = new EnglishSolitaireModel(3);
    armThickness5 = new EnglishSolitaireModel(5);
    viewArmThickness3 = new MarbleSolitaireTextView(armThickness3);
    viewArmThickness5 = new MarbleSolitaireTextView(armThickness5);
  }

  @Test
  public void testToString() {
    assertEquals(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", viewArmThickness3.toString());
    assertEquals(
        "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", viewArmThickness5.toString());

  }
}