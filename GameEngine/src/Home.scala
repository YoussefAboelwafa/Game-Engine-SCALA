
import java.awt.{BorderLayout, Color, Dimension}
import javax.swing.{JButton, JFrame, JPanel, WindowConstants}

object Home extends App{
  val frame: JFrame = new JFrame("Board Games")
  val panel: JPanel = new JPanel()

  val TicTacToe: JButton = new JButton("Tic-Tac-Toe")
  val Chess: JButton = new JButton("Chess")
  val Suduko: JButton = new JButton("Sudoku")
  val Checkers: JButton = new JButton("Checkers")
  val Connect4: JButton = new JButton("Connect-4")
  val EightQueens: JButton = new JButton("Eight Queens")
  frame.setMinimumSize(new Dimension(600, 600))
  frame.setLocationRelativeTo(null)
  frame.setResizable(true)
  frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
  frame.setLayout(new BorderLayout(10, 10))

  panel.setBounds(0, 50, 800, 800)
  panel.setLocation(2000, 2000)
  panel.setBackground(Color.white)

  //  frame.add(reverse, BorderLayout.NORTH)
  val buttonList = List(Chess,TicTacToe, Checkers, Connect4, Suduko, EightQueens)
  buttonList.foreach(_.setBackground(Color.yellow))
  buttonList.foreach(panel.add)

  frame.add(panel)
  frame.pack()
  frame.setVisible(true)
}