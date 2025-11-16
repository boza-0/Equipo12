package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.DataInputStream;
import java.io.IOException;

public class MainC {
    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO("FICHE.DAT");
        try (DataInputStream in = dao.openForRead()) {
            Empleado e;
            while ((e = dao.readNext(in)) != null) {
                double sueldo = calcularSueldo(e);
                System.out.printf("%s -> %.2f€%n", e.getNombre(), sueldo);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    // SUELDO = SALARIO + COMPLEMENTOS
    // - Trienios: 24€ por trienio
    // - Destino: Jaén(6), Huelva(5), Almería(1) +10% del salario
    // - Por la cara: H +120€
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
