/* 
 * Correcciones en CampoBatalla.java
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
 * Clase que representa el campo de batalla donde interactúan las pulgas y las armas.
 * Gestiona el dibujo, las interacciones del jugador y la lógica de juego.
 * Implementa la interfaz SpriteContainer para gestionar todos los sprites del juego.
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
    // Contenedor de sprites opcional para futura expansión
    private SpriteContainer contenedorSprites;

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
        contenedorSprites = new SpriteContainer();

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
    private Image fondo;

    private void cargarImagenes() {
        try {
            URL urlFondo = getClass().getResource("/recursos/fondo.png");
            URL urlNormal = getClass().getResource("/recursos/pulga_normal.png");
            URL urlMutante = getClass().getResource("/recursos/pulga_mutante.png");

            if (urlFondo != null) fondo = new ImageIcon(urlFondo).getImage();
            if (urlNormal != null) imgPulgaNormal = new ImageIcon(urlNormal).getImage();
            if (urlMutante != null) imgPulgaMutante = new ImageIcon(urlMutante).getImage();

            if (fondo == null) fondo = crearImagenGenerica(getWidth(), getHeight(), Color.LIGHT_GRAY);
            if (imgPulgaNormal == null) imgPulgaNormal = crearImagenGenerica(20, 20, Color.RED);
            if (imgPulgaMutante == null) imgPulgaMutante = crearImagenGenerica(25, 25, Color.GREEN);
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        for (Pulga pulga : pulgas) {
            pulga.paint(g);
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
     * Agrega una nueva pulga normal al campo, evitando colisiones con otras.
     */
    public void agregarPulgaNormal() {
        if (!juegoEnCurso) return;

        int x = (int) (Math.random() * (getWidth() - 20)); // Evitar usar getWidth(null) de la imagen
        int y = (int) (Math.random() * (getHeight() - 20));

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
            // También podríamos añadirla al contenedor de sprites
            // contenedorSprites.addSprite(nuevaPulga);
            juegoIniciado = true;
            repaint();
        }
    }

    /**
     * Agrega una nueva pulga mutante al campo, evitando colisiones con otras.
     */
    public void agregarPulgaMutante() {
        if (!juegoEnCurso) return;

        int x = (int) (Math.random() * (getWidth() - 25)); // Evitar usar getWidth(null) de la imagen
        int y = (int) (Math.random() * (getHeight() - 25));

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
            // También podríamos añadirla al contenedor de sprites
            // contenedorSprites.addSprite(nuevaPulga);
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
        // También limpiar el contenedor de sprites si se está usando
        // contenedorSprites = new SpriteContainer();
        juegoEnCurso = true;
        juegoIniciado = false;
        repaint();
    }
}


