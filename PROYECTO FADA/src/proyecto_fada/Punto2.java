/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_fada;

import Archivos.ManejoArchivos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author neox
 */
public class Punto2 {
    
    public void solucionPunto2() {
        try {
            ManejoArchivos archivos = new ManejoArchivos();
            ArrayList<Rodal> arreglo = new ArrayList();
            JOptionPane.showMessageDialog(null, "Seleccione el archivo de entrada");
            arreglo = archivos.leerArchivosUnoYDos();
            long TInicio, TFin, tiempoFinal;
            TInicio = System.currentTimeMillis();
            MergeSort merge = new MergeSort();
            Rodal[] rodales = arreglo.toArray(new Rodal[arreglo.size()]);
            merge.mergeSort(rodales);
            ArrayList<Rodal> solucion = new ArrayList<Rodal>();
            int fechaAcomparar = rodales[0].getFechaFin();
            for (int i = 0; i < rodales.length; i++) {
                if (i == 0) {
                    solucion.add(rodales[i]);
                } else {
                    if (rodales[i].getFechaInicio() > fechaAcomparar) {
                        solucion.add(rodales[i]);
                        fechaAcomparar = rodales[i].getFechaFin();
                    }
                }
            }

            Rodal[] solucionRodales = solucion.toArray(new Rodal[solucion.size()]);
            TFin = System.currentTimeMillis();
            tiempoFinal = TFin - TInicio;
            JOptionPane.showMessageDialog(null, "Seleccione la ruta y el nombre del Archivo.txt de Salida");
            archivos.escribirArchivo(solucionRodales);
            JOptionPane.showMessageDialog(null, "El tiempo que tomo la ejecucion del punto2 fue de: " + tiempoFinal + " milisegundos.");
            System.out.println("Tiempo de Ejecucion " + tiempoFinal);
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }
}
