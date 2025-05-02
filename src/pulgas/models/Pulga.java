/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author juans
 *
 * Clase abstracta que define el comportamiento base de las pulgas
 */

public abstract class Pulga {
    protected int x, y;
    protected int ancho, alto;
    protected Image imagen;
    
    /**
     * Dibuja la pulga en el componente gráfico
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }
    
    /**
     * Maneja la acción de recibir un impacto
     * @return true si la pulga debe ser eliminada, false en caso contrario
     */
    public abstract boolean recibirImpacto();
    
    /**
     * Hace que la pulga salte a una posición aleatoria
     */
    public void saltar(int maxX, int maxY) {
        this.x = (int) (Math.random() * (maxX - ancho));
        this.y = (int) (Math.random() * (maxY - alto));
    }
    
    /**
     * Obtiene el rectángulo de colisión de la pulga
     */
    public Rectangle obtenerRectangulo() {
        return new Rectangle(x, y, ancho, alto);
    }
    
    /**
     * Verifica si esta pulga colisiona con otra
     */
    public boolean colisiona(Pulga otra) {
        return obtenerRectangulo().intersects(otra.obtenerRectangulo());
    }
    
    /**
     * Obtiene la posición X
     */
    public int getX() {
        return x;
    }
    
    /**
     * Obtiene la posición Y
     */
    public int getY() {
        return y;
    }
}
