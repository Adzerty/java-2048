/*
@author : Adrien PESTEL
Classe Tuile : comporte une valeur
Si la valeur de la Tuile est 0 alors la Tuile est vide, sinon elle est visible
et solide (aucune Tuile peut la traverser).
*/

public class Tuile
{
  private int valeur;
  //Prevoir un attribut icon pour pouvoir mettre une image à la tuile.

  public Tuile()     { this.valeur = 0; } /* CONSTRUCTEURS */
  public Tuile(int v){ this.valeur = v; }

  public void setValeur(int v){ this.valeur = v;    } /* SETTER de l'attribut : valeur */
  public int  getValeur()     { return this.valeur; } /* GETTER de l'attribut : valeur */



}
