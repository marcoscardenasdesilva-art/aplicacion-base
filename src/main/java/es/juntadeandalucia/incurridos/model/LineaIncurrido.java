package es.juntadeandalucia.incurridos.model;

public class LineaIncurrido {

    private String idPeticion;
    private double horasDP;
    private double horasCO;
    private double horasAN;
    private double horasPR;
    private double totalHoras;
    private double totalImporte;

    public LineaIncurrido(String idPeticion, double horasDP, double horasCO,
                          double horasAN, double horasPR,
                          double totalHoras, double totalImporte) {
        this.idPeticion = idPeticion;
        this.horasDP = horasDP;
        this.horasCO = horasCO;
        this.horasAN = horasAN;
        this.horasPR = horasPR;
        this.totalHoras = totalHoras;
        this.totalImporte = totalImporte;
    }

    public String getIdPeticion() {
        return idPeticion;
    }

    public double getHorasDP() {
        return horasDP;
    }

    public double getHorasCO() {
        return horasCO;
    }

    public double getHorasAN() {
        return horasAN;
    }

    public double getHorasPR() {
        return horasPR;
    }

    public double getTotalHoras() {
        return totalHoras;
    }

    public double getTotalImporte() {
        return totalImporte;
    }
}
