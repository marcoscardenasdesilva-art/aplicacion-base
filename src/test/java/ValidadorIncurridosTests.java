


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import es.juntadeandalucia.incurridos.model.LineaIncurrido;
import es.juntadeandalucia.incurridos.service.ValidadorIncurridos;
import es.juntadeandalucia.incurridos.service.ValidadorIncurridos.ResultadoValidacion;


public class ValidadorIncurridosTests {

    /**
     * TEST 1
     * Falla a propósito:
     * - La suma de horas es incorrecta
     * - El importe también es incorrecto
     */
    @Test
    void testFallaTodo() {

        LineaIncurrido linea = new LineaIncurrido(
                "TEST-1",
                10,   // JP
                10,   // CO
                10,   // AN
                10,   // PR
                100,  // total horas MAL (debería ser 40)
                9999  // importe MAL
        );

        ValidadorIncurridos validador = new ValidadorIncurridos();
        ResultadoValidacion resultado = validador.validar(linea);

        assertNotNull(resultado);
        assertEquals("TEST-1", resultado.id);
        assertEquals(40, resultado.sumaHorasCorrecta);
        assertTrue(resultado.importeCorrecto > 0);
    }

    /**
     * TEST 2
     * La suma de horas es correcta
     * El importe NO es correcto (fallo de tarifas)
     */
    @Test
    void testHorasCorrectasImporteIncorrecto() {

        // Horas: 10 + 10 + 10 + 10 = 40 (CORRECTO)
        // Importe real:
        // (10*38) + (10*38) + (10*31.5) + (10*20) = 1275
        LineaIncurrido linea = new LineaIncurrido(
                "TEST-2",
                10,    // JP
                10,    // CO
                10,    // AN
                10,    // PR
                40,    // total horas CORRECTO
                1000   // importe MAL a propósito
        );

        ValidadorIncurridos validador = new ValidadorIncurridos();
        ResultadoValidacion resultado = validador.validar(linea);

        assertNotNull(resultado);
        assertEquals("TEST-2", resultado.id);

        // como la suma es correcta, debe ir 0
        assertEquals(0, resultado.sumaHorasCorrecta);

        // el importe correcto debe calcularse
        assertEquals(1275, resultado.importeCorrecto);
    }
}
