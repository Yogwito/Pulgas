/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.control;

/**
 *
 * @author juans
 *
 */

public class GeneradorPulgas extends Thread {
    private CampoBatalla campoBatalla;
    private boolean ejecutando;
    
    public GeneradorPulgas(CampoBatalla campoBatalla) {
        this.campoBatalla = campoBatalla;
        this.ejecutando = true;
    }
    
    @Override
    public void run() {
        try {
            int contadorNormal = 0;
            int contadorMutante = 0;
            
            while (ejecutando) {
                Thread.sleep(1000); 
                
                contadorNormal++;
                contadorMutante++;
                
                if (contadorNormal >= 5) { 
                    campoBatalla.agregarPulgaNormal();
                    contadorNormal = 0;
                }
                
                if (contadorMutante >= 10) { 
                    campoBatalla.agregarPulgaMutante();
                    contadorMutante = 0;
                }
            }
        } catch (InterruptedException e) {
            ejecutando = false;
        }
    }
    
    public void detener() {
        ejecutando = false;
        interrupt();
    }
}
