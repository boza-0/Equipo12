package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.*;

public class EmpleadoDAO {

    private final String fichero;

    public EmpleadoDAO(String fichero) {
        this.fichero = fichero;
    }

    // Append one employee to the sequential binary file
    public void addEmpleado(Empleado e) throws IOException {
        // Append mode for sequential file
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(fichero, true)))) {
            e.writeRecord(out);
        }
    }

    // Open DataInputStream for sequential reading
    public DataInputStream openForRead() throws IOException {
        return new DataInputStream(new BufferedInputStream(new FileInputStream(fichero)));
    }

    // Read next record; return null on EOF
    public Empleado readNext(DataInputStream in) throws IOException {
        try {
            return Empleado.readRecord(in);
        } catch (EOFException eof) {
            return null;
        }
    }
}
