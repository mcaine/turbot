package com.mikeycaine.turbot.patterns

case class Pattern(rows: String*) {

  val rowLength = rows(0).length
  def nRows = rows.length

  if (rows.isEmpty) throw new IllegalArgumentException("Pattern needs rows")
  assert(rows.size > 0)

  rows.foreach(row => {
    if (row.length != rowLength) throw new IllegalArgumentException("Rows should have same length")
    assert(row.length == rowLength)
  })

  def charAt(x: Int, y: Int): Char = {
    val rowIndex = y % nRows
    val colIndex = x % rowLength
    rows(rowIndex).charAt(colIndex)
  }

  def nextTo(another: Pattern): Pattern = {
    if (another.nRows != this.nRows)
      throw new IllegalArgumentException("Different numbers of rows")

    val newRows: Seq[String] = this.rows zip another.rows map {
      case (a,b) => a + b
    }
    Pattern(newRows: _*)
  }

  def above(another: Pattern): Pattern = {
    if (another.rowLength != this.rowLength)
      throw new IllegalArgumentException("Different row sizes")

    val newRows = this.rows ++ another.rows
    Pattern(newRows: _*)
  }

  def printPattern(xReps: Int, yReps: Int) = {
    for (ny <- 0 until yReps * nRows) {
      for (nx <- 0 until xReps * rowLength) {
        print(charAt(nx, ny))
      }
      println()
    }
  }
}

object Pattern {
//  def main(args: Array[String]): Unit = {
//
//    //val pattern = new Pattern()
//
//    val pattern1 = Pattern(
//      "*****^*****",
//      "****---****",
//      "**<----->**",
//      "****---****",
//      "*****V*****"
//    )
//
//    val pattern2 = Pattern(
//      "***********",
//      "***********",
//      "***********",
//      "***********",
//      "***********"
//    )
//
//    val pattern3 = pattern1 nextTo pattern2
//    val pattern4 = pattern2 nextTo pattern1
//    //pattern3.printPattern(1, 1)
//
//    val pattern5 = pattern3 above pattern4
//    pattern5.printPattern(2, 2)
//
//    val pattern6 = pattern4 above pattern3
//
//    val pattern7 = pattern5 nextTo pattern6
//
//    val pattern8 = pattern2 nextTo pattern2
//    val pattern9 = pattern8 nextTo pattern8
//
//    val pattern10 = pattern9 above pattern7
//
//    //pattern7.printPattern(1, 1)
//
//
//  }
}

