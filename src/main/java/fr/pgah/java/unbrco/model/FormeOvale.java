package fr.pgah.java.unbrco.model;

import fr.pgah.java.son.MidiSynth;
import java.awt.*;

public class FormeOvale extends Forme {

    public FormeOvale(Point hautGauche, MidiSynth midiSynth) {
        super(hautGauche, midiSynth);
        this.instrument = 2;
    }

    @Override
    public void dessiner(Graphics g) {
        Color saveCouleur = g.getColor();
        if (estSelectionnee) {
            g.setColor(COULEUR_LIGNE_JOUEE);
        } else {
            g.setColor(Color.blue);
        }
        g.fillOval(x, y, longueur, hauteur);
        g.setColor(saveCouleur);
        g.drawOval(x, y, longueur, hauteur);

        if (colonneJouee > 0 && colonneJouee < longueur) {
            g.setColor(Color.black);
            g.drawLine(x + colonneJouee, y, x + colonneJouee, y + hauteur);
            g.setColor(saveCouleur);
        }
    }
}
