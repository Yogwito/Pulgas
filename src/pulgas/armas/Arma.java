/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pulgas.armas;
import java.awt.Image;
import java.util.List;
import pulgas.models.GestorPuntaje;
import pulgas.models.Pulga;

/**
 *
 * @author juans
 */
interface Arma {
    int atacar(List<Pulga> pulgas, int x, int y, GestorPuntaje gestorPuntaje, Image imgPulgaNormal);
}