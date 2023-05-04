import java.awt._
import java.awt.event._
import javax.swing._
import java.awt.Color
import javax.swing.border.Border
import scala.util.control.Breaks.break
class Connect4 {


  // Create the game board
  def createBoard(): JPanel = {
    val ROWS = 6
    val COLS = 7
    val board = Array.ofDim[JLabel](ROWS, COLS)
    val panel = new JPanel(new GridLayout(ROWS+2, COLS))
    var count: String = "0"
    for (col <- 0 until COLS) {
      panel.add(new JLabel(count))
      var temp:Int=count.toInt
      temp+=1
      count=temp.toString
    }
    for (row <- 0 until ROWS) {
      for(col<- 0 until COLS) {
        board(row)(col) = new JLabel()
        board(row)(col) setBorder BorderFactory.createLineBorder(Color.BLACK)
        board(row)(col).setOpaque(true)
        board(row)(col).setBackground(Color.WHITE)
        println(board(row)(col).getBackground)
        //      board(row)(col).addActionListener(this)
        panel.add(board(row)(col))
      }
    }
    val textfield = new JTextField()
    val input=new JLabel("enter column")
    val button=new JButton("ENTER")
    button.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent): Unit = {
             Controller(board,textfield.getText.toInt)
      }
    })
    panel.add(input)
    textfield.setBorder(BorderFactory.createLineBorder(Color.RED))
    textfield.setPreferredSize(new Dimension(100, 20))
    textfield.setForeground(Color.RED)
    panel.add(textfield)
    panel.add(button)
   panel
  }
  def Controller(board:Array[Array[JLabel]],input:Int):Unit={
    var red_cells: Int = 0
    var yellow_cells:Int=0
    //player=1 (yellow) player=2 (red)
    var player:Int=1
    for (row <- 0 until 6) {
      for (col <- 0 until 7) {
      if(board(row)(col).getBackground==Color.RED)red_cells+=1
      else if(board(row)(col).getBackground==Color.YELLOW)yellow_cells+=1
      }
    }
    if(red_cells>yellow_cells)player=1
    else if(yellow_cells>red_cells)player=2
    else player=1
for(i<- 5 until 0)
  {
   if(board(i)(input).getBackground==Color.WHITE)
     { if(player==1) {
       board(i)(input).setBackground(Color.YELLOW)
     }
     else {
       board(i)(input).setBackground(Color.RED)

     }
       break
     }
  }
  }

}
