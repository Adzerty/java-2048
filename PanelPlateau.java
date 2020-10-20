import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class PanelPlateau extends JPanel
{
  private Plateau p;
  private JPanel[][] ensPanel;
  private Tuile[][] ensTuile;
  private JLabel[][] ensLabel;

  public PanelPlateau(Plateau p)
  {
    this.ensTuile = p.getEnsTuile();
    this.ensPanel = new JPanel[4][4];
    this.ensLabel = new JLabel[4][4];
    this.setLayout(new GridLayout(4,4));

    for (int i = 0;i<4 ;i++ )
    {
      for (int j = 0;j<4 ;j++ )
      {
          this.ensPanel[i][j] = null;
          this.ensPanel[i][j] = new JPanel(new GridBagLayout());
          this.ensPanel[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

          String num = Integer.toString(ensTuile[i][j].getValeur());
          if(num.equals("0")){ num = "";}

          this.ensLabel[i][j] = new JLabel(num);

          this.ensLabel[i][j].setForeground(Color.black);
          this.ensPanel[i][j].add(this.ensLabel[i][j]);
          this.add(ensPanel[i][j]);
      }
    }
  }

  public void afficherPlateauPanel()
  {
    for (int i = 0;i<4 ;i++ )
    {
      for (int j = 0;j<4 ;j++ )
      {
          String num = Integer.toString(ensTuile[i][j].getValeur());
          if(num.equals("0")){ num = "";}

          this.ensLabel[i][j].setText(num);
          switch(this.ensLabel[i][j].getText())
          {
            case "" : this.ensPanel[i][j].setBackground(new Color(255,255,255));break;
            case "2" : this.ensPanel[i][j].setBackground(new Color(255,0,0));break;
            case "4" : this.ensPanel[i][j].setBackground(new Color(0,255,0));break;
            case "8" : this.ensPanel[i][j].setBackground(new Color(0,0,255));break;
            case "16" : this.ensPanel[i][j].setBackground(new Color(255,255,0));break;
            case "32" : this.ensPanel[i][j].setBackground(new Color(255,0,255));break;
            case "64" : this.ensPanel[i][j].setBackground(new Color(0,255,255));break;
            case "128" : this.ensPanel[i][j].setBackground(new Color(100,100,100));break;
            case "256" : this.ensPanel[i][j].setBackground(new Color(200,100,50));break;
            case "512" : this.ensPanel[i][j].setBackground(new Color(100,200,50));break;
            case "1024" : this.ensPanel[i][j].setBackground(new Color(50,200,100));break;
            case "2048" : this.ensPanel[i][j].setBackground(new Color(50,100,200));break;
          }
      }
    }
  }
}
