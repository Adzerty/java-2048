/*
@author : Adrien PESTEL
Classe Plateau :
*/

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;


public class Plateau
{
  private Tuile[][] ensTuile;
  private int score;
  private int bestScore;
  private FramePrc f;

  public Plateau()  /* CONSTRUCTEUR */
  {
    this.ensTuile = new Tuile[4][4];
    for (int i=0; i<4; i++)
    {
      for (int j=0; j<4; j++)
      {
        this.ensTuile[i][j] = new Tuile();
      }
    }

    this.score = 0;
    try{
      Scanner sc = new Scanner ( new File ( "bestScore.data"), "utf-8" );
      this.bestScore = Integer.parseInt(sc.nextLine());
    }
    catch(Exception e){
      System.out.println(e);
    }

    this.ajouterTuile();
    this.ajouterTuile();

    f = new FramePrc(this);
    f.repeindre();

  }

  public String afficherPlateau()
  {
    String sRet = "+---+---+---+---+\n";

    for (int i=0; i<4; i++)
    {
      for (int j=0; j<4; j++)
      {
        sRet += "| "+ensTuile[i][j].getValeur()+" ";
      }
      sRet += "|\n+---+---+---+---+\n";
    }

    return sRet;
  }

  public static void main(String[] args)
  {
    Plateau p = new Plateau();
  }
  /*
    Méthode bougerTuile(char dir)
    Réalise le déplacement et la combinaison des tuiles dans la direction
    précisée en paramètre et retourne un booléen :
      FAUX = déplacement impossible
      VRAI = déplacement possible ET réalisé.
  */
  public boolean bougerTuile(char dir)
  {
    boolean bOk = false;

    Tuile[][] ensTmp = new Tuile[4][4];

    for (int i = 0;i<4;i++)
      for (int j = 0;j<4 ;j++ )
          ensTmp[i][j] = new Tuile(this.ensTuile[i][j].getValeur());

    for (int i = 0;i<4;i++)
    {
        this.regrouperTuile(dir, i);
        this.additionTuile(dir, i);
        this.regrouperTuile(dir, i);
    }

    for (int i = 0;i<4;i++)
      for (int j = 0;j<4 ;j++ )
          if(this.ensTuile[i][j].getValeur()!= ensTmp[i][j].getValeur())
            bOk = true;

    return bOk;
  }

  public Tuile[][] getEnsTuile(){ return this.ensTuile; }

  /*
    Méthode regrouperTuile(char dir, int i)
    Bouge toutes les tuiles lorsque c'est possible dans la direction (H,B,G,D)
  */
  private void regrouperTuile(char dir, int k)
  {
    switch(dir)
    {
      case 'H':
        for(int i = 0; i<4; i++)
        {
          if(this.ensTuile[i][k].getValeur() == 0)
          {
            int j = i+1;
            Tuile tuileTmp = null;
            while(j<4)
            {
              if(this.ensTuile[j][k].getValeur() != 0)
              {
                tuileTmp = this.ensTuile[j][k];

                this.ensTuile[i][k].setValeur(tuileTmp.getValeur());
                this.ensTuile[j][k].setValeur(0);
                j=5;
              }
              else
                j++;
            }
          }
        }
        break;

      case 'B':
        for(int i = 3; i>=0; i--)
        {
          if(this.ensTuile[i][k].getValeur() == 0)
          {
            int j = i-1;
            Tuile tuileTmp = null;
            while(j>=0)
            {
              if(this.ensTuile[j][k].getValeur() != 0)
              {
                tuileTmp = this.ensTuile[j][k];

                this.ensTuile[i][k].setValeur(tuileTmp.getValeur());
                this.ensTuile[j][k].setValeur(0);
                j=-1;
              }
              else
                j--;
              }
            }
          }
          break;

      case 'G':
        for(int i = 0; i<4; i++)
        {
          if(this.ensTuile[k][i].getValeur() == 0)
          {
            int j = i+1;
            Tuile tuileTmp = null;
            while(j<4)
            {
              if(this.ensTuile[k][j].getValeur() != 0)
              {
                tuileTmp = this.ensTuile[k][j];

                this.ensTuile[k][i].setValeur(tuileTmp.getValeur());
                this.ensTuile[k][j].setValeur(0);
                j=5;
              }
              else
                j++;
              }
            }
          }
          break;
        case 'D':
          for(int i = 3; i>=0; i--)
          {
            if(this.ensTuile[k][i].getValeur() == 0)
            {
              int j = i-1;
              Tuile tuileTmp = null;
              while(j>=0)
              {
                if(this.ensTuile[k][j].getValeur() != 0)
                {
                  tuileTmp = this.ensTuile[k][j];

                  this.ensTuile[k][i].setValeur(tuileTmp.getValeur());
                  this.ensTuile[k][j].setValeur(0);
                  j=-1;
                }
                else
                  j--;
                }
              }
            }
            break;
    }

  }

  /*
    Méthode additionTuile(char dir, int i)
    Additionne les tuiles identiques juxtaposées
  */
  private void additionTuile(char dir, int k)
  {
    switch(dir)
    {
      case 'H':

        for (int i = 1;i<4 ;i++ )
        {
          if(this.ensTuile[i][k].getValeur() == this.ensTuile[i-1][k].getValeur())
          {
            this.score += this.ensTuile[i][k].getValeur() + this.ensTuile[i-1][k].getValeur();
            this.ensTuile[i-1][k].setValeur(this.ensTuile[i][k].getValeur() + this.ensTuile[i-1][k].getValeur());
            this.ensTuile[i][k].setValeur(0);
          }
        }
        break;
      case 'B':

        for (int i = 3;i>0 ;i-- )
        {
          if(this.ensTuile[i-1][k].getValeur() == this.ensTuile[i][k].getValeur())
          {
            this.score += this.ensTuile[i-1][k].getValeur() + this.ensTuile[i][k].getValeur();
            this.ensTuile[i][k].setValeur(this.ensTuile[i-1][k].getValeur() + this.ensTuile[i][k].getValeur());
            this.ensTuile[i-1][k].setValeur(0);
          }
        }
        break;
      case 'D':
        for (int i = 3;i>0 ;i-- )
        {
          if(this.ensTuile[k][i-1].getValeur() == this.ensTuile[k][i].getValeur())
          {
            this.score += this.ensTuile[k][i-1].getValeur() + this.ensTuile[k][i].getValeur();
            this.ensTuile[k][i].setValeur(this.ensTuile[k][i-1].getValeur() + this.ensTuile[k][i].getValeur());
            this.ensTuile[k][i-1].setValeur(0);
          }
        }
        break;
      case 'G':
        for (int i = 1;i<4 ;i++ )
        {
          if(this.ensTuile[k][i].getValeur() == this.ensTuile[k][i-1].getValeur())
          {
            this.score += this.ensTuile[k][i].getValeur() + this.ensTuile[k][i-1].getValeur();
            this.ensTuile[k][i-1].setValeur(this.ensTuile[k][i].getValeur() + this.ensTuile[k][i-1].getValeur());
            this.ensTuile[k][i].setValeur(0);
          }
        }
        break;
    }
  }

  public void ajouterTuile()
  {
    int[] tab2   ={2,2,2,2,2,4};
    int aleaLig, aleaCol;

    do
    {
      aleaLig = (int)(Math.random()*4);
      aleaCol = (int)(Math.random()*4);
    }
    while(this.ensTuile[aleaLig][aleaCol].getValeur() != 0);

    this.ensTuile[aleaLig][aleaCol].setValeur(tab2[(int)(Math.random()*6)]);
  }

  public void recommencer()
  {
    if(score > bestScore)
    {
      try
      {
        FileWriter fw = new FileWriter ("bestScore.data");
        PrintWriter pw = new PrintWriter ( fw );

        pw.print(score);
        fw.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    System.gc();
    f.dispose();
    new Plateau();
  }

  public int getScore()    { return this.score; }
  public int getBestScore(){return this.bestScore; }


}
