/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.armas;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import pulgas.models.GestorPuntaje;
import pulgas.models.Pulga;
import pulgas.models.PulgaMutante;
import pulgas.models.PulgaNormal;

/**
 * Implementación del arma "Pistola Pulguipium", que dispara en una pequeña
 * área y afecta a una sola pulga que esté en el punto de impacto.
 */
public class PistolaPulguipium implements Arma {

    /**
     * Ejecuta el disparo de la Pistola Pulguipium.
     * El arma detecta si alguna pulga está en el área del disparo y actúa:
     * si la pulga es eliminada, se suma al puntaje; si es mutante y sobrevive,
     * se convierte en una pulga normal. Solo afecta a una pulga por disparo.
     */
    @Override
    public int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal) {
        Rectangle puntoDisparo = new Rectangle(x - 5, y - 5, 10, 10);
        int pulgasEliminadas = 0;

        for (int i = 0; i < pulgas.size(); i++) {
            Pulga pulga = pulgas.get(i);
            if (pulga.obtenerRectangulo().intersects(puntoDisparo)) {
                if (pulga.recibirImpacto()) {
                    pulgas.remove(i);
                    pulgasEliminadas++;
                    gestorPuntaje.aumentarPuntaje(1);
                } else if (pulga instanceof PulgaMutante) {
                    // Convertir pulga mutante en normal
                    PulgaNormal pulgaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), imgPulgaNormal);
                    pulgas.set(i, pulgaNormal);
                }
                break; // Solo afecta a una pulga
            }
        }

        return pulgasEliminadas;
    }
}
