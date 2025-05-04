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
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import pulgas.armas.*;
import pulgas.models.*;

/**
 *
 * @author juans & Trujirend & JJDaza
 *
 */
/**
 * Clase que representa el campo de batalla donde interactúan las pulgas y las armas.
 * Gestiona el dibujo, las interacciones del jugador y la lógica de juego.
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

    /**
     * Constructor que inicializa el campo de batalla y configura imágenes y eventos del ratón.
     */
    public CampoBatalla(GestorPuntaje gestorPuntaje) {
        this.gestorPuntaje = gestorPuntaje;
        pulgas = new ArrayList<>();
        pistola = new PistolaPulguipium();
        misil = new MisilPulgoson();
        juegoEnCurso = true;
        juegoIniciado = false;

        cargarImagenes(); // Cargar imágenes de las pulgas

        // Detectar clic izquierdo para disparar con la pistola
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

        // Permitir recibir eventos de teclado
        setFocusable(true);
        requestFocus();
    }

    /**
     * Carga las imágenes de las pulgas desde los recursos, o genera imágenes genéricas si no se encuentran.
     */
    private void cargarImagenes() {

        try {
            URL urlNormal = getClass().getResource("/recursos/pulga_normal.png");
            URL urlMutante = getClass().getResource("/recursos/pulga_mutante.png");

            // Intentar cargar imágenes desde recursos
            if (urlNormal != null && urlMutante != null) {
                imgPulgaNormal = new ImageIcon(urlNormal).getImage();
                imgPulgaMutante = new ImageIcon(urlMutante).getImage();
            } else {
                imgPulgaNormal = crearImagenGenerica(20, 20, Color.RED);
                imgPulgaMutante = crearImagenGenerica(25, 25, Color.GREEN);
                System.out.println("URL normal: " + urlNormal);
                System.out.println("URL mutante: " + urlMutante);

            }
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes: " + e.getMessage());
            imgPulgaNormal = crearImagenGenerica(20, 20, Color.RED);
            imgPulgaMutante = crearImagenGenerica(25, 25, Color.GREEN);
        }
    }

    /**
     * Crea una imagen ovalada genérica del color y tamaño especificados.
     */
    private Image crearImagenGenerica(int ancho, int alto, Color color) {
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(color);
        g2d.fillOval(0, 0, ancho, alto);
        g2d.dispose();
        return img;
    }

    /**
     * Devuelve la imagen actual de la pulga normal.
     */
    public Image obtenerImagenPulgaNormal() {
        return imgPulgaNormal;
    }

    /**
     * Devuelve la imagen actual de la pulga mutante.
     */
    public Image obtenerImagenPulgaMutante() {
        return imgPulgaMutante;
    }

    /**
     * Dibuja el campo de batalla y las pulgas en pantalla.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Pulga pulga : pulgas) {
            pulga.dibujar(g);
        }
    }

    /**
     * Agrega una nueva pulga normal al campo, evitando colisiones con otras.
     */
    public void agregarPulgaNormal() {
        if (!juegoEnCurso) return;

        int x = (int) (Math.random() * (getWidth() - imgPulgaNormal.getWidth(null)));
        int y = (int) (Math.random() * (getHeight() - imgPulgaNormal.getHeight(null)));

        PulgaNormal nuevaPulga = new PulgaNormal(x, y, imgPulgaNormal);

        // Evitar que aparezca sobre otra pulga
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

    /**
     * Agrega una nueva pulga mutante al campo, evitando colisiones con otras.
     */
    public void agregarPulgaMutante() {
        if (!juegoEnCurso) return;

        int x = (int) (Math.random() * (getWidth() - imgPulgaMutante.getWidth(null)));
        int y = (int) (Math.random() * (getHeight() - imgPulgaMutante.getHeight(null)));

        PulgaMutante nuevaPulga = new PulgaMutante(x, y, imgPulgaMutante);

        // Evitar que aparezca sobre otra pulga
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

    /**
     * Hace que todas las pulgas salten a una nueva posición aleatoria.
     */
    public void saltarPulgas() {
        if (!juegoEnCurso) return;

        for (Pulga pulga : pulgas) {
            pulga.saltar(getWidth(), getHeight());
        }
        repaint();
    }

    /**
     * Dispara el misil Pulgoson, eliminando aleatoriamente la mitad de las pulgas.
     */
    public void dispararMisil() {
        if (!juegoEnCurso) return;

        misil.atacar(pulgas, 0, 0, gestorPuntaje, imgPulgaNormal);
        if (!pulgas.isEmpty()) {
            juegoIniciado = true;
        }
        repaint();
    }

    /**
     * Verifica si el juego ha terminado (todas las pulgas eliminadas).
     */
    public boolean juegoTerminado() {
        return juegoEnCurso && juegoIniciado && pulgas.isEmpty();
    }

    /**
     * Reinicia el juego limpiando el campo de batalla.
     */
    public void reiniciarJuego() {
        pulgas.clear();
        juegoEnCurso = true;
        juegoIniciado = false;
        repaint();
    }
}
