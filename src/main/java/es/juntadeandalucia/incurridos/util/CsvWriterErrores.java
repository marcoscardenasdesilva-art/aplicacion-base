package es.juntadeandalucia.incurridos.util;

import es.juntadeandalucia.incurridos.service.ValidadorIncurridos.ResultadoValidacion;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvWriterErrores {

    public void escribirErrores(List<ResultadoValidacion> errores) throws Exception {

        Path carpeta = Path.of("output");
        Files.createDirectories(carpeta);

        Path fichero = carpeta.resolve("errores_incurridos.csv");

        BufferedWriter bw = Files.newBufferedWriter(fichero);
        bw.write("ID_PETICION,SUMA_HORAS_CORRECTAS,IMPORTE_CORRECTO");
        bw.newLine();

        for (ResultadoValidacion r : errores) {
            bw.write(r.id + "," + r.sumaHorasCorrecta + "," + r.importeCorrecto);
            bw.newLine();
        }

        bw.close();
    }
}