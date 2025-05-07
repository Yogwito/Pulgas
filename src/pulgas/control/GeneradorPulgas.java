/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.control;

/**
 *
 * @author juans & Trujirend & JJDaza
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
     * Detiene la ejecución del hilo de generación de pulgas.
     */
    public void detener() {
        ejecutando = false;
        interrupt(); // Interrumpe si está dormido
    }
}
