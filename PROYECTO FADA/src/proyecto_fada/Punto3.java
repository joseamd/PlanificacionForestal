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
public class Punto3 {
    
    //Declaracion de Variables
    public ArrayList<Rodal> Entrada_Rodales = new ArrayList<>();
    public ArrayList<Rodal> NumeroArbolesMaximo = new ArrayList<>();
    public ArrayList<Rodal> AlmacenamientoArbolesTemporal = new ArrayList<>();
    public ArrayList<Rodal> Arreglo_Soluciones_Temporales = new ArrayList<>();
    int maximoNumeroArboles = 0;
    int NumeroArbolesTemporal = 0;
    int CostoTemporal = 0;
    int CostoAlmacenado = 0;
    int Umbral_a_Evaluar = 0;
    Rodal Rodal_Partida = new Rodal();
    
    /**
     * Evalua entre iteraciones que el costo total almacenado no supere el umbral al momento de adicionar el rodal que no
     * se solapa
     * @param valorAcomparar 
     */
    private void Sumar(Rodal valorAcomparar) {
        NumeroArbolesTemporal += valorAcomparar.getNumerArboles();
        CostoTemporal += valorAcomparar.getCosto();
        if (CostoTemporal <= Umbral_a_Evaluar) {
            AlmacenamientoArbolesTemporal.add(valorAcomparar);
        } else {
            CostoTemporal -= valorAcomparar.getCosto();
            NumeroArbolesTemporal -= valorAcomparar.getNumerArboles();
        }
    }
    
    /**
     * Los resultados que son almacenados por los metodos CalcularRodalesUp y CalcularRodalesDown son almacenados
     * y comparados en cada una de las iteraciones, evaluando si el numero de arboles de la iteracion
     * anterior es mayor a la de la actual. Tambien, evalua que el costo acomulado no supere el umbral definido.
     */
    public void Comparar() {
        if (NumeroArbolesTemporal > maximoNumeroArboles && CostoTemporal > CostoAlmacenado) {
            maximoNumeroArboles = NumeroArbolesTemporal;
            CostoAlmacenado = CostoTemporal;
            try {
                NumeroArbolesMaximo = (ArrayList<Rodal>) AlmacenamientoArbolesTemporal.clone();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        NumeroArbolesTemporal = 0;
        CostoTemporal = 0;
        AlmacenamientoArbolesTemporal.clear();
    }
    
    /**
     * Toma el Rodal que se encuentren la posicion "partida" del arreglo y almacena en dos variables, 
 el costo total y el numero total de arboles que seran talados en todos los rodales que esten      * delante de el y no se solapen
     * @param partida
     * @return 
     */
    public ArrayList<Rodal> CalcularRodalesUp(int partida) {
        int punto = partida;
        Rodal_Partida = Entrada_Rodales.get(punto);
        Arreglo_Soluciones_Temporales.add(Rodal_Partida);
        Sumar(Rodal_Partida);
        for (int i = punto; i < Entrada_Rodales.size(); i++) {
            int fechaFinAcomprar = Arreglo_Soluciones_Temporales.get(Arreglo_Soluciones_Temporales.size() - 1).getFechaFin();
            int fechaInicioAcomparar = Entrada_Rodales.get(i).getFechaInicio();

            if (fechaInicioAcomparar > fechaFinAcomprar) {
                Arreglo_Soluciones_Temporales.add(Entrada_Rodales.get(i));
                Sumar(Entrada_Rodales.get(i));
                
            }
        }
        return Arreglo_Soluciones_Temporales;
    }
    
    /**
     * Toma el Rodal que se encuentren la posicion "partida - 1" del arreglo y almacena en dos variables 
     * el costo total y el numero total de arboles que seran talados por todos los rodales que esten 
     * atras de el y no se solapen
     * @param partida 
     */
    public void CalcularRodalesDown(int partida) {
        int punto = partida - 1;
        int contador = 1;
        for (int i = punto; i > 0; i--) {
            int fechaInicioAcomparar = Entrada_Rodales.get(Entrada_Rodales.size() - contador).getFechaInicio();
            int fechaFinAcomparar = Entrada_Rodales.get(i).getFechaFin();
            if (fechaFinAcomparar < fechaInicioAcomparar) {
                if (!AlmacenamientoArbolesTemporal.contains(Entrada_Rodales.get(i))) {
                    Sumar(Entrada_Rodales.get(i));
                }
                contador++;
            }
        }
    }
    
    /**
     * Realiza la lectura lectura de archivos
     * pasa el algoritmo de ordenamiento
     * llama a los metodos CalcularRodalesDown y CalcularRodalesUp y comparar
     * para cada uno de los elementos.
     */
    public void solucionPunto3() {
        
        try {
            ManejoArchivos archivos = new ManejoArchivos();
            MergeSort merge = new MergeSort();
            JOptionPane.showMessageDialog(null, "Seleccione el archivo de entrada");
            Entrada_Rodales = archivos.leerArchivosTresYCuatro();
            Umbral_a_Evaluar = archivos.getUmbralTresYCuatro();
            Rodal[] rodalesEntrada = Entrada_Rodales.toArray(new Rodal[Entrada_Rodales.size()]);
            merge.mergeSort(rodalesEntrada);
            long TInicio, TFin, tiempoFinal;
            TInicio = System.currentTimeMillis();
            for (int i = 0; i < rodalesEntrada.length; i++) {
                Arreglo_Soluciones_Temporales.clear();
                CalcularRodalesUp(i); //Calculo es CostoAlmacenado de tiempo del algoritmo// 
                CalcularRodalesDown(i);
                Comparar();
            }
            
            Rodal[] rodaslFinal = NumeroArbolesMaximo.toArray(new Rodal[NumeroArbolesMaximo.size()]);
            TFin = System.currentTimeMillis();
            tiempoFinal = TFin - TInicio;
            JOptionPane.showMessageDialog(null, "Seleccione la ruta y el nombre del Archivo.txt de Salida");
            archivos.escribirArchivo(rodaslFinal);
            JOptionPane.showMessageDialog(null, "El tiempo que tomo la ejecucion del punto3 fue de: " + tiempoFinal + " milisegundos.");
        } catch (Exception ex) {            
            System.out.println("error");
        }   
    }
}
