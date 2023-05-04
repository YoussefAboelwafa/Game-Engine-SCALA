import java.awt._
import javax.swing._
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.Try

object Main extends Exception {
  def main(args: Array[String]): Unit = {
    val Game = home()
    Game match {
      case "chess" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
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
        Array("", "", ""),
        Array("", "", ""),
        Array("", "", "")
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
      case "suduko" => val myboard = Array(
        Array("-", "-", "7", "4", "9", "1", "6", "-", "5"),
        Array("2", "-", "-", "-", "6", "-", "3", "-", "9"),
        Array("-", "-", "-", "-", "-", "7", "-", "1", "-"),
        Array("-", "5", "8", "6", "-", "-", "-", "-", "4"),
        Array("-", "-", "3", "-", "-", "-", "-", "9", "-"),
        Array("-", "-", "6", "2", "-", "-", "1", "8", "7"),
        Array("9", "-", "4", "-", "7", "-", "-", "-", "2"),
        Array("6", "7", "-", "8", "3", "-", "-", "-", "-"),
        Array("8", "1", "-", "-", "4", "5", "-", "-", "-"),
      )
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
      cell.setPreferredSize(new Dimension(200, 200))
      cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 120))
      cell.setHorizontalAlignment(SwingConstants.CENTER)
      cell.setForeground(Color.BLACK)
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
      cell.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK))
      cell.setOpaque(true)
      board(i)(j) match {
        case "R" => cell.setBackground(Color.RED)
        case "Y" => cell.setBackground(Color.YELLOW)
        case "W" => cell.setBackground(Color.WHITE)
      }
      cell.setPreferredSize(new Dimension(100, 100))
      cell.setFont(cell.getFont().deriveFont(64f))
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
      val cell = new JLabel()
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
      cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 70))
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
      (board, true, !player)
    }
  }

}

