import java.awt._
import javax.swing._
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.control.Breaks._

object Main extends Exception {
  def main(args: Array[String]): Unit = {
    //decide which game
    //val Game = home()
    //    Game match {
    //      case "chess" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
    //      case "checkers" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
    //      case "tic_tac_toe" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
    //      case "connect4" => play(connect4_Drawer, connect4_Controller, initialize_board(Game))
    //      case "suduko" => play(suduko_Drawer, suduko_Controller, initialize_board(Game))
    //      case "8queens" => play(eightqueens_Drawer, eightqueens_Controller, initialize_board(Game))
    //    }
    play(suduko_Drawer, suduko_Controller, initialize_board("suduko"))

  }

  def initialize_board(Game: String): Array[Array[String]] = {

    Game match {
      case "chess" => val myboard = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array(" ", "", "")
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
    while (true) {
      val input = StdIn.readLine("Enter input: ")
      tuple = controller(input, tuple._1, tuple._3)
      if (tuple._2) {
        drawer(tuple._1)
      }
      else
        println("\u001b[31mInvalid Input\u001b[0m")

    }
  }

  def suduko_Drawer(board: Array[Array[String]]): Unit = {
    val frame = new JFrame("Suduko")
    val panel = new JPanel(new GridLayout(9, 9))
    for (i <- 0 until 9; j <- 0 until 9) {
      if (board(i)(j) == "-") {
        val cell = new JLabel(s"${i},${j} ")
        cell.setPreferredSize(new Dimension(70, 70))

        cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 18))
        cell.setForeground(Color.GRAY)
        cell.setBackground(Color.YELLOW)
        cell.setOpaque(true)
        cell.setHorizontalAlignment(SwingConstants.CENTER)
        cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK))
        if (i % 3 == 0 && j % 3 == 0) {
          cell.setBorder(BorderFactory.createMatteBorder(8, 8, 1, 1, Color.BLACK))
        }
        else if (i % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(8, 1, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else if (j % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 8, 1, 1, Color.BLACK),
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
        cell.setFont(new Font(cell.getFont().getName(), Font.BOLD, 24))
        cell.setForeground(Color.BLACK)
        cell.setBackground(Color.YELLOW)
        cell.setHorizontalAlignment(SwingConstants.CENTER)
        if (i % 3 == 0 && j % 3 == 0) {
          cell.setBorder(BorderFactory.createMatteBorder(8, 8, 1, 1, Color.BLACK))
        }
        else if (i % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(8, 1, 1, 1, Color.BLACK),
            cell.getBorder
          ))
        }
        else if (j % 3 == 0) {
          cell.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 8, 1, 1, Color.BLACK),
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
    val row = inputArray(0).toInt
    val col = inputArray(1).toInt
    val value = inputArray(2).toInt
    var result = true
    var new_player = player
    if (row < 0 || row > 8 || col < 0 || col > 8 || board(row)(col) != "-" || value < 0 || value > 9)
      result = false
    else {

    }
    (board, result, true)
  }

  def tic_tac_toe_Drawer(board: Array[Array[String]]): Unit = {
    val frame = new JFrame("Tic Tac Toe")
    frame.setLayout(new BorderLayout())

    val panel = new JPanel(new GridLayout(3, 3))

    for (i <- 0 until 3; j <- 0 until 3) {
      val button = new JButton(board(i)(j))
      button.setPreferredSize(new Dimension(100, 100))
      button.setFont(button.getFont().deriveFont(64f))
      button.setEnabled(false)
      panel.add(button)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def tic_tac_toe_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(" ")
    val row = inputArray(0).toInt
    val col = inputArray(1).toInt
    var result = true
    var new_player = player
    if (row < 0 || row > 2 || col < 0 || col > 2 || board(row)(col) != "")
      result = false
    else {
      board(row)(col) = if (player) "x" else "o"
      new_player = !new_player
    }
    (board, result, new_player)
  }

  def connect4_Drawer(board: Array[Array[String]]): Unit = {
    val frame = new JFrame("connect4")
    frame.setLayout(new BorderLayout())
    val panel = new JPanel(new GridLayout(6, 7))
    for (i <- 0 until 6; j <- 0 until 7) {
      val button = new JButton()
      button.setOpaque(true)
      board(i)(j) match {
        case "0" => button.setBackground(Color.RED)
        case "Y" => button.setBackground(Color.YELLOW)
        case "W" => button.setBackground(Color.WHITE)
      }
      button.setPreferredSize(new Dimension(100, 100))
      button.setFont(button.getFont().deriveFont(64f))
      button.setEnabled(false)
      panel.add(button)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def connect4_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    var result = true
    var new_player = player
    if (input.toInt > 6 || input.toInt < 0) result = false
    else {
      breakable {
        for (i <- 5 until 0 by -1) {
          if (board(i)(input.toInt) == "W") {
            if (new_player) board(i)(input.toInt) = "R"
            else board(i)(input.toInt) = "Y"
            break
          }
        }
      }

      new_player = !new_player
    }
    (board, result, new_player)
  }

  def eightqueens_Drawer(board: Array[Array[String]]): Unit = {
    val frame = new JFrame("8queens")
    frame.setLayout(new BorderLayout())
    val panel = new JPanel(new GridLayout(8, 8))
    for (i <- 0 until 8; j <- 0 until 8) {
      val button = new JButton()
      button.setOpaque(true)
      if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
        button.setBackground(Color.WHITE)
      }
      else if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
        button.setBackground(Color.GRAY)
      }
      board(i)(j) match {
        case "1" => button.setText("♛")
        case "0" =>
      }
      button.setPreferredSize(new Dimension(100, 100))
      button.setFont(button.getFont().deriveFont(64f))
      button.setEnabled(false)
      panel.add(button)
    }

    frame.add(panel, BorderLayout.CENTER)
    frame.pack()
    frame.setVisible(true)
  }

  def eightqueens_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(",")
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
      println("Invalid move")
      (board, false, player)
    } else {
      board(row)(col) = "1"
      (board, true, !player)
    }
  }

  def home(): String = {
    println("Choose a game to play:\n1.chess\n2.checkers\n3.tic_tac_toe\n4.connect4\n5.suduko\n6.8queens\nEnter your choice:")
    val input = StdIn.readLine().toInt
    var result: String = ""
    input match {
      case 1 => result = "chess"
      case 2 => result = "checkers"
      case 3 => result = "tic_tac_toe"
      case 4 => result = "connect4"
      case 5 => result = "suduko"
      case 6 => result = "8queens"
    }
    result
  }

}

