import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SimpleGUI {
   public static void main(String[] args) {
      final JFrame frame = new JFrame("Simple GUI Example");
      frame.setSize(400, 300);
      frame.setDefaultCloseOperation(3);
      frame.setLayout((LayoutManager)null);
      final JTextField textField = new JTextField();
      textField.setBounds(50, 50, 300, 30);
      frame.add(textField);
      JButton button = new JButton("Click Me");
      button.setBounds(150, 100, 100, 30);
      frame.add(button);
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String inputText = textField.getText();
            JOptionPane.showMessageDialog(frame, "You entered: " + inputText);
         }
      });
      frame.setVisible(true);
   }
}
