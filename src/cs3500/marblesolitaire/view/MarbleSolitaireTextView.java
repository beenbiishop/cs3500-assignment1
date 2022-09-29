package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a textual view of a Marble Solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;

  /**
   * Constructs a new text view of the given model.
   *
   * @param model the model object to be represented
   * @throws IllegalArgumentException if the given model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.model = model;
  }

  @Override
  public String toString() {
    String state = "";
    for (int row = 0; row < this.model.getBoardSize(); row++) {
      for (int col = 0; col < this.model.getBoardSize(); col++) {
        switch (this.model.getSlotAt(row, col)) {
          case Marble:
            state += "O";
            break;
          case Invalid:
            state += " ";
            break;
          case Empty:
            state += "_";
            break;
        }
        if (col != this.model.getBoardSize() - 1) {
          state += " ";
        }
      }
      if (row != this.model.getBoardSize() - 1) {
        state += "\n";
      }
    }
    return state;
  }
}
