/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_fada;

import Archivos.ManejoArchivos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author neox
 */
public class Punto1 {
    
    public int[] tabla;
    public int cantidad_arboles;
    public ArrayList<Rodal> rodalesAevaluar;
    public ArrayList<Rodal> rodalesSeleccionados;
    private int numeroArboles;

    public Punto1() {
        numeroArboles = 0;
    }

    
    
    //nonConflict
    public int siguienteRodalValido(ArrayList<Rodal> rodales, int posicionActal) {
        int posicion = -1;

        for (int i = posicionActal; i >= 0; i--) {
            if (rodales.get(i).getFechaFin()<= rodales.get(posicionActal).getFechaInicio()) 
            {
                posicion = i;
                return posicion;
            }
        }
        return posicion;
    }
    
    
    //FindMonitors
    public void buscarRodales(ArrayList<Rodal> rodales, int n) {
        tabla[0] = rodales.get(0).getNumerArboles();
        for (int i = 1; i < n; i++) {
            int numeroArbolesInicial = rodales.get(i).getNumerArboles();
            int siguienteRodal = siguienteRodalValido(rodales, i);
            //System.out.println(monitorNC);
            if (siguienteRodal != -1) {
                numeroArbolesInicial += tabla[siguienteRodal];
            }
            if (numeroArbolesInicial > tabla[i - 1]) {
                tabla[i] = numeroArbolesInicial;
            } 
            else {
                tabla[i] = tabla[i - 1];
            }
        }
    }
    
        //Retorna la posicion del elemento mayor evaluando posiciones inferiores a el.
    public int findMayorMenorPosicion(int actual, int posicionActual) {
        int pos = posicionActual;
        for (int i = posicionActual; i >= 0; i--) {
            if (i == 0 && tabla[i] == actual) {
                pos = 0;
                return pos;
            } else {
                if (tabla[i] < actual) {
                    pos = i + 1;
                    return pos;
                }
            }
        }
        return pos;
    }
    
 
    //Acordarse que hAce!
    public void RodalesSeleccionados() {
        int i = tabla.length - 1;
        int actualAcomparar = tabla[i];
        int rodal = 0;
        rodalesSeleccionados = new ArrayList<Rodal>();

        while (i >= 0 && actualAcomparar > 0) {
            rodal = findMayorMenorPosicion(actualAcomparar, i);
            rodalesSeleccionados.add(rodalesAevaluar.get(rodal));
            numeroArboles += rodalesAevaluar.get(rodal).getNumerArboles();
            actualAcomparar = actualAcomparar - rodalesAevaluar.get(rodal).getNumerArboles();
            i = rodal - 1;

        }
    }

    public void solucionPunto1()
    {
        try {
            ManejoArchivos archivos = new ManejoArchivos();
            rodalesAevaluar = new ArrayList<Rodal>();
            JOptionPane.showMessageDialog(null, "Seleccione el archivo de entrada");
            rodalesAevaluar = archivos.leerArchivosUnoYDos();
            long TInicio, TFin, tiempoFinal;
            TInicio = System.currentTimeMillis();
            MergeSort merge = new MergeSort();
            Rodal[] rodales = rodalesAevaluar.toArray(new Rodal[rodalesAevaluar.size()]);
            merge.mergeSort(rodales);
            tabla = new int[rodales.length];
            buscarRodales(rodalesAevaluar, rodales.length);
            RodalesSeleccionados();
            Rodal[] rodalesAseleccionar = rodalesSeleccionados.toArray(new Rodal[rodalesSeleccionados.size()]);
            TFin = System.currentTimeMillis();
            tiempoFinal = TFin - TInicio;
            JOptionPane.showMessageDialog(null, "Seleccione la ruta y el nombre del Archivo.txt de Salida");
            archivos.escribirArchivo(rodalesAseleccionar);
            JOptionPane.showMessageDialog(null, "El tiempo que tomo la ejecucion del punto1 fue de: " + tiempoFinal + " milisegundos.");
            System.out.println("Tiempo de Ejecucion " + tiempoFinal);
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }
}


