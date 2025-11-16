package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainD {

    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO("FICHE.DAT");
        ArrayList<Empleado> mejores = new ArrayList<Empleado>();
        double maxSueldo = Double.NEGATIVE_INFINITY;

        try (DataInputStream in = dao.openForRead()) {
            Empleado e;
            while ((e = dao.readNext(in)) != null) {
                double sueldo = calcularSueldo(e);
                if (sueldo > maxSueldo) {
                    maxSueldo = sueldo;
                    mejores.clear();
                    mejores.add(e);
                } else if (sueldo == maxSueldo) {
                    mejores.add(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        if (mejores.isEmpty()) {
            System.out.println("No hay empleados en el fichero.");
        } else {
            for (Empleado e : mejores) {
                System.out.println(e.getNombre() + " -> " + String.format("%.2f", maxSueldo) + " €");
            }
        }
    }

    private static double calcularSueldo(Empleado e) {
        double sueldo = e.getSalario();
        sueldo += e.getTrienios() * 24.0;
        byte p = e.getProvincia();
        if (p == 1 || p == 5 || p == 6) {
            sueldo += e.getSalario() * 0.10;
        }
        if (e.getSexo() == 'H') {
            sueldo += 120.0;
        }
        return sueldo;
    }
}
