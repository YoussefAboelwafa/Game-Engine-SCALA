object TicTacToe {
  val BoardSize = 3
  val Empty = " "
  val Player1 = "\u001b[34mX\u001b[0m"
  val Player2 = "\u001b[31mO\u001b[0m"
  type Board = Array[Array[String]]

  def drawer() = {
    println("Welcome to XO Game!")
    play(Array.fill(BoardSize, BoardSize)(Empty), Player1)
  }

  def play(board: Board, currentPlayer: String): Unit = {
    printBoard(board)
    val (row, col) = readMove(currentPlayer)
    updateBoard(board, row, col, currentPlayer) match {
      case Some(newBoard) =>
        if (isWin(newBoard, currentPlayer)) {
          println("\u001b[33mPlayer\u001b[0m " + currentPlayer + "\u001b[33m wins!\u001b[0m")
        } else if (isBoardFull(newBoard)) {
          println("\u001b[33mGame over! It's a tie!\u001b[0m")
        } else {
          play(newBoard, switchPlayer(currentPlayer))
        }
      case None =>
        println("\u001b[33mINVALID MOVE, Please try again\u001b[0m")
        play(board, currentPlayer)
    }
  }

  def readMove(currentPlayer: String): (Int, Int) = {
    val row = readInt("Enter row (1-3) for " + currentPlayer + ": ") - 1
    val col = readInt("Enter col (1-3) for " + currentPlayer + ": ") - 1
    (row, col)
  }

  def updateBoard(board: Board, row: Int, col: Int, player: String): Option[Board] = {
    if (isValidMove(board, row, col)) {
      val newBoard = board.clone()
      newBoard(row)(col) = player
      Some(newBoard)
    } else {
      None
    }
  }

  def printBoard(board: Board): Unit = {
    println("\u001b[33m-------------\u001b[0m")
    for (row <- board) {
      for (cell <- row) {
        print("\u001b[33m|\u001b[0m " + cell + " ")
      }
      println("\u001b[33m|\u001b[0m")
      println("\u001b[33m-------------\u001b[0m")
    }
  }

  def isValidMove(board: Board, row: Int, col: Int): Boolean = {
    row >= 0 && row < BoardSize && col >= 0 && col < BoardSize && board(row)(col) == Empty
  }

  def isWin(board: Board, player: String): Boolean = {
    val rows = board.map(row => row.count(_ == player))
    val cols = (0 until BoardSize).map(i => board.count(_(i) == player))
    val diag1 = (0 until BoardSize).map(i => board(i)(i)).count(_ == player)
    val diag2 = (0 until BoardSize).map(i => board(i)(BoardSize - i - 1)).count(_ == player)
    rows.contains(BoardSize) || cols.contains(BoardSize) || diag1 == BoardSize || diag2 == BoardSize
  }

  def isBoardFull(board: Board): Boolean = {
    !board.flatten.contains(Empty)
  }

  def switchPlayer(player: String): String = {
    if (player == Player1) Player2 else Player1
  }

  def readInt(message: String): Int = {
    print(message)
    scala.io.StdIn.readInt()
  }

  def main(args: Array[String]): Unit = {
    TicTacToe.drawer()
  }
}
