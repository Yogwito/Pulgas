/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.armas;

/**
 *
 * @author juans
 *
 *
 * Interfaz para las armas del juego
 */
interface Arma {
    int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal);
}