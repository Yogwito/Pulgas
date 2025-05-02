/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.armas;
import java.awt.Image;
import java.util.Collections;
import java.util.List;
import pulgas.models.GestorPuntaje;
import pulgas.models.Pulga;
import pulgas.models.PulgaMutante;
import pulgas.models.PulgaNormal;

/**
 *
 * @author juans
 *
 */
public class MisilPulgoson implements Arma {
    @Override
    public int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal) {
        if (pulgas.isEmpty()) {
            return 0;
        }
        
        // Calcular cuántas pulgas eliminar (50%)
        int cantidadEliminar = Math.max(1, pulgas.size() / 2);
        Collections.shuffle(pulgas); // Aleatorizar para no eliminar siempre las mismas
        
        int pulgasEliminadas = 0;
        
        for (int i = 0; i < cantidadEliminar && !pulgas.isEmpty(); i++) {
            Pulga pulga = pulgas.get(0);
            if (pulga.recibirImpacto()) {
                pulgas.remove(0);
                pulgasEliminadas++;
            } else if (pulga instanceof PulgaMutante) {
                // Convertir pulga mutante en normal
                PulgaNormal pulgaNormal = new PulgaNormal(pulga.getX(), pulga.getY(), imgPulgaNormal);
                pulgas.set(0, pulgaNormal);
            }
        }
        
        gestorPuntaje.aumentarPuntaje(pulgasEliminadas);
        return pulgasEliminadas;
    }
}



