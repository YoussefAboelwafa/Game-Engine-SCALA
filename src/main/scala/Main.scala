import java.awt._
import javax.swing._
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.Try
import scala.util.Random
object Main extends Exception {
  def main(args: Array[String]): Unit = {
    val Game = home()
    Game match {
      case "chess" => play(chess_Drawer, chess_Controller, initialize_board(Game))
      case "checkers" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "tic_tac_toe" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "connect4" => play(connect4_Drawer, connect4_Controller, initialize_board(Game))
      case "suduko" => play(suduko_Drawer, suduko_Controller, initialize_board(Game))
      case "8queens" => play(eightqueens_Drawer, eightqueens_Controller, initialize_board(Game))
    }
  }

  def home(): String = {
    println("\u001b[33mChoose a game to play:\u001b[0m\n1.Chess\n2.Checkers\n" +
      "3.Tic Tac Toe\n4.Connect4\n5.Suduko\n6.Eight Queens\n\u001b[33mEnter your choice:\u001b[0m")
    val input = Try(StdIn.readLine().toInt).toOption
    var result: String = ""
    input match {
      case Some(1) => result = "chess"
      case Some(2) => result = "checkers"
      case Some(3) => result = "tic_tac_toe"
      case Some(4) => result = "connect4"
      case Some(5) => result = "suduko"
      case Some(6) => result = "8queens"
      case _ => {
        println("\u001b[31mINVALID INPUT\u001b[0m")
        home()
      }
    }
    result
  }

  def initialize_board(Game: String): Array[Array[String]] = {

    Game match {
      case "chess" => val myboard = Array(
        Array("T1", "H1", "F1", "Q1", "K1", "F1", "H1", "T1"),
          Array("S1", "S1", "S1", "S1", "S1", "S1", "S1", "S1"),
          Array("", "", "", "", "", "", "", ""),
          Array("", "", "", "", "", "", "", ""),
          Array("", "", "", "", "", "", "", ""),
          Array("", "", "", "", "", "", "", ""),
          Array("S2", "S2", "S2", "S2", "S2", "S2", "S2", "S2"),
          Array("T2", "H2", "F2", "Q2", "K2", "F2", "H2", "T2")
      )
        myboard
      case "checkers" => val myboard = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array(" ", "", "")
      )
        myboard
      case "tic_tac_toe" => val myboard = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array("", "", "")
      )
        myboard
      case "connect4" => val myboard = Array(
        Array("W", "W", "W", "W", "W", "W", "W"),
        Array("W", "W", "W", "W", "W", "W", "W"),
        Array("W", "W", "W", "W", "W", "W", "W"),
        Array("W", "W", "W", "W", "W", "W", "W"),
        Array("W", "W", "W", "W", "W", "W", "W"),
        Array("W", "W", "W", "W", "W", "W", "W")
      )
        myboard
      case "suduko" => val myboard = generate()
        myboard
      case "8queens" => val myboard = Array(
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0"),
        Array("0", "0", "0", "0", "0", "0", "0", "0")
      )
        myboard
    }

  }

  def play(drawer: Array[Array[String]] => Unit, controller: (String, Array[Array[String]], Boolean) => (Array[Array[String]], Boolean, Boolean), board: Array[Array[String]]): Unit = {
    val turn: Boolean = true
    val result: Boolean = true
    var tuple = (board, result, turn)
    drawer(tuple._1)
    while (true) {
      val input = StdIn.readLine("\u001b[33mEnter your Input: \u001b[0m")
      tuple = controller(input, tuple._1, tuple._3)
      if (tuple._2) {
        drawer(tuple._1)
      }
      else
        println("\u001b[31mINVALID INPUT\u001b[0m")
    }
  }

  def suduko_Drawer(board: Array[Array[String]]): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val frame = new JFrame("Suduko")
    val panel = new JPanel(new GridLayout(9, 9))
    for (i <- 0 until 9; j <- 0 until 9) {
      if (board(i)(j) == "-" || board(i)(j) == "0") {
        val cell = new JLabel(s"${i},${j}")
        cell.setPreferredSize(new Dimension(70, 70))

        cell.setFont(new Font(cell.getFont().getName(), Font.ITALIC, 18))
        cell.setForeground(Color.GRAY)
        cell.setBackground(Color.YELLOW)
        cell.setOpaque(true)
        cell.setHorizontalAlignment(SwingConstants.CENTER)
        cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK))
        if (i % 3 == 0 && j % 3 == 0) {
          cell.setBorder(BorderFactory.createMatteBorder(10, 10, 1, 1, Color.BLACK))
        }
        else if (i % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(10, 1, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else if (j % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 10, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else {
          cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK))
        }
        panel.add(cell)
      }
      else {
        val cell = new JLabel(board(i)(j))
        cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 30))
        cell.setForeground(Color.BLACK)
        cell.setBackground(Color.YELLOW)
        cell.setHorizontalAlignment(SwingConstants.CENTER)
        if (i % 3 == 0 && j % 3 == 0) {
          cell.setBorder(BorderFactory.createMatteBorder(10, 10, 1, 1, Color.BLACK))
        }
        else if (i % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(10, 1, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else if (j % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 10, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else {
          cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK))
        }
        panel.add(cell)
      }
    }
    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def suduko_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(" ")
    val (row, col, value) = inputArray.map(_.toInt) match {
      case Array(row, col, value) if row >= 0 && row <= 8 && col >= 0 && col <= 8 && value >= 0 && value <= 9
      => (row, col, value)
      case _ => return (board, false, false)
    }
    for (i <- 0 until (9)) {
      if (value.toString == board(row)(i) || value.toString == board(i)(col)) {
        return (board, false, false)
      }
    }
    val boxRow = row / 3
    val boxCol = col / 3
    for (i <- boxRow * 3 until boxRow * 3 + 3; j <- boxCol * 3 until boxCol * 3 + 3) {
      if (value.toString == board(i)(j)) {
        return (board, false, false)
      }
    }
    board(row)(col) = if (value == 0) "-" else value.toString
    (board, true, false)
  }

  def tic_tac_toe_Drawer(board: Array[Array[String]]): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val frame = new JFrame("Tic Tac Toe")
    frame.setLayout(new BorderLayout())

    val panel = new JPanel(new GridLayout(3, 3))
    for (i <- 0 until 3; j <- 0 until 3) {
      val cell = new JLabel(board(i)(j))
      if(cell.getText=="") cell.setText(s"${i},${j}")
      cell.setPreferredSize(new Dimension(200, 200))
      if(board(i)(j)!="") {
        cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 120))
      }
      else {
        cell.setFont(new Font(cell.getFont().getName(), Font.ITALIC, 20))
      }
      cell.setHorizontalAlignment(SwingConstants.CENTER)
      if(board(i)(j)!="")
      cell.setForeground(Color.BLACK)
      else
        cell.setForeground(Color.GRAY)
      cell.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK),
        cell.getBorder
      ))
      panel.add(cell)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def tic_tac_toe_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(" ")
    val new_player = player
    val newBoard = board.clone()
    val (row, col) = (inputArray(0).toInt, inputArray(1).toInt)
    if (row < 0 || row > 2 || col < 0 || col > 2 || newBoard(row)(col) != "") (newBoard, false, new_player)
    else {
      newBoard(row)(col) = if (player) "x" else "o"
      (newBoard, true, !new_player)
    }

  }

  def connect4_Drawer(board: Array[Array[String]]): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val frame = new JFrame("connect4")
    frame.setLayout(new BorderLayout())
    val panel = new JPanel(new GridLayout(6, 7))
    for (i <- 0 until 6; j <- 0 until 7) {
      val cell = new JLabel()
      if(i==0) cell.setText(s"${j}")
      cell.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK))
      cell.setOpaque(true)
      board(i)(j) match {
        case "R" => cell.setBackground(Color.RED)
        case "Y" => cell.setBackground(Color.YELLOW)
        case "W" => cell.setBackground(Color.WHITE)
      }
      cell.setPreferredSize(new Dimension(100, 100))
cell.setHorizontalAlignment(SwingConstants.CENTER)
      cell.setForeground(Color.GRAY)
      cell.setFont(new Font(cell.getFont().getName(), Font.ITALIC, 30))
//      cell.setFont(cell.getFont().deriveFont(64f))
      panel.add(cell)
    }
    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def connect4_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {

    val new_player = player
    if (input.toInt > 6 || input.toInt < 0) (board, false, new_player)
    else {
      (5 until -1 by -1).find { i =>
        board(i)(input.toInt) == "W"
      }.map { i =>
        val newBoard = board.clone()
        newBoard(i)(input.toInt) = if (new_player) "R" else "Y"
      }
      (board, true, !new_player)
    }

  }

  def eightqueens_Drawer(board: Array[Array[String]]): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val frame = new JFrame("8queens")
    frame.setLayout(new BorderLayout())
    val panel = new JPanel(new GridLayout(8, 8))
    for (i <- 0 until 8; j <- 0 until 8) {
      val cell=new JLabel(s"${i},${j}")
      cell.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK))
      cell.setHorizontalAlignment(SwingConstants.CENTER)
      cell.setOpaque(true)
      cell.setForeground(Color.BLACK)

      if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
        cell.setBackground(Color.WHITE)
        cell.setOpaque(true)
      }
      else if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
        cell.setBackground(Color.GRAY)
        cell.setOpaque(true)

      }
      board(i)(j) match {
        case "1" => cell.setText("♛")
        case "0" =>
      }
      cell.setPreferredSize(new Dimension(100, 100))
      if(board(i)(j)=="1")
      cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 70))
      else
        {
          cell.setFont(new Font(cell.getFont().getName(), Font.ITALIC, 18))

        }
      panel.add(cell)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def eightqueens_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(" ")
    val row = inputArray(0).toInt
    val col = inputArray(1).toInt
    val remove=if(inputArray.length==3)inputArray(2).toInt else -1
    if(remove!=0 && remove!=(-1)) return (board,false,player)
     if(row<0 || row>7 || col <0 || col>7 ) (board, false, player)
    else {
  if(remove==0)
    { if(board(row)(col)=="1") {
      board(row)(col) = "0"
      return (board, true, player)
    }
      else {
      return (board, false, player)
    }

    }
  // Check for row
  val row_bool = board(row).contains("1")
  val col_bool = board.exists(_(col) == "1")

  val diagonal_bool = (1 to 7).flatMap { i =>
    Seq((row + i, col + i), (row + i, col - i), (row - i, col + i), (row - i, col - i))
  }.exists {
    case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && board(r)(c) == "1"
  }

  if (row_bool || col_bool || diagonal_bool) {
    (board, false, player)
  } else {
    board(row)(col) = "1"
    (board, true, player)
  }
}
  }

  def generate(): Array[Array[String]] = {
    val board = Array.fill(9)(Array.fill(9)("-"))
    for (i <- 0 until 9; j <- 0 until 9) {
      if (Random.nextDouble() < 0.5) {
        val randomNumber = Random.nextInt(9) + 1
        if (validate_input(board, i, j, randomNumber)) {
          board(i)(j) = randomNumber.toString
        }
      }
    }
    board
  }

  def validate_input(grid: Array[Array[String]], row: Int, col: Int, value: Int): Boolean = {
    val boxRow = (row / 3) * 3
    val boxCol = (col / 3) * 3

    def checkRow(j: Int): Boolean = grid(row)(j) == value.toString

    def checkColumn(i: Int): Boolean = grid(i)(col) == value.toString

    def checkBox(i: Int, j: Int): Boolean = grid(i)(j) == value.toString

    !((0 until 9).exists(checkRow) ||
      (0 until 9).exists(checkColumn) ||
      (boxRow until boxRow + 3).exists(i => (boxCol until boxCol + 3).exists(j => checkBox(i, j))))
  }
  def chess_Drawer(board: Array[Array[String]]): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val frame = new JFrame("Chess")
    frame.setLayout(new BorderLayout())
    val panel = new JPanel(new GridLayout(8, 8))
    for (i <- 0 until 8; j <- 0 until 8) {
      val cell = new JLabel(s"${i},${j}")
      cell.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK))
      cell.setHorizontalAlignment(SwingConstants.CENTER)
      cell.setOpaque(true)
      cell.setForeground(Color.BLACK)

      if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
        cell.setBackground(Color.WHITE)
        cell.setOpaque(true)
      }
      else if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
        cell.setBackground(Color.lightGray)
        cell.setOpaque(true)

      }
      val blackKing = '\u265A'
      val blackQueen = '\u265B'
      val blackRook = '\u265C'
      val blackBishop = '\u265D'
      val blackKnight = '\u265E'
      val blackPawn = '\u265F'

      val whiteKing = '\u2654'
      val whiteQueen = '\u2655'
      val whiteRook = '\u2656'
      val whiteBishop = '\u2657'
      val whiteKnight = '\u2658'
      val whitePawn = '\u2659'

      board(i)(j) match {
        case "S1" => cell.setText(blackPawn.toString)
        case "S2" =>cell.setText(whitePawn.toString)
        case "K1" =>cell.setText(blackKing.toString)
        case "K2" =>cell.setText(whiteKing.toString)
        case "Q1" =>cell.setText(blackQueen.toString)
        case "Q2" =>cell.setText(whiteQueen.toString)
        case "T1" =>cell.setText(blackRook.toString)
        case "T2" =>cell.setText(whiteRook.toString)
        case "H1" =>cell.setText(blackKnight.toString)
        case "H2" =>cell.setText(whiteKnight.toString)
        case "F1" =>cell.setText(blackBishop.toString)
        case "F2" =>cell.setText(whiteBishop.toString)
        case "" =>

      }
      cell.setPreferredSize(new Dimension(100, 100))
      if(board(i)(j)=="")cell.setFont(new Font(cell.getFont().getName(), Font.ITALIC, 18))
      else
        cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 70))

      panel.add(cell)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }
  def chess_Controller(input: String, board: Array[Array[String]], player: Boolean):(Array[Array[String]], Boolean, Boolean)={
    val inputArray = input.split(" ")
    val src_row=inputArray(0).toInt
    val src_col=inputArray(1).toInt
    val dest_row=inputArray(2).toInt
    val dest_col=inputArray(3).toInt
    if(src_row<0 || src_row>7 || src_col<0 || src_col>7 || dest_col<0 || dest_col>7 || dest_row<0 || dest_row>7 || board(src_row)(src_col)=="") return (board,false,player)
    if(board(src_row)(src_col).charAt(1)=='1' && player==true)return (board,false,player)
    if(board(src_row)(src_col).charAt(1)=='2' && player==false)return (board,false,player)
    if(board(src_row)(src_col)=="S2")
      {
        if ((src_row == 6) && (dest_row == 5) && (src_col == dest_col) && (board(dest_row)(dest_col) == "")) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "S2"
          return (board, true, !player)
        }
        else if ((src_row == 6) && (dest_row == 4) && (src_col == dest_col) && (board(dest_row)(dest_col) == "") && (board(dest_row + 1)(dest_col) == "")) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "S2"
          return (board, true, !player)
        }
        else if ((src_col == dest_col) && ((src_row - dest_row) == 1) && (board(dest_row)(dest_col) == "")) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "S2"
          return (board,true,!player)
        }
        else if (((src_row - dest_row) == 1) && ((dest_col == (src_col - 1)) || (dest_col == (src_col + 1))) && (board(dest_row)(dest_col) != "") && (board(dest_row)(dest_col).charAt(1) == '1')) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "S2"
          return (board, true, !player)
        }
        else {
          return (board,false,player)
        }
      }
    else if (board(src_row)(src_col) == "S1") {
      if ((src_row == 1) && (dest_row == 2) && (src_col == dest_col) && (board(dest_row)(dest_col) == "")) {
        board(src_row)(src_col) = ""
        board(dest_row)(dest_col) = "S1"
        return (board, true, !player)
      }
      else if ((src_row == 1) && (dest_row == 3) && (src_col == dest_col) && (board(dest_row)(dest_col) == "") && (board(src_row + 1)(dest_col) == "")) {
        board(src_row)(src_col) = ""
        board(dest_row)(dest_col) = "S1"
        return (board, true, !player)
      }
      else if ((src_col == dest_col) && ((dest_row - src_row) == 1) && (board(dest_row)(dest_col) == "")) {
        board(src_row)(src_col) = ""
        board(dest_row)(dest_col) = "S1"
        return (board, true, !player)
      }
      else if (((dest_row - src_row) == 1) && ((dest_col == (src_col - 1)) || (dest_col == (src_col + 1))) && (board(dest_row)(dest_col) != "") && (board(dest_row)(dest_col).charAt(1) == '2')) {
        board(src_row)(src_col) = ""
        board(dest_row)(dest_col) = "S1"
        return (board, true, !player)
      }
      else {
        return (board, false, player)
      }
    }
    else if(board(src_row)(src_col)=="H2")
      {
        if ((dest_row == src_row - 1) && ((dest_col == src_col - 2) || (dest_col == src_col + 2)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H2"
          return (board,true,!player)
        }
        else if ((dest_row == src_row + 1) && ((dest_col == src_col - 2) || (dest_col == src_col + 2)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H2"
          return (board, true, !player)
        }
        else if ((dest_row == src_row + 2) && ((dest_col == src_col - 1) || (dest_col == src_col + 1)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H2"
          return (board, true, !player)
        }
        else if ((dest_row == src_row - 2) && ((dest_col == src_col - 1) || (dest_col == src_col + 1)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H2"
          return (board, true, !player)
        }
        else {
          return (board,false,player)
        }
      }
      else if(board(src_row)(src_col)=="H1")
      {
        if ((dest_row == src_row - 1) && ((dest_col == src_col - 2) || (dest_col == src_col + 2)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H1"
          return (board, true, !player)
        }
        else if ((dest_row == src_row + 1) && ((dest_col == src_col - 2) || (dest_col == src_col + 2)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H1"
          return (board, true, !player)
        }
        else if ((dest_row == src_row + 2) && ((dest_col == src_col - 1) || (dest_col == src_col + 1)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H1"
          return (board, true, !player)
        }
        else if ((dest_row == src_row - 2) && ((dest_col == src_col - 1) || (dest_col == src_col + 1)) && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "H1"
          return (board, true, !player)
        }
        else {
          return (board, false, player)
        }
      }
      else if(board(src_row)(src_col)=="F2")
      {
        if ((src_col - dest_col == src_row - dest_row) && src_row - dest_row > 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          var i = 1
          while (i < src_row - dest_row) {
            if (board(src_row - i)(src_col - i) != "") {
             return (board,false,player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F2"
          return (board,true,!player)
        }
        else if ((src_col - dest_col == src_row - dest_row) && src_row - dest_row < 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          var i = 1
          while (i < dest_row - src_row) {
            if (board(src_row + i)(src_col + i) != "") {
              return (board,false,player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F2"
         return (board,true,!player)
        }
        else if ((src_col - dest_col == dest_row - src_row) && dest_row - src_row > 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          var i = 1
          while (i < dest_row - src_row) {
            if (board(src_row + i)(src_col - i) != "") {
              return (board,false,player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F2"
          return (board,true,!player)
        }
        else if ((src_col - dest_col == dest_row - src_row) && dest_row - src_row < 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1'))) {
          var i = 1
          while (i < src_row - dest_row) {
            if (board(src_row - i)(src_col + i) != "") {
             return (board,false,player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F2"
         return (board,true,!player)
        }
        else {
         return (board,false,player)
        }
      }
      else if(board(src_row)(src_col)=="F1")
      {
        if ((src_col - dest_col == src_row - dest_row) && src_row - dest_row > 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          var i = 1
          while (i < src_row - dest_row) {
            if (board(src_row - i)(src_col - i) != "") {
              return (board, false, player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F1"
          return (board, true, !player)
        }
        else if ((src_col - dest_col == src_row - dest_row) && src_row - dest_row < 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          var i = 1
          while (i < dest_row - src_row) {
            if (board(src_row + i)(src_col + i) != "") {
              return (board, false, player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F1"
          return (board, true, !player)
        }
        else if ((src_col - dest_col == dest_row - src_row) && dest_row - src_row > 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          var i = 1
          while (i < dest_row - src_row) {
            if (board(src_row + i)(src_col - i) != "") {
              return (board, false, player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F1"
          return (board, true, !player)
        }
        else if ((src_col - dest_col == dest_row - src_row) && dest_row - src_row < 0 && ((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2'))) {
          var i = 1
          while (i < src_row - dest_row) {
            if (board(src_row - i)(src_col + i) != "") {
              return (board, false, player)
            }

            i += 1
          }
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "F1"
          return (board, true, !player)
        }
        else {
          return (board, false, player)
        }
      }
    else if(board(src_row)(src_col)=="T2")
      {
        val rook_bool = (1 to 7).flatMap { i =>
          Seq((src_row + i, src_col), (src_row , src_col + i), (src_row - i, src_col ), (src_row , src_col - i))
        }.exists {
          case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row==r && dest_col==c)

        }

        if(!rook_bool) return (board,false,player)
        else {

          val right = dest_col > src_col
          val left = dest_col < src_col
          val down = dest_row > src_row
          val up = dest_row < src_row
          if (right) {
            for (col <- src_col + 1 until dest_col) {
              if (board(src_row)(col) != "") {
                return (board,false,player)
              }
            }
          } else if (left) {
            for (col <- src_col - 1 until dest_col by -1) {
              if (board(src_row)(col) != "") {
                return (board,false,player)
              }
            }
          } else if (down) {
            for (row <- src_row + 1 until dest_row) {
              if (board(row)(src_col) != "") {
                return (board,false,player)
              }
            }
          } else if (up) {

            for (row <- src_row - 1 until dest_row by -1) {
              if (board(row)(src_col) != "") {

                return (board,false,player)
              }
            }
          }


            if(  board(dest_row)(dest_col)=="" || board(dest_row)(dest_col).charAt(1)=='1') {
              board(src_row)(src_col)=""
              board(dest_row)(dest_col) = "T2"
              return (board,true,!player)
            }
            else return (board,false,player)


        }
      }
    else if (board(src_row)(src_col)=="T1")
      {
        val rook_bool = (1 to 7).flatMap { i =>
          Seq((src_row + i, src_col), (src_row, src_col + i), (src_row - i, src_col), (src_row, src_col - i))
        }.exists {
          case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row == r && dest_col == c)

        }

        if (!rook_bool) return (board, false, player)
        else {

          val right = dest_col > src_col
          val left = dest_col < src_col
          val down = dest_row > src_row
          val up = dest_row < src_row
          if (right) {
            for (col <- src_col + 1 until dest_col) {
              if (board(src_row)(col) != "") {
                return (board, false, player)
              }
            }
          } else if (left) {
            for (col <- src_col - 1 until dest_col by -1) {
              if (board(src_row)(col) != "") {
                return (board, false, player)
              }
            }
          } else if (down) {
            for (row <- src_row + 1 until dest_row) {
              if (board(row)(src_col) != "") {
                return (board, false, player)
              }
            }
          } else if (up) {

            for (row <- src_row - 1 until dest_row by -1) {
              if (board(row)(src_col) != "") {

                return (board, false, player)
              }
            }
          }


          if (board(dest_row)(dest_col) == "" || board(dest_row)(dest_col).charAt(1) == '2') {
            board(src_row)(src_col) = ""
            board(dest_row)(dest_col) = "T1"
            return (board, true, !player)
          }
          else return (board, false, player)


        }
      }
      else if(board(src_row)(src_col)=="K2")
      {
        if (((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '1')) && (((src_row == dest_row) && (Math.abs(src_col - dest_col) == 1)) || ((src_col == dest_col) && (Math.abs(src_row - dest_row) == 1)) || ((Math.abs(src_col - dest_col) == 1) && (Math.abs(src_row - dest_row) == 1)))) {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "K2"
         return (board,true,!player)
        }
        else {
          return (board,false,player)
        }
      }
    else if (board(src_row)(src_col) == "K1") {
      if (((board(dest_row)(dest_col) == "") || (board(dest_row)(dest_col).charAt(1) == '2')) && (((src_row == dest_row) && (Math.abs(src_col - dest_col) == 1)) || ((src_col == dest_col) && (Math.abs(src_row - dest_row) == 1)) || ((Math.abs(src_col - dest_col) == 1) && (Math.abs(src_row - dest_row) == 1)))) {
        board(src_row)(src_col) = ""
        board(dest_row)(dest_col) = "K1"
        return (board, true, !player)
      }
      else {
        return (board, false, player)
      }
    }
    else if(board(src_row)(src_col)=="Q2")
      {
        val row_col_bool = (1 to 7).flatMap { i =>
          Seq((src_row + i, src_col), (src_row, src_col + i), (src_row - i, src_col), (src_row, src_col - i))
        }.exists {
          case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row == r && dest_col == c)

        }
        val diagonal_bool = (1 to 7).flatMap { i =>
          Seq((src_row + i, src_col + i), (src_row + i, src_col - i), (src_row - i, src_col + i), (src_row - i, src_col - i))
        }.exists {
          case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row == r && dest_col == c)
        }
        if(!(row_col_bool|| diagonal_bool)) return (board,false,player)
        else
          {
            if (src_row == dest_row) {
              // check row
              val min_col = Math.min(src_col, dest_col)
              val max_col = Math.max(src_col, dest_col)
             val bool= (min_col + 1 until max_col).exists(c => board(src_row)(c) != "")
              if(bool) return (board,false,player)
            } else if (src_col == dest_col) {
              // check column
              val min_row = Math.min(src_row, dest_row)
              val max_row = Math.max(src_row, dest_row)
             val bool= (min_row + 1 until max_row).exists(r => board(r)(src_col) != "")
              if(bool) return (board,false,player)
            } else if (Math.abs(src_row - dest_row) == Math.abs(src_col - dest_col)) {
              println("d5l")
              // check diagonal
              val (start_row, end_row, start_col, end_col) = if (src_row < dest_row && src_col < dest_col) {
                (src_row + 1, dest_row, src_col + 1, dest_col)
              } else if (src_row < dest_row && src_col > dest_col) {
                (src_row + 1, dest_row, dest_col, src_col - 1)
              } else if (src_row > dest_row && src_col < dest_col) {
                (dest_row + 1, src_row, src_col + 1, dest_col)
              } else {
                (dest_row + 1, src_row, dest_col, src_col - 1)
              }
             val bool= (start_row until end_row).zip(start_col until end_col).exists {
                case (r, c) => board(r)(c) != ""
              }
              println(bool)
              if(bool) return (board,false,player)
            }
            if(board(dest_row)(dest_col)=="" || board(dest_row)(dest_col).charAt(1)=='1') {
              board(src_row)(src_col) = ""
              board(dest_row)(dest_col) = "Q2"
              return (board, true, !player)
            }
            else {
              return (board,false,player)
            }
          }
      }
    else if (board(src_row)(src_col) == "Q1") {
      val row_col_bool = (1 to 7).flatMap { i =>
        Seq((src_row + i, src_col), (src_row, src_col + i), (src_row - i, src_col), (src_row, src_col - i))
      }.exists {
        case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row == r && dest_col == c)

      }
      val diagonal_bool = (1 to 7).flatMap { i =>
        Seq((src_row + i, src_col + i), (src_row + i, src_col - i), (src_row - i, src_col + i), (src_row - i, src_col - i))
      }.exists {
        case (r, c) => r >= 0 && r < 8 && c >= 0 && c < 8 && (dest_row == r && dest_col == c)
      }
      if (!(row_col_bool || diagonal_bool)) return (board, false, player)
      else {
        if (src_row == dest_row) {
          // check row
          val min_col = Math.min(src_col, dest_col)
          val max_col = Math.max(src_col, dest_col)
          val bool = (min_col + 1 until max_col).exists(c => board(src_row)(c) != "")
          if (bool) return (board, false, player)
        } else if (src_col == dest_col) {
          // check column
          val min_row = Math.min(src_row, dest_row)
          val max_row = Math.max(src_row, dest_row)
          val bool = (min_row + 1 until max_row).exists(r => board(r)(src_col) != "")
          if (bool) return (board, false, player)
        } else if (Math.abs(src_row - dest_row) == Math.abs(src_col - dest_col)) {
          println("d5l")
          // check diagonal
          val (start_row, end_row, start_col, end_col) = if (src_row < dest_row && src_col < dest_col) {
            (src_row + 1, dest_row, src_col + 1, dest_col)
          } else if (src_row < dest_row && src_col > dest_col) {
            (src_row + 1, dest_row, dest_col, src_col - 1)
          } else if (src_row > dest_row && src_col < dest_col) {
            (dest_row + 1, src_row, src_col + 1, dest_col)
          } else {
            (dest_row + 1, src_row, dest_col, src_col - 1)
          }
          val bool = (start_row until end_row).zip(start_col until end_col).exists {
            case (r, c) => board(r)(c) != ""
          }
          println(bool)
          if (bool) return (board, false, player)
        }
        if (board(dest_row)(dest_col) == "" || board(dest_row)(dest_col).charAt(1) == '2') {
          board(src_row)(src_col) = ""
          board(dest_row)(dest_col) = "Q1"
          return (board, true, !player)
        }
        else {
          return (board, false, player)
        }
      }
    }

    (board, true, !player)

  }




}

