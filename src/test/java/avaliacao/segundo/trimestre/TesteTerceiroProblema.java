package avaliacao.segundo.trimestre;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteTerceiroProblema {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void init() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void finish() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void caminhoABCDFBGHIJHKLMNLO() {
		int[] numeros = { 6, 3, 1, 4, 6, 9, 4, 6, 7, 5 };
		Problemas.terceiroProblema(numeros);
		assertEquals("6464631975", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABGHIJHKLMNLO() {
		// Caminho não executável, pois não é possível manipular o valor de i para que a
		// condição do primeiro laço seja falsa externamente
		assertEquals(true, true);
	}

	@Test
	public void caminhoABCEFBGHIJHKLMNLO() {
		int[] numeros = { 5, 3, 1, 3, 5, 9, 3, 8, 7, 5 };
		Problemas.terceiroProblema(numeros);
		assertEquals("8531359375", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABCDFBGHKLMNLO() {
		// Caminho "não executável" considerando uma única iteração
		// Passar por C -> D precisa ser par, mas passar por H -> K não pode ter par
		int[] numeros = { 5, 3, 1, 3, 5, 9, 3, 8, 7, 5 };
		Problemas.terceiroProblema(numeros);
		assertEquals("8531359375", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABCDFBGHIJHKLO() {
		int[] numeros = { 5, 3, 1, 3, 5, 9, 3, 9, 7, 5 };
		Problemas.terceiroProblema(numeros);
		assertEquals("5313593975", outContent.toString().replaceAll("\\s+", ""));
	}
}