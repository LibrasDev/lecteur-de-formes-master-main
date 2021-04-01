package fr.pgah.java.unbrco.model;

import fr.pgah.java.son.MidiSynth;
import java.awt.*;

public abstract class Forme {

  protected static final Color COULEUR_LIGNE_JOUEE = new Color(230, 158, 60);

  protected int x;
  protected int y;
  protected int longueur;
  protected int hauteur;
  protected boolean estSelectionnee;
  protected MidiSynth midiSynth;
  protected int instrument;
  protected int colonneJouee;

  public Forme(Point hautGauche, MidiSynth midiSynth) {

    this.x = (int) hautGauche.getX();
    this.y = (int) hautGauche.getY();
    this.longueur = 0;
    this.hauteur = 0;
    estSelectionnee = false;
    this.midiSynth = midiSynth;
    instrument = 0;
    colonneJouee = 0;
  }

  public boolean contientX(int x) {

    if (x >= this.x && x <= this.x + longueur) {
      return true;
    } else if (x <= this.x && x >= this.x + longueur) {
      return true;
    }
    return false;

  }

  public boolean contientY(int y) {

    if (y <= this.y + hauteur && y >= this.y) {
      return true;
    }

    else if (y >= this.y + hauteur && y <= this.y) {
      return true;
    }

    return false;

  }

  public boolean contient(Point pt) {

    if (contientX((int) pt.getX()) && contientY((int) pt.getY())) {

      return true;
    }

    return false;
  }

  public void setLimites(Point basDroite) {
    longueur = basDroite.x - x;
    hauteur = basDroite.y - y;
  }

  public void deplacer(int dx, int dy) {
    boolean changementNote;
    changementNote = (convertirCoordVersNote(y) != convertirCoordVersNote(y + dy));
    if (changementNote) {
      stopper();
    }
    x += dx;
    y += dy;
    if (changementNote) {
      jouer();
    }
  }

  public void selectionnerEtJouer() {
    if (!estSelectionnee) {
      estSelectionnee = true;
      jouer();
    }
  }

  public void deselectionnerEtStopper() {
    if (estSelectionnee) {
      estSelectionnee = false;
      stopper();
    }
  }

  private void jouer() {
    int volume = convertirZoneVersVelocite(longueur * hauteur);
    midiSynth.play(instrument, convertirCoordVersNote(y), volume);
  }

  private void stopper() {
    midiSynth.stop(instrument, convertirCoordVersNote(y));
  }

  private int convertirZoneVersVelocite(int zone) {
    return Math.max(60, Math.min(127, zone / 30));
  }

  private int convertirCoordVersNote(int y) {
    return 70 - y / 12;
  }

  public void setColonneJouee(int colonneJouee) {
    this.colonneJouee = colonneJouee;

  }

  public int getLongueur() {
    return longueur;
  }

  public abstract void dessiner(Graphics g);
}
