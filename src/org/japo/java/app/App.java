/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.app;

import java.util.Properties;
import org.japo.java.libraries.UtilesApp;

/**
 *
 * @author Victor Hugo Polo Polo - victorpolodev@gmail.com
 */
public class App {

    //Nombre propiedades
    public static final String PRP_NOMBRE = "alumno";
    public static final String PRP_EXP = "expediente";
    public static final String PRP_CICLO = "ciclo";
    public static final String PRP_CURSO = "curso";
    public static final String PRP_MODULO = "modulo";
    public static final String PRP_EV1 = "ev1";
    public static final String PRP_EV2 = "ev2";
    public static final String PRP_EV3 = "ev3";

    //String de formato
    public static final String MODULO = "Modulo";
    public static final String PEV = "1EV";
    public static final String SEV = "2EV";
    public static final String TEV = "3EV";
    public static final String FINAL = "FINAL";

    public void launchApp() {
        //Importar Propiedades
        Properties prp = UtilesApp.importarPropiedades();
        String nombre = prp.getProperty(PRP_NOMBRE);
        String exp = prp.getProperty(PRP_EXP);
        String ciclo = prp.getProperty(PRP_CICLO);
        String curso = prp.getProperty(PRP_CURSO);

        //Generar Arrays Modulos
        String[] modulo = prp.getProperty(PRP_MODULO).split("-");

        //Generar Arrays Notas
        String[] notas1 = prp.getProperty(PRP_EV1).split("-");
        String[] notas2 = prp.getProperty(PRP_EV2).split("-");
        String[] notas3 = prp.getProperty(PRP_EV3).split("-");

        //Calcular Medias
        double[] notasFinal = new double[modulo.length];
        for (int i = 0; i < notasFinal.length; i++) {
            notasFinal[i] = 0.0;
        }
        String[] notasText = new String[modulo.length];
        for (int i = 0; i < notasText.length; i++) {
            notasText[i] = "";
        }
        int asignaturasSuspendidas = 0;
        for (int i = 0; i < notasText.length; i++) {
            notasFinal[i] = (Double.parseDouble(notas1[i]) + Double.parseDouble(notas2[i]) + Double.parseDouble(notas3[i])) / 3.0;
            if (notasFinal[i] >= 5) {
                notasText[i] = " - APROBADO";
            } else {
                notasText[i] = " - SUSPENDIDO";
                asignaturasSuspendidas++;
            }
        }

        //Salida a Pantalla
        System.out.println("INFORME CURRICULAR - Ciclo: " + ciclo + " - CURSO: " + curso);
        System.out.println("==================================================");
        System.out.println("Alumno .....: " + nombre);
        System.out.println("Expediente .: " + exp);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12S %-6S %-6S %-6S %-6S%n", MODULO, PEV, SEV, TEV, FINAL);
        System.out.println("------------ ------ ------ ------ ------");
        for (int i = 0; i < modulo.length; i++) {
            System.out.printf("%-12s %-6s %-6s %-6s %5.1f %s%n", modulo[i], notas1[i], notas2[i], notas3[i], notasFinal[i], notasText[i]);
        }
        System.out.println("---");
        System.out.println("Atención a los padres:");
        if (asignaturasSuspendidas > 1) {
            System.out.printf("Su hijo/a tiene %d modulos suspendidos %n", asignaturasSuspendidas);
        } else if (asignaturasSuspendidas == 1) {
            System.out.printf("Su hijo/a tiene %d modulo suspendido %n", asignaturasSuspendidas);
        } else {
            System.out.println("Su hijo ha aprobado todos los modulos");
        }

    }
}
