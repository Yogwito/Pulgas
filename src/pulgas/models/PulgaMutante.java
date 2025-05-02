/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;
import java.awt.Image;
/**
 *
 * @author juans
 */

public class PulgaMutante extends Pulga {
    public PulgaMutante(int x, int y, Image imagen) {
        this.x = x;
        this.y = y;
        this.imagen = imagen;
        this.ancho = imagen.getWidth(null);
        this.alto = imagen.getHeight(null);
    }
    
    @Override
    public boolean recibirImpacto() {
        // Pulga mutante se convierte en normal con un impacto
        return false; // Indica que la pulga no debe eliminarse sino transformarse
    }
}

