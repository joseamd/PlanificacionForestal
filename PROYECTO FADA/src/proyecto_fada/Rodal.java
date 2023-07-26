/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_fada;

/**
 *
 * @author neox
 */
public class Rodal {
    private int idRodal;
    private int numerArboles;
    private int costo;
    private int fechaInicio;
    private int fechaFin;
    private int numeroEmpleados;

    public Rodal(int idRodal, int numerArboles, int costo, int fechaInicio, int fechaFin, int numeroEmpleados) {
        this.idRodal = idRodal;
        this.numerArboles = numerArboles;
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroEmpleados = numeroEmpleados;
    }
    
    public Rodal(){
    }

    public int getIdRodal() {
        return idRodal;
    }

    public int getNumerArboles() {
        return numerArboles;
    }

    public int getCosto() {
        return costo;
    }

    public int getFechaInicio() {
        return fechaInicio;
    }

    public int getFechaFin() {
        return fechaFin;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setIdRodal(int idRodal) {
        this.idRodal = idRodal;
    }

    public void setNumerArboles(int numerArboles) {
        this.numerArboles = numerArboles;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setFechaInicio(int fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(int fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }
    
    
}