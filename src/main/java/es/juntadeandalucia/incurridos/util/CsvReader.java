package es.juntadeandalucia.incurridos.util;


import es.juntadeandalucia.incurridos.model.LineaIncurrido;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<LineaIncurrido> leerIncurridos(String cierre_ute_202603) throws Exception {

        List<LineaIncurrido> lineas = new ArrayList<>();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(cierre_ute_202603),
                        StandardCharsets.ISO_8859_1
                )
        );

        String linea;
        br.readLine(); // Cabecera

        while ((linea = br.readLine()) != null) {
            String[] c = linea.split(";");

            if (c.length < 20) {
                continue;
            }

            String id = c[2].replace("\"", "").trim();          // ID PETICION
            double dp = parse(c[14]);         // Horas JP Eje.
            double co = parse(c[15]);         // Horas CO Eje.
            double an = parse(c[16]);         // Horas AN Eje.
            double pr = parse(c[17]);         // Horas PR Eje.
            double totalHoras = parse(c[18]); // Total Horas Ejecutadas
            double totalImporte = parse(c[19]); // Importe inicial Incurridos

            lineas.add(new LineaIncurrido(
                    id, dp, co, an, pr, totalHoras, totalImporte
            ));
        }

        br.close();
        return lineas;
    }

    private double parse(String valor) {
        if (valor == null || valor.isBlank()) {
            return 0;
        }
        String valorLimpio = valor.replace("\"", "").replace(",", ".").trim();
        if (valorLimpio.isBlank()) {
            return 0;
        }
        return Double.parseDouble(valorLimpio);
    }
}