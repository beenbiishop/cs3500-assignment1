import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import org.junit.Assert;
import org.junit.Test;

public class EnglishSolitaireModelTest {

  @Test
  public void getBoardSize() {
    Assert.assertEquals(1, new EnglishSolitaireModel(1).getBoardSize());
    assertEquals(7, new EnglishSolitaireModel(3).getBoardSize());
    assertEquals(13, new EnglishSolitaireModel(5).getBoardSize());
    assertEquals(19, new EnglishSolitaireModel(7).getBoardSize());
  }

  @Test
  public void getSlotAt() {
    Assert.assertEquals(MarbleSolitaireModelState.SlotState.Empty,
        new EnglishSolitaireModel(1).getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
        new EnglishSolitaireModel(5).getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
        new EnglishSolitaireModel(2, 3).getSlotAt(2, 0));
  }

  @Test
  public void getScore() {
    assertEquals(0, new EnglishSolitaireModel(1).getScore());
    assertEquals(32, new EnglishSolitaireModel(3).getScore());
    assertEquals(32, new EnglishSolitaireModel().getScore());
    assertEquals(104, new EnglishSolitaireModel(5).getScore());
    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    model.move(8, 6, 6, 6);
    model.move(10, 6, 8, 6);
    assertEquals(102, model.getScore());
  }

  @Test
  public void move() {
    MarbleSolitaireModel model1 = new EnglishSolitaireModel(3);
    model1.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 1));
    model1.move(3, 2, 3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 4));

  }

  @Test
  public void isGameOver() {
    assertTrue(new EnglishSolitaireModel(1).isGameOver());
    assertFalse(new EnglishSolitaireModel().isGameOver());
  }
}