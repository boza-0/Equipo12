package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.IOException;
import java.util.Scanner;

public class MainA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpleadoDAO dao = new EmpleadoDAO("FICHE.DAT");

        try {
            boolean continuar = true;
            while (continuar) {

                String nombre;
                while (true) {
                    System.out.print("Nombre y apellidos (máx 30 caracteres): ");
                    nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        continue;
                    }
                    if (nombre.length() > 30) {
                        System.out.println("El nombre es demasiado largo. Inténtelo de nuevo.");
                        continue;
                    }
                    break;
                }

                char sexo;
                while (true) {
                    System.out.print("Sexo (M/H): ");
                    String input = sc.nextLine().trim().toUpperCase();
                    if (input.length() != 1) {
                        System.out.println("Debe introducir solo un carácter (M o H).");
                        continue;
                    }
                    sexo = input.charAt(0);
                    if (sexo == 'M' || sexo == 'H') break;
                    System.out.println("(M(mujer) o H(hombre)). Inténtelo de nuevo.");
                }

                float salario;
                while (true) {
                    System.out.print("Salario (0 - 99999.99): ");
                    try {
                        salario = Float.parseFloat(sc.nextLine());
                        if (salario >= 0 && salario <= 99999.99f) break;
                        System.out.println("El salario debe estar entre 0 y 99999.99.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Introduzca un número.");
                    }
                }

                short anio;
                while (true) {
                    System.out.print("Año de ingreso (1900 - 2100): ");
                    try {
                        anio = Short.parseShort(sc.nextLine());
                        if (anio >= 1900 && anio <= 2100) break;
                        System.out.println("El año debe estar entre 1900 y 2100.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Introduzca un número entero.");
                    }
                }

                byte mes;
                while (true) {
                    System.out.print("Mes de ingreso (1 - 12): ");
                    try {
                        mes = Byte.parseByte(sc.nextLine());
                        if (mes >= 1 && mes <= 12) break;
                        System.out.println("El mes debe estar entre 1 y 12.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Introduzca un número entero.");
                    }
                }

                byte dia;
                while (true) {
                    System.out.print("Día de ingreso (1 - 31): ");
                    try {
                        dia = Byte.parseByte(sc.nextLine());
                        if (dia >= 1 && dia <= 31) break;
                        System.out.println("El día debe estar entre 1 y 31.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Introduzca un número entero.");
                    }
                }

                char tipo;
                while (true) {
                    System.out.print("Tipo de empleado (C/F/D): ");
                    String input = sc.nextLine().trim().toUpperCase();
                    if (input.length() != 1) {
                        System.out.println("Debe introducir solo un carácter (C, F o D).");
                        continue;
                    }
                    tipo = input.charAt(0);
                    if (tipo == 'C' || tipo == 'F' || tipo == 'D') break;
                    System.out.println("Tipo inválido. Inténtelo de nuevo.");
                }

                byte provincia;
                while (true) {
                    System.out.print("Provincia (1-8): ");
                    try {
                        provincia = Byte.parseByte(sc.nextLine());
                        if (provincia >= 1 && provincia <= 8) break;
                        System.out.println("Provincia inválida. Inténtelo de nuevo.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Introduzca un número entero.");
                    }
                }

                Empleado e = new Empleado(nombre, sexo, salario, anio, mes, dia, tipo, provincia);
                dao.addEmpleado(e);

                System.out.print("¿Desea añadir otro empleado? (s/n): ");
                String resp = sc.nextLine().trim().toLowerCase();
                char respuesta = resp.isEmpty() ? 'n' : resp.charAt(0);
                if (respuesta != 's') continuar = false;
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
