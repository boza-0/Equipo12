package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainB {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int veteranos = 0;
        int pageSize = 5;
        int printed = 0;

        EmpleadoDAO dao = new EmpleadoDAO("FICHE.DAT");

        try (DataInputStream in = dao.openForRead()) {
            Empleado e;
            while ((e = dao.readNext(in)) != null) {
                total++;
                if (e.getAntiguedad() >= 10) {
                    veteranos++;
                    // Show a succinct line per employee
                    System.out.printf("%s; %c; %.2f; %d/%02d/%02d; %c; %d%n",
                            e.getNombre(), e.getSexo(), e.getSalario(),
                            e.getAnio(), e.getMes(), e.getDia(),
                            e.getTipo(), e.getProvincia());
                    printed++;
                    if (printed % pageSize == 0) {
                        System.out.print("Pulse Enter para continuar...");
                        sc.nextLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        if (total > 0) {
            double porcentaje = (veteranos * 100.0) / total;
            System.out.println("Total empleados: " + total);
            System.out.println("Número de empleados con 10+ años: " + veteranos);
            System.out.printf("Porcentaje de empleados con 10+ años: %.2f%%%n", porcentaje);
        } else {
            System.out.println("No hay empleados en el fichero.");
        }
    }
}
