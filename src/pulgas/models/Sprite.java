/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta que representa un sprite básico en el juego.
 * Sirve como base para todos los objetos visuales que se mostrarán en pantalla.
 * 
 * @author juans
 */

public abstract class Sprite {
    // Propiedades básicas del sprite
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    /**
     * Dibuja el sprite en el contexto gráfico proporcionado.
     * 
     * @param g Contexto gráfico donde se dibujará el sprite
     */
    public abstract void paint(Graphics g);
    
    /**
     * Obtiene los límites del sprite como un rectángulo.
     * Útil para detección de colisiones.
     * 
     * @return Un objeto Rectangle con la posición y dimensiones del sprite
     */
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * Comprueba si este sprite colisiona con otro.
     * 
     * @param otro El otro sprite con el que comprobar colisión
     * @return true si hay colisión, false en caso contrario
     */
    public boolean checkCollision(Sprite otro) {
        return getBoundaries().intersects(otro.getBoundaries());
    }
}