import java.awt.{BorderLayout, Color, Dimension, GridLayout}
import javax.swing.{JButton, JFrame, JPanel}
import scala.io.StdIn
import scala.language.postfixOps
import scala.util.control.Breaks._

object Main extends Exception {
  def main(args: Array[String]): Unit = {
    //decide which game
    val Game = home()
    Game match {
      case "chess" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "checkers" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "tic_tac_toe" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "connect4" => play(connect4_Drawer, connect4_Controller, initialize_board(Game))
      case "sudoko" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
      case "8queens" => play(tic_tac_toe_Drawer, tic_tac_toe_Controller, initialize_board(Game))
    }

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
        Array("", "", "")
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
      case "sudoko" => val myboard = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array(" ", "", "")
      )
        myboard
      case "8queens" => val myboard = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array("", "", "")
      )
        myboard
    }

  }

  def play(drawer: Array[Array[String]] => Unit, controller: (String, Array[Array[String]], Boolean) => (Array[Array[String]], Boolean, Boolean), board: Array[Array[String]]): Unit = {
    val turn: Boolean = true
    val result: Boolean = true
    var tupel = (board, result, turn)
    while (true) {
      val input = StdIn.readLine("Enter input: ")
      tupel = controller(input, tupel._1, tupel._3)
      if (tupel._2) {
        drawer(tupel._1)
      }
      else
        println("invalid input")

    }
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
    //    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  }

  def tic_tac_toe_Controller(input: String, board: Array[Array[String]], player: Boolean): (Array[Array[String]], Boolean, Boolean) = {
    val inputArray = input.split(",")
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
        case "R" => button.setBackground(Color.RED)
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

  def home(): String = {
    println("1.chess\n2.checkers\n3.tic_tac_toe\n4.connect4\n5.sudoko\n6.8queens\nenter your choice:")
    val input = StdIn.readLine().toInt
    var result: String = ""
    input match {
      case 1 => result = "chess"
      case 2 => result = "checkers"
      case 3 => result = "tic_tac_toe"
      case 4 => result = "connect4"
      case 5 => result = "sudoko"
      case 6 => result = "8queens"
    }
    result
  }
}

