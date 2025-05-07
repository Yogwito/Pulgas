/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.armas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pulgas.models.*;
/**
 *
 * @author juans & Trujirend & JJDaza
 *
 */
/**
 * Implementación del arma "Misil Pulgoson", que ataca eliminando al azar
 * el 50% de las pulgas presentes. Las pulgas mutantes impactadas que no
 * son eliminadas se transforman en pulgas normales.
 */
public class MisilPulgoson implements Arma {

    /**
     * Ejecuta el ataque del Misil Pulgoson.
     * Elimina al azar la mitad de las pulgas. Si una pulga mutante sobrevive,
     * se convierte en una pulga normal. Se actualiza el puntaje según las pulgas eliminadas.
     */
    @Override
    public int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal) {
        if (pulgas.isEmpty()) return 0;
        List<Pulga> copia = new ArrayList<>(pulgas);
        Collections.shuffle(copia);
        int cantidadEliminar = Math.max(1, copia.size() / 2);
        int eliminadas = 0;

        for (int i = 0; i < cantidadEliminar && i < copia.size(); i++) {
            Pulga p = copia.get(i);
            if (!pulgas.contains(p)) continue;
            if (p.recibirImpacto()) {
                pulgas.remove(p);
                eliminadas++;
            } else if (p instanceof PulgaMutante) {
                int idx = pulgas.indexOf(p);
                if (idx >= 0) {
                    PulgaNormal nueva = new PulgaNormal(p.getX(), p.getY(), imgPulgaNormal);
                    pulgas.set(idx, nueva);
                }
            }
        }

        gestorPuntaje.aumentarPuntaje(eliminadas);
        return eliminadas;
    }
}


