package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.DataInputStream;
import java.io.IOException;

public class MainE {
    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO("FICHE.DAT");
        int[] contador = new int[9]; // indices 1..8

        try (DataInputStream in = dao.openForRead()) {
            Empleado e;
            while ((e = dao.readNext(in)) != null) {
                byte p = e.getProvincia();
                if (p >= 1 && p <= 8) {
                    contador[p]++;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        int max = 0;
        for (byte i = 1; i <= 8; i++) {
            if (contador[i] > max) max = contador[i];
        }

        if (max == 0) {
            System.out.println("No hay empleados en el fichero.");
            return;
        }

        for (byte i = 1; i <= 8; i++) {
            if (contador[i] == max) {
                System.out.println(nombreProvincia(i) + " -> " + max + " empleados");
            }
        }
    }

    private static String nombreProvincia(byte codigo) {
        switch (codigo) {
            case 1: return "Almería";
            case 2: return "Cádiz";
            case 3: return "Córdoba";
            case 4: return "Granada";
            case 5: return "Huelva";
            case 6: return "Jaén";
            case 7: return "Málaga";
            case 8: return "Sevilla";
            default: return "Desconocida";
        }
    }
}
