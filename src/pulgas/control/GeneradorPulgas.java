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

/**
 * Hilo encargado de generar pulgas normales y mutantes periódicamente
 * mientras el juego esté en ejecución.
 */
public class GeneradorPulgas extends Thread {
    private CampoBatalla campoBatalla;
    private boolean ejecutando;

    /**
     * Constructor que recibe el campo de batalla donde se agregarán las pulgas.
     */
    public GeneradorPulgas(CampoBatalla campoBatalla) {
        this.campoBatalla = campoBatalla;
        this.ejecutando = true;
    }

    /**
     * Ejecuta el hilo que agrega pulgas normales cada 5 segundos y mutantes cada 10 segundos.
     */
    @Override
    public void run() {
        try {
            int contadorNormal = 0;
            int contadorMutante = 0;

            while (ejecutando) {
                Thread.sleep(1000); // Espera 1 segundo

                contadorNormal++;
                contadorMutante++;

                if (contadorNormal >= 5) {
                    campoBatalla.agregarPulgaNormal(); // Cada 5 segundos
                    contadorNormal = 0;
                }

                if (contadorMutante >= 10) {
                    campoBatalla.agregarPulgaMutante(); // Cada 10 segundos
                    contadorMutante = 0;
                }
            }
        } catch (InterruptedException e) {
            ejecutando = false;
        }
    }

    /**
     * Detiene la ejecución del hilo de generación de pulgas.
     */
    public void detener() {
        ejecutando = false;
        interrupt(); // Interrumpe si está dormido
    }
}
