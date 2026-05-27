package es.juntadeandalucia.incurridos.service;

import es.juntadeandalucia.incurridos.model.LineaIncurrido;

public class ValidadorIncurridos {

    private static  double TARIFA_DP = 38;
    private static  double TARIFA_CO = 38;
    private static  double TARIFA_AN = 31.5;
    private static  double TARIFA_PR = 20;

    public ResultadoValidacion validar(LineaIncurrido li) {

        double sumaHorasReal = li.getHorasDP() + li.getHorasCO()
                + li.getHorasAN() + li.getHorasPR();

        double importeReal =
                li.getHorasDP() * TARIFA_DP +
                li.getHorasCO() * TARIFA_CO +
                li.getHorasAN() * TARIFA_AN +
                li.getHorasPR() * TARIFA_PR;

        boolean sumaHorasOk = Double.compare(sumaHorasReal, li.getTotalHoras()) == 0;
        boolean importeOk = Double.compare(importeReal, li.getTotalImporte()) == 0;

        if (sumaHorasOk && importeOk) {
            return null; // línea correcta
        }

        double horasSalida = sumaHorasOk ? 0 : sumaHorasReal;
        double importeSalida = importeOk ? 0 : importeReal;

        return new ResultadoValidacion(
                li.getIdPeticion(),
                horasSalida,
                importeSalida
        );
    }

    public static class ResultadoValidacion {
        public String id;
        public double sumaHorasCorrecta;
        public double importeCorrecto;

        public ResultadoValidacion(String id, double sumaHorasCorrecta, double importeCorrecto) {
            this.id = id;
            this.sumaHorasCorrecta = sumaHorasCorrecta;
            this.importeCorrecto = importeCorrecto;
        }
    }
}