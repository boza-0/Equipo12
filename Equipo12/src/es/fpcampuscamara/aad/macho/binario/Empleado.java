package es.fpcampuscamara.aad.macho.binario;

/* Equipo 12: Juan Luis Gil de Miguel, Ricardo Boza Villar */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

public class Empleado {

    private final String nombre;    // X(30)
    private final char sexo;        // 'M' / 'H'
    private final float salario;    // 0..99999.99
    private final short anio;       // 1900..2100
    private final byte mes;         // 1..12
    private final byte dia;         // 1..31
    private final char tipo;        // 'C' / 'F' / 'D'
    private final byte provincia;   // 1..8

    public Empleado(String nombre, char sexo, float salario,
                    short anio, byte mes, byte dia, char tipo, byte provincia) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.salario = salario;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.tipo = tipo;
        this.provincia = provincia;
    }

    public String getNombre() { return nombre; }
    public char getSexo() { return sexo; }
    public float getSalario() { return salario; }
    public short getAnio() { return anio; }
    public byte getMes() { return mes; }
    public byte getDia() { return dia; }
    public char getTipo() { return tipo; }
    public byte getProvincia() { return provincia; }

    // Sequential binary write
    public void writeRecord(DataOutputStream out) throws IOException {
        out.writeUTF(nombre);
        out.writeChar(sexo);
        out.writeFloat(salario);
        out.writeShort(anio);
        out.writeByte(mes);
        out.writeByte(dia);
        out.writeChar(tipo);
        out.writeByte(provincia);
    }

    // Sequential binary read (in the same order as writeRecord)
    public static Empleado readRecord(DataInputStream in) throws IOException {
        String nombre = in.readUTF();
        char sexo = in.readChar();
        float salario = in.readFloat();
        short anio = in.readShort();
        byte mes = in.readByte();
        byte dia = in.readByte();
        char tipo = in.readChar();
        byte provincia = in.readByte();
        return new Empleado(nombre, sexo, salario, anio, mes, dia, tipo, provincia);
    }

    // Years of service (antigüedad)
    public byte getAntiguedad() {
        GregorianCalendar hoy = new GregorianCalendar();
        GregorianCalendar ingreso = new GregorianCalendar(anio, mes - 1, dia);
        int years = hoy.get(GregorianCalendar.YEAR) - ingreso.get(GregorianCalendar.YEAR);
        if (hoy.get(GregorianCalendar.DAY_OF_YEAR) < ingreso.get(GregorianCalendar.DAY_OF_YEAR)) {
            years--;
        }
        return (byte) (years > 0 ? years : 0);
    }

    // Trienios = antigüedad / 3
    public byte getTrienios() {
        byte antiguedad = getAntiguedad();
        return (byte) (antiguedad > 0 ? antiguedad / 3 : 0);
    }
}
