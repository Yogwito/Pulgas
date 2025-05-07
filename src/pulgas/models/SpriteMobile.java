
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;

/**
 * Clase que representa un sprite con capacidad de movimiento.
 * Extiende la clase Sprite añadiendo funcionalidad para actualizar su posición.
 * 
 * @author juans
 */
public class SpriteMobile extends Sprite {
    // Paso de movimiento del sprite
    protected int step;
    
    /**
     * Constructor por defecto
     */
    public SpriteMobile() {
        this.step = 1;
    }
    
    /**
     * Constructor con paso específico
     * 
     * @param step Valor del paso de movimiento
     */
    public SpriteMobile(int step) {
        this.step = step;
    }
    
    /**
     * Actualiza la posición del sprite según sus propiedades de movimiento
     */
    public void updatePosition() {
        // Implementación básica - puede ser sobrescrita por subclases
        // para comportamientos más complejos
    }
    
    /**
     * Implementación del método paint heredado de Sprite.
     * En esta clase base simplemente proporciona una implementación vacía.
     */
    @Override
    public void paint(java.awt.Graphics g) {
        // La implementación específica se hará en las subclases
    }
}