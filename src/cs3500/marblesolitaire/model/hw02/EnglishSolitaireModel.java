package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a marble solitaire game with the English rules.
 * <p>
 * The class will instantiate the game/board, handle moves from the player.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private SlotState[][] board;

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot in the center.
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.sRow = 3;
    this.sCol = 3;
    this.board = new SlotState[7][7];
    this.initializeBoard();
  }

  /**
   * Constructs a new model with the default arm thickness of 3 and the empty slot at the specified
   * row and column.
   *
   * @param sRow the row of the starting empty slot
   * @param sCol the column of the starting empty slot
   * @throws IllegalArgumentException if the empty slot position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    if (!isValidSlotArmThickness(sRow, sCol, 3)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.armThickness = 3;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = new SlotState[7][7];
    this.initializeBoard();
  }

  /**
   * Constructs a new model with the specified arm thickness and the empty slot in the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
    this.armThickness = armThickness;
    this.sRow = (armThickness - 1) / 2;
    this.sCol = (armThickness - 1) / 2;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.initializeBoard();
  }

  /**
   * Constructs a new model with the specified arm thickness and the empty slot at the specified row
   * and column.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row of the starting empty slot
   * @param sCol         the column of the starting empty slot
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the empty
   *                                  slot position is invalid
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 1) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
    if (!isValidSlotArmThickness(sRow, sCol, armThickness)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.initializeBoard();
  }

  /**
   * Initializes each slot's state on the board.
   * <p>
   * Invalid slots are set to Invalid. Valid slots are set to Empty if they are the starting empty
   * slot, otherwise they are set to Marble.
   */
  private void initializeBoard() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (row == this.sRow && col == this.sCol) {
          this.board[row][col] = SlotState.Empty;
        } else if (this.isValidSlot(row, col)) {
          this.board[row][col] = SlotState.Marble;
        } else {
          this.board[row][col] = SlotState.Invalid;
        }
      }
    }
  }

  /**
   * Returns whether the given slot position is valid based on the given arm thickness.
   *
   * @param row          the row of the slot to validate
   * @param col          the column of the slot to validate
   * @param armThickness the board's arm thickness to validate against
   * @return true if the slot position is valid, false otherwise
   */
  private boolean isValidSlotArmThickness(int row, int col, int armThickness) {
    return ((!(armThickness - 1 > col) && !(armThickness * 2 - 2 < col)) || (
        !(armThickness - 1 > row) && !(armThickness * 2 - 2 < row)));
  }

  /**
   * Returns whether the given slot position is valid based on the model's arm thickness.
   *
   * @param row the row of the slot to validate
   * @param col the column of the slot to validate
   * @return true if the slot position is valid, false otherwise
   */
  private boolean isValidSlot(int row, int col) {
    return isValidSlotArmThickness(row, col, this.armThickness);
  }

  @Override
  public int getBoardSize() {
    return this.armThickness + (this.armThickness - 1) * 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (!this.isValidSlot(row, col)) {
      throw new IllegalArgumentException("Slot position is beyond board dimensions");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.isValidSlot(fromRow, fromCol)) {
      throw new IllegalArgumentException("From slot position is beyond board dimensions");
    }
    if (!this.isValidSlot(toRow, toCol)) {
      throw new IllegalArgumentException("To slot position is beyond board dimensions");
    }
    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("No marble at from slot position");
    }
    if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("To slot position is not empty: " + this.getSlotAt(toRow,
          toCol));
    }
    int rowDifference = Math.abs(fromRow - toRow);
    int columnDifference = Math.abs(fromCol - toCol);
    if (rowDifference != 2 && columnDifference != 2) {
      throw new IllegalArgumentException("A marble must move exactly two positions");
    }
    if (rowDifference == 2 && columnDifference == 2) {
      throw new IllegalArgumentException("A marble must move in only one direction");
    }
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;
    if (this.getSlotAt(midRow, midCol) != SlotState.Marble) {
      throw new IllegalArgumentException("No marble to jump over");
    }
    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[midRow][midCol] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
  }

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (this.isValidSlot(row - 2, col) && this.getSlotAt(row - 2, col) == SlotState.Empty
              && this.getSlotAt(row - 1, col) == SlotState.Marble) {
            return false;
          }
          if (this.isValidSlot(row + 2, col) && this.getSlotAt(row + 2, col) == SlotState.Empty
              && this.getSlotAt(row + 1, col) == SlotState.Marble) {
            return false;
          }
          if (this.isValidSlot(row, col - 2) && this.getSlotAt(row, col - 2) == SlotState.Empty
              && this.getSlotAt(row, col - 1) == SlotState.Marble) {
            return false;
          }
          if (this.isValidSlot(row, col + 2) && this.getSlotAt(row, col + 2) == SlotState.Empty
              && this.getSlotAt(row, col + 1) == SlotState.Marble) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
