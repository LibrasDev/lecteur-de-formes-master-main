package fr.pgah.java.unbrco.model;

import fr.pgah.java.son.MidiSynth;
import java.awt.*;

public class FormeRectangle extends Forme {

    public FormeRectangle(Point hautGauche, MidiSynth midiSynth) {
        super(hautGauche, midiSynth);
        this.instrument = 1;
    }

    @Override
    public void dessiner(Graphics g) {
        Color saveCouleur = g.getColor();
        if (estSelectionnee) {
            g.setColor(COULEUR_LIGNE_JOUEE);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, longueur, hauteur);
        g.setColor(saveCouleur);
        g.drawRect(x, y, longueur, hauteur);

        if (colonneJouee > 0 && colonneJouee < longueur) {
            g.setColor(Color.red);
            g.drawLine(x + colonneJouee, y, x + colonneJouee, y + hauteur);
            g.setColor(saveCouleur);
        }
    }
}
