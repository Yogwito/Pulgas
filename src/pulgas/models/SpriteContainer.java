/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pulgas.models;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Clase que representa un contenedor de sprites.
 * Permite gestionar múltiples sprites como una entidad única.
 * 
 * @author juans
 */
public class SpriteContainer extends Sprite {
    // Lista de sprites contenidos
    protected ArrayList<Sprite> sprites;
    
    /**
     * Constructor por defecto
     */
    public SpriteContainer() {
        sprites = new ArrayList<>();
    }
    
    /**
     * Añade un sprite al contenedor
     * 
     * @param sprite El sprite a añadir
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
    
    /**
     * Elimina un sprite del contenedor
     * 
     * @param sprite El sprite a eliminar
     */
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }
    
    /**
     * Obtiene la lista de todos los sprites contenidos
     * 
     * @return ArrayList con todos los sprites
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
    
    /**
     * Dibuja todos los sprites contenidos
     * 
     * @param g Contexto gráfico donde dibujar
     */
    @Override
    public void paint(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.paint(g);
        }
    }
}