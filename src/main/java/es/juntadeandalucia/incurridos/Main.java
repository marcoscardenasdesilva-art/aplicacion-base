package es.juntadeandalucia.incurridos;


import es.juntadeandalucia.incurridos.model.LineaIncurrido;
import es.juntadeandalucia.incurridos.service.ValidadorIncurridos;
import es.juntadeandalucia.incurridos.util.CsvReader;
import es.juntadeandalucia.incurridos.util.CsvWriterErrores;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            CsvReader reader = new CsvReader();
            ValidadorIncurridos validador = new ValidadorIncurridos();
            CsvWriterErrores writer = new CsvWriterErrores();

            List<LineaIncurrido> lineas =
                    reader.leerIncurridos("cierre_ute_202603.csv");

            List<ValidadorIncurridos.ResultadoValidacion> errores =
                    new ArrayList<>();

            for (LineaIncurrido l : lineas) {
                ValidadorIncurridos.ResultadoValidacion r =
                        validador.validar(l);

                if (r != null) {
                    errores.add(r);
                }
            }

            writer.escribirErrores(errores);

            System.out.println("Proceso finalizado. Errores encontrados: " + errores.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}