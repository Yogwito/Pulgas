/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;

import java.awt.Image;

/**
 * Clase que representa una pulga normal.
 * Las pulgas normales mueren con un solo impacto.
 * 
 * @author juans
 */
public class PulgaNormal extends Pulga {
    
    /**
     * Constructor de pulga normal
     * 
     * @param x Posición inicial X
     * @param y Posición inicial Y
     * @param imagen Imagen de la pulga
     */
    public PulgaNormal(int x, int y, Image imagen) {
        super(x, y, imagen);
    }
    
    /**
     * Maneja el impacto recibido.
     * Las pulgas normales mueren con un solo impacto.
     * 
     * @return true indicando que la pulga debe ser eliminada
     */
    @Override
    public boolean recibirImpacto() {
        // Pulga normal muere con un solo impacto
        return true; // Indica que la pulga debe eliminarse
    }
    
    /**
     * Implementación de updatePosition para pulgas normales.
     * Por defecto, las pulgas normales no se mueven automáticamente.
     */
    @Override
    public void updatePosition() {
        // Pulgas normales no tienen movimiento automático
        // El movimiento se maneja con el método saltar()
    }
}