/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_fada;

import Archivos.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author neox
 */
public class Punto5 {
    int maximoNumeroArboles = 0;
    int tmpNumeroArboles = 0;
    int tmpNumeroEmpleados = 0;
    public ArrayList<Rodal> entrada = new ArrayList<>();
    public ArrayList<Rodal> MayorNumeroArboles = new ArrayList<>();
    public ArrayList<Rodal> mayorNumeroArbolesTemporal = new ArrayList<>();
    SimpleDateFormat cambio_Formato_Fecha = new SimpleDateFormat("dd/MM/yyyy");
    Rodal inicial_Rodal = new Rodal();
    public ArrayList<Rodal> Solucion = new ArrayList<>();
    public int umbralArboles;
    public int umbralEmpleados;

    /*Complejida O(n)
     Esta funcion se para en en un punto compara sino se solapa el rodal con los demas elementos del arreglo*/
    public ArrayList<Rodal> MaximoNumeroRodales(int inicio) {
        int punto = inicio;
        inicial_Rodal = entrada.get(punto);
        Solucion.add(inicial_Rodal);
        //Almaceno el valor de numero de arboles par saber quien es mayor
        Agregar(inicial_Rodal);
        //Este algoritmo resuel el problema del maximo numero de rodales permitido    
        for (int i = punto; i < entrada.size(); i++) {
            int date1 = Solucion.get(Solucion.size() - 1).getFechaFin();
            int date2 = entrada.get(i).getFechaInicio();
    //!date2.before(date1)
            if (date2 > date1) {
                Solucion.add(entrada.get(i));
                Agregar(entrada.get(i));
            }
        }
        return Solucion;
    }

    /*Complejida O(n)
     Esta funcion separa en un punto del arreglo que identifique com la varieable inicio y compara si no se solapa con todos los elementos anteriores 
     a el*/
    public void MaximoNumeroRodalesReverse(int inicio) {
        int punto = inicio - 1;
        //entrada.
        //Este algoritmo resuel el problema del maximo numero de rodales permitido
        int contador = 1;
        for (int i = punto; i > 0; i--) {
            int date1 = entrada.get(entrada.size() - contador).getFechaInicio();
            int date2 = entrada.get(i).getFechaFin();
            //date2.before(date1
            if ( date2<date1 ) {
                if (!mayorNumeroArbolesTemporal.contains(entrada.get(i))) {
                    Agregar(entrada.get(i));
                }
                contador++;
            }
        }
    }
//Complejidad O(1)
    //Realiza las comparacion para saber si esta operacion obtuvo la mayor ganancia en arboles
    //Comparo el valor que ya existe y el nuevo si el nuevo es mayor almaceno el nuevo. de lo contrario lo ignoro
    //Ademas agrego la condicion de el umbral establecido

    public void Probar() {
        if (tmpNumeroArboles > maximoNumeroArboles) {
            maximoNumeroArboles = tmpNumeroArboles;

            try {
                MayorNumeroArboles = (ArrayList<Rodal>) mayorNumeroArbolesTemporal.clone();
            } catch (Exception e) {
                JOptionPane.showInputDialog(this, "Si sale este Mensaja Algo anda Muy Mal ");
            }
        }
        tmpNumeroArboles = 0;
        tmpNumeroEmpleados = 0;
        mayorNumeroArbolesTemporal.clear();

    }
    /*Modificacion del 23 de febrero Mauro Castillo
     En esta funcion agrego las restricciones para los algoritmos
     En esta funcion agrego las restracciones para la captacion de Objetos la diferencia de esto al punto 3 Esque cambien la variable de costo por 
     numero de arboles*/

    /* Compejida O(n) */
    private void Agregar(Rodal entrada) {
        tmpNumeroArboles += entrada.getNumerArboles();
        tmpNumeroEmpleados += entrada.getNumeroEmpleados();
      /*Restriciones que contrala que no se pase del umbral establesido*/
        if (tmpNumeroArboles <= umbralArboles && tmpNumeroEmpleados <= umbralEmpleados) {
            mayorNumeroArbolesTemporal.add(entrada);
            
            
        System.out.println("umbralArbolestem " + tmpNumeroArboles);
        System.out.println("umbralEmpleadostem " + tmpNumeroEmpleados);
  
        } else {
            tmpNumeroArboles -= entrada.getNumerArboles();
            tmpNumeroEmpleados -= entrada.getNumeroEmpleados();
        }
    }
        
    public void solucionPunto5() {
        ManejoArchivos archivos = new ManejoArchivos();
        //Lectura lectura = new Lectura();
        MergeSort merge = new MergeSort();
        //Algoritmos ordenamieto = new Algoritmos();
        //Funcionalidades funcionalidades = new Funcionalidades();
        entrada = archivos.leerArchivosCinco();
        //ArrayList<Rodal> c = lectura.ReadFile();

//Ordeno el arreglo con el algoritmo mergeSort Complejida  O(nlogn)
        Rodal[] rodalesAevaluar = entrada.toArray(new Rodal[entrada.size()]);
        //ArrayList<Rodal> MergeOut = ordenamieto.mergeSort(c);
        //entrada = MergeOut;
        merge.mergeSort(rodalesAevaluar);
        /*En esta parte agregao los umbrales estraidos del archivo plano a la funcion*/
        umbralArboles = archivos.getUmbralArboles();
        umbralEmpleados = archivos.getUmbralEmpleados();
        System.out.println("Este es el valor de umbralArboles" + umbralArboles);
        System.out.println("Este es el valor de umbralEmpleados" + umbralEmpleados);
    
        /*La complejida Total de este ciclo es de O(n^2) porque al relizar la llamada a las funciones
         MaximoNumeroRodales(i); 
         MaximoNumeroRodalesReverse(i);
         Cada una de ellas Posee una Complejida de O(n)
         */
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        for (int i = 0; i < rodalesAevaluar.length; i++) {
            Solucion.clear();
            MaximoNumeroRodales(i); //Calculo es costo de tiempo del algoritmo// 
            MaximoNumeroRodalesReverse(i);
            Probar();
        }
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken " + (time_end - time_start) + " milliseconds");
        //ImprimirArchivo(MayorNumeroArboles);
        Rodal[] rodalsFinal = MayorNumeroArboles.toArray(new Rodal[MayorNumeroArboles.size()]);
        archivos.escribirArchivo(rodalsFinal);
    }
}
