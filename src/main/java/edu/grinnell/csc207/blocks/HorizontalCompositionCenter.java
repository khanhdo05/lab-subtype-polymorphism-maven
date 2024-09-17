package edu.grinnell.csc207.blocks;

/**
 * The center-aligned horizontal composition of two blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HorizontalCompositionCenter implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The left block.
   */
  AsciiBlock left;

  /**
   * The right block.
   */
  AsciiBlock right;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a Horizontal Composition.
   *
   * @param leftBlock
   *   The block on the left.
   *
   * @param rightBlock
   *   The block on the right.
   */
  public HorizontalCompositionCenter(AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.left = leftBlock;
    this.right = rightBlock;
  } // HorizontalCompositionCenter

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    int offset = (this.left.height() - this.right.height()) / 2;
    int offsetLeft = Math.max(0, - offset);
    int offsetRight = Math.max(0, offset);

    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } else if ((i - offsetLeft >= 0) && (i - offsetRight >= 0) && (i - offsetLeft < this.left.height()) && (i - offsetRight < this.right.height())) {
      // Inside both left and right bounds
      return this.left.row(i - offsetLeft) + this.right.row(i - offsetRight);
    } else if ((i - offsetRight >= 0) && (i - offsetRight < this.right.height())) {
      // Inside right bounds, outside left bounds
      return " ".repeat(this.left.width()) + this.right.row(i - offsetRight);
    } else {
      // Inside left bounds, outside right bounds
      return this.left.row(i - offsetLeft) + " ".repeat(this.right.width());
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return Math.max(this.left.height(), this.right.height());
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.left.width() + this.right.width();
  } // width()

} // class HorizontalCompositionCenter
