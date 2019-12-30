import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import countdown.CountdownDictionary;

public class CountdownLettersSolver extends JPanel implements ActionListener
{
  CountdownDictionary dictionary;
  JTextField input, output;

  public CountdownLettersSolver(CountdownDictionary dictionary)
  {
    this.dictionary = dictionary;
    setLayout(new FlowLayout());
    input = new JTextField(50);
    output = new JTextField(50);
    output.setEditable(false);
    output.setText("Enter letters in the input field and press solve");
    JButton solve = new JButton("Solve");
    solve.addActionListener(this);
    add(input);
    add(output);
    add(solve);
  }

  public void actionPerformed(ActionEvent e)
  {
    output.setText(dictionary.solve(input.getText()));
  }

  public static void main(String[] args)
  {
    CountdownDictionary dictionary = new CountdownDictionary();
    JFileChooser chooser = new JFileChooser();
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
    {
      try
      {
        BufferedReader in = new BufferedReader(new FileReader(chooser.getSelectedFile()));
        for (String s = in.readLine();s != null;s = in.readLine())
        {
          dictionary.add(s, s);
        }
        CountdownLettersSolver panel = new CountdownLettersSolver(dictionary);
        JFrame window = new JFrame("Countdown Letters Solver");
        window.getContentPane().add(panel);
        window.addWindowListener
        (
          new WindowAdapter()
          {
            public void windowClosing(WindowEvent e)
            {
              System.exit(0);
            }
          }
        );
        window.setSize(250, 250);
        window.show();
        in.close();
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null, "An error occurred while accessing the dictionary file");
        return;
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null, "You must select a file containing the dictionary");
    }
  }
}