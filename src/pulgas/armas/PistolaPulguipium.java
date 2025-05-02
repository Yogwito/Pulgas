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
 *
 * @author juans
 */
public class PistolaPulguipium implements Arma {
    @Override
    public int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal) {
        Rectangle puntoDisparo = new Rectangle(x-5, y-5, 10, 10);
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
