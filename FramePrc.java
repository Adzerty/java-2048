import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FramePrc extends Frame implements KeyListener, ActionListener
{
  private PanelPlateau panelPlateau;
  private int score;
  private JPanel panelTmp;
  private JLabel lblScore;
  private JLabel lblBestScore;
  private JTextField txtScore;
  private JTextField txtBestScore;
  private JButton btnRst;

  private String bestScore;

  private Plateau plateau;

  public FramePrc(Plateau p)
  {
    this.plateau = p;

    this.setTitle("2048");
    this.setLocation(50,50);
    this.setSize(500,500);

    this.panelPlateau = new PanelPlateau(p);
    this.panelTmp = new JPanel();

    this.lblScore = new JLabel("Score de la partie : ");
    this.lblScore.setForeground(Color.black);

    this.txtScore = new JTextField(Integer.toString(plateau.getScore()));
    this.txtScore.setEditable(false);

    this.btnRst = new JButton("Recommencer");
    this.btnRst.addActionListener(this);


    this.addKeyListener(this);

    this.lblBestScore = new JLabel("Meilleure score : ");
    this.lblBestScore.setForeground(Color.black);

    this.txtBestScore = new JTextField(Integer.toString(plateau.getBestScore()));
    this.txtBestScore.setEditable(false);



    this.panelPlateau.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

    this.add(panelPlateau, "Center");
    this.panelTmp.add(lblScore);
    this.panelTmp.add(txtScore);
    this.panelTmp.add(btnRst);
    this.panelTmp.add(lblBestScore);
    this.panelTmp.add(txtBestScore);
    this.add(panelTmp, "South");

    this.setVisible(true);
  }

  public void repeindre()
  {
    this.panelPlateau.afficherPlateauPanel();
    this.validate();
  }

  public void ajouterScore(int i)
  {
    this.score += i;
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == this.btnRst)
    {
      this.plateau.recommencer();
    }
  }
  public void keyPressed(KeyEvent e)
  {
    if(e.getKeyCode() == 38)
      if(this.plateau.bougerTuile('H'))
        this.plateau.ajouterTuile();
    if(e.getKeyCode() == 40)
      if(this.plateau.bougerTuile('B'))
        this.plateau.ajouterTuile();
    if(e.getKeyCode() == 37)
      if(this.plateau.bougerTuile('G'))
        this.plateau.ajouterTuile();
    if(e.getKeyCode() == 39)
      if(this.plateau.bougerTuile('D'))
        this.plateau.ajouterTuile();

    this.txtScore.setText(Integer.toString(plateau.getScore()));
    this.repeindre();

  }

  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}

}
