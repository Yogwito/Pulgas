/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Clase abstracta que representa una pulga en el juego.
 * Extiende SpriteMobile para heredar sus funcionalidades de movimiento.
 * 
 * @author juans
 */
public abstract class Pulga extends SpriteMobile {
    // La imagen específica de la pulga
    protected Image imagen;
    
    /**
     * Constructor de pulga
     * 
     * @param x Posición inicial X
     * @param y Posición inicial Y
     * @param imagen Imagen de la pulga
     */
    public Pulga(int x, int y, Image imagen) {
        super(5); // Paso de movimiento predeterminado
        this.x = x;
        this.y = y;
        this.imagen = imagen;
        this.width = imagen.getWidth(null);
        this.height = imagen.getHeight(null);
    }
    // Agrega estos métodos en la clase Pulga
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    /**
     * Dibuja la pulga en el componente gráfico
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }
    
    /**
     * Método heredado pero mantenido por compatibilidad.
     * Se recomienda usar paint() en su lugar.
     */
    public void dibujar(Graphics g) {
        paint(g);
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
        this.x = (int) (Math.random() * (maxX - width));
        this.y = (int) (Math.random() * (maxY - height));
    }
    
    /**
     * Obtiene el rectángulo de colisión de la pulga.
     * Override del método en Sprite para mantener compatibilidad con el código existente.
     */
    public Rectangle obtenerRectangulo() {
        return getBoundaries();
    }
    
    /**
     * Verifica si esta pulga colisiona con otra.
     * Override del método en Sprite para mantener compatibilidad con el código existente.
     */
    public boolean colisiona(Pulga otra) {
        return checkCollision(otra);
    }
}