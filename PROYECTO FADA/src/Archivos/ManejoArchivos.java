/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template arch, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import proyecto_fada.Rodal;

/**
 *
 * @author neox
 */
public class ManejoArchivos {
    
    private int umbralEmpleados;
    private int umbralArboles;
    private int umbralTresYCuatro;
    
    public String FechaCompletaString(String fechaString){
        StringTokenizer stk = new StringTokenizer(fechaString,"-");
        String ano = stk.nextToken();
        String mes = stk.nextToken();
        String dia = stk.nextToken();
        String fechaFin = ano + mes + dia;
        
        return fechaFin;
    }
    
    public ArrayList<Rodal> leerArchivosUnoYDos() {

        ArrayList<Rodal> arregloRodales = new ArrayList<>();

        try {
            JFileChooser archivo = new JFileChooser();
            archivo.showOpenDialog(null);
            File arch = archivo.getSelectedFile();
            FileReader frLector = new FileReader(arch);
            BufferedReader brLector = new BufferedReader(frLector);

            String linea = null;
            int contadorLineas = 0;
            int tamañoArreglo = Integer.parseInt(linea = brLector.readLine());
            while ((linea = brLector.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(linea);
                Rodal rodalx = new Rodal(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(stk.nextToken()));
                arregloRodales.add(rodalx);
            }

        }
        catch (IOException e){
            
        }
        
        for (int i = 0; i < arregloRodales.size(); i++) {
            System.out.println(arregloRodales.get(i).getFechaFin());
            
        }
        return arregloRodales;
    }
    
    
    
    public ArrayList<Rodal> leerArchivosTresYCuatro() {

        ArrayList<Rodal> arregloRodales = new ArrayList<>();

        try {
            JFileChooser archivo = new JFileChooser();
            archivo.showOpenDialog(null);
            File arch = archivo.getSelectedFile();
            FileReader frLector = new FileReader(arch);
            BufferedReader brLector = new BufferedReader(frLector);

            String linea = null;
            int contadorLineas = 0;
            int tamañoArreglo = Integer.parseInt(linea = brLector.readLine());
            umbralTresYCuatro = Integer.parseInt((linea = brLector.readLine()));
            while ((linea = brLector.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(linea);
                Rodal rodalx = new Rodal(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(stk.nextToken()));
                arregloRodales.add(rodalx);
            }

        }
        catch (IOException e){
            
        }
        
        for (int i = 0; i < arregloRodales.size(); i++) {
            System.out.println(arregloRodales.get(i).getFechaFin());
            
        }
        return arregloRodales;
    }
    
    public ArrayList<Rodal> leerArchivosCinco() {

        ArrayList<Rodal> arregloRodales = new ArrayList<>();

        try {
            JFileChooser archivo = new JFileChooser();
            archivo.showOpenDialog(null);
            File arch = archivo.getSelectedFile();
            FileReader frLector = new FileReader(arch);
            BufferedReader brLector = new BufferedReader(frLector);

            String linea = null;
            int contadorLineas = 0;
            int tamañoArreglo = Integer.parseInt(linea = brLector.readLine());
            umbralArboles = Integer.parseInt((linea = brLector.readLine()));
            umbralEmpleados = Integer.parseInt((linea = brLector.readLine()));
            while ((linea = brLector.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(linea);
                Rodal rodalx = new Rodal(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(FechaCompletaString(stk.nextToken())), Integer.parseInt(stk.nextToken()));
                arregloRodales.add(rodalx);
            }

        }
        catch (IOException e){
            
        }
        System.out.println(getUmbralArboles());
        System.out.println(getUmbralEmpleados());
        
        for (int i = 0; i < arregloRodales.size(); i++) {
            System.out.println(arregloRodales.get(i).getFechaFin());
            
        }
        return arregloRodales;
    }
    
    public void escribirArchivo(Rodal[] r) {

        FileWriter arch = null;
        PrintWriter pw = null;
        try {
            JFileChooser archivo = new JFileChooser();
            archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
            archivo.showSaveDialog(null);
            File rutaArchivo = archivo.getSelectedFile();
            arch = new FileWriter(rutaArchivo, true);
            pw = new PrintWriter(arch);
            pw.println(r.length);

            for (int i = 0; i < r.length; i++) {
                pw.println(r[i].getIdRodal());
            }
        } catch (Exception e) {

        } finally {
            if (null != pw) {
                pw.close();
            }
        }
    }

    public int getUmbralEmpleados() {
        return umbralEmpleados;
    }

    public int getUmbralArboles() {
        return umbralArboles;
    }

    public int getUmbralTresYCuatro() {
        return umbralTresYCuatro;
    }
    
    
}
