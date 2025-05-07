/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;

import java.awt.Image;

/**
 * Clase que representa una pulga mutante.
 * Las pulgas mutantes no mueren con un impacto, sino que se transforman en pulgas normales.
 * 
 * @author juans
 */
public class PulgaMutante extends Pulga {
    
    /**
     * Constructor de pulga mutante
     * 
     * @param x Posición inicial X
     * @param y Posición inicial Y
     * @param imagen Imagen de la pulga
     */
    public PulgaMutante(int x, int y, Image imagen) {
        super(x, y, imagen);
    }
    
    /**
     * Maneja el impacto recibido.
     * Las pulgas mutantes no mueren con un impacto, sino que se transforman en pulgas normales.
     * 
     * @return false indicando que la pulga no debe ser eliminada sino transformada
     */
    @Override
    public boolean recibirImpacto() {
        // Pulga mutante se convierte en normal con un impacto
        return false; // Indica que la pulga no debe eliminarse sino transformarse
    }
    
    /**
     * Implementación de updatePosition para pulgas mutantes.
     * Las pulgas mutantes podrían tener comportamiento de movimiento especial.
     */
    @Override
    public void updatePosition() {
        // Por ahora, las pulgas mutantes no tienen movimiento automático
        // El movimiento se maneja con el método saltar()
        // Se podría implementar movimiento aleatorio aquí si se desea
    }
}