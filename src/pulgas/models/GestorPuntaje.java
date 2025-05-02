/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.models;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author juans
 */
/**
 * Gestor de puntaje del juego
 */
public class GestorPuntaje {
    private int puntajeActual;
    private int puntajeMaximo;
    private final String ARCHIVO_PUNTAJE = "puntaje_maximo.txt";
    
    public GestorPuntaje() {
        puntajeActual = 0;
        puntajeMaximo = 0;
    }
    
    public void cargarPuntajeMaximo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PUNTAJE))) {
            String linea = br.readLine();
            if (linea != null) {
                puntajeMaximo = Integer.parseInt(linea);
            }
        } catch (IOException | NumberFormatException e) {
            // Si hay error al leer, se mantiene el puntaje en 0
            puntajeMaximo = 0;
        }
    }
    
    public void guardarPuntajeMaximo() {
        if (puntajeActual > puntajeMaximo) {
            puntajeMaximo = puntajeActual;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PUNTAJE))) {
                bw.write(String.valueOf(puntajeMaximo));
            } catch (IOException e) {
                System.err.println("Error al guardar puntaje: " + e.getMessage());
            }
        }
    }
    
    public void aumentarPuntaje(int puntos) {
        puntajeActual += puntos;
        if (puntajeActual > puntajeMaximo) {
            puntajeMaximo = puntajeActual;
        }
    }
    
    public int getPuntajeActual() {
        return puntajeActual;
    }
    
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
    
    public void reiniciarPuntaje() {
        guardarPuntajeMaximo();
        puntajeActual = 0;
    }
}

