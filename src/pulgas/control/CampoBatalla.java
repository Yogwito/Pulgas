/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pulgas.control;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import pulgas.armas.*;
import pulgas.models.*;

/**
 * 
 * @author juans
 */
public class CampoBatalla extends JPanel {
    private List<Pulga> pulgas;
    private PistolaPulguipium pistola;
    private MisilPulgoson misil;
    private GestorPuntaje gestorPuntaje;
    private Image imgPulgaNormal;
    private Image imgPulgaMutante;
    private boolean juegoEnCurso;
    private boolean juegoIniciado;
    
    public CampoBatalla(GestorPuntaje gestorPuntaje) {
        this.gestorPuntaje = gestorPuntaje;
        pulgas = new ArrayList<>();
        pistola = new PistolaPulguipium();
        misil = new MisilPulgoson();
        juegoEnCurso = true;
        juegoIniciado = false;
        
        // Cargar imágenes
        cargarImagenes();
        
        // Configurar eventos del ratón
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    pistola.atacar(pulgas, e.getX(), e.getY(), gestorPuntaje, imgPulgaNormal);
                    if (!pulgas.isEmpty()) {
                        juegoIniciado = true;
                    }
                    repaint();
                }
            }
        });
        
        // Configurar foco para recibir eventos de teclado
        setFocusable(true);
        requestFocus();
    }
    
    private void cargarImagenes() {
        try {
            // Intentar cargar imágenes desde recursos
            URL urlNormal = getClass().getResource("/recursos/pulga_normal.png");
            URL urlMutante = getClass().getResource("/recursos/pulga_mutante.png");
            
            if (urlNormal != null && urlMutante != null) {
                imgPulgaNormal = new ImageIcon(urlNormal).getImage();
                imgPulgaMutante = new ImageIcon(urlMutante).getImage();
            } else {
                // Usar imágenes generadas si no se encuentran los recursos
                imgPulgaNormal = crearImagenGenerica(20, 20, Color.RED);
                imgPulgaMutante = crearImagenGenerica(25, 25, Color.GREEN);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes: " + e.getMessage());
            // Fallback a imágenes generadas
            imgPulgaNormal = crearImagenGenerica(20, 20, Color.RED);
            imgPulgaMutante = crearImagenGenerica(25, 25, Color.GREEN);
        }
    }
    
    private Image crearImagenGenerica(int ancho, int alto, Color color) {
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(color);
        g2d.fillOval(0, 0, ancho, alto);
        g2d.dispose();
        return img;
    }
    
    public Image obtenerImagenPulgaNormal() {
        return imgPulgaNormal;
    }
    
    public Image obtenerImagenPulgaMutante() {
        return imgPulgaMutante;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar fondo
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Dibujar pulgas
        for (Pulga pulga : pulgas) {
            pulga.dibujar(g);
        }
    }
    
    public void agregarPulgaNormal() {
        if (!juegoEnCurso) return;
        
        int x = (int) (Math.random() * (getWidth() - imgPulgaNormal.getWidth(null)));
        int y = (int) (Math.random() * (getHeight() - imgPulgaNormal.getHeight(null)));
        
        PulgaNormal nuevaPulga = new PulgaNormal(x, y, imgPulgaNormal);
        
        // Verificar que no colisione con otras pulgas
        boolean colision;
        int intentos = 0;
        do {
            colision = false;
            for (Pulga pulga : pulgas) {
                if (nuevaPulga.colisiona(pulga)) {
                    colision = true;
                    nuevaPulga.saltar(getWidth(), getHeight());
                    break;
                }
            }
            intentos++;
        } while (colision && intentos < 100);
        
        if (!colision) {
            pulgas.add(nuevaPulga);
            juegoIniciado = true;
            repaint();
        }
    }
    
    public void agregarPulgaMutante() {
        if (!juegoEnCurso) return;
        
        int x = (int) (Math.random() * (getWidth() - imgPulgaMutante.getWidth(null)));
        int y = (int) (Math.random() * (getHeight() - imgPulgaMutante.getHeight(null)));
        
        PulgaMutante nuevaPulga = new PulgaMutante(x, y, imgPulgaMutante);
        
        // Verificar que no colisione con otras pulgas
        boolean colision;
        int intentos = 0;
        do {
            colision = false;
            for (Pulga pulga : pulgas) {
                if (nuevaPulga.colisiona(pulga)) {
                    colision = true;
                    nuevaPulga.saltar(getWidth(), getHeight());
                    break;
                }
            }
            intentos++;
        } while (colision && intentos < 100);
        
        if (!colision) {
            pulgas.add(nuevaPulga);
            juegoIniciado = true;
            repaint();
        }
    }
    
    public void saltarPulgas() {
        if (!juegoEnCurso) return;
        
        for (Pulga pulga : pulgas) {
            pulga.saltar(getWidth(), getHeight());
        }
        repaint();
    }
    
    public void dispararMisil() {
        if (!juegoEnCurso) return;
        
        misil.atacar(pulgas, 0, 0, gestorPuntaje, imgPulgaNormal);
        if (!pulgas.isEmpty()) {
            juegoIniciado = true;
        }
        repaint();
    }
    
    public boolean juegoTerminado() {
        if (juegoEnCurso && juegoIniciado && pulgas.isEmpty()) {
            return true; // El juego está en curso, se inició (hubo pulgas) y ahora están todas eliminadas
        }
        return false;
    }
    
    public void reiniciarJuego() {
        pulgas.clear();
        juegoEnCurso = true;
        juegoIniciado = false;
        repaint();
    }
}
