package avaliacao.segundo.trimestre;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteSegundoProblema {
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
	public void caminhoABCDBEFGHIFJ() {
		int[] fig = { 1 };
		Problemas.segundoProblema(2, 1, fig);
		assertEquals("1", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABEFGHIFJ() {
		int[] fig = {};
		Problemas.segundoProblema(2, 0, fig);
		assertEquals("2", outContent.toString().replaceAll("\\s+", ""));
	}

	/**
	 * Teste que identifica uma falha
	 * já que a solução não trata o caso o n menor que o m
	 */
	@Test
	public void caminhoABCDBEFJ() {
		int[] fig = { 1 };
		Problemas.segundoProblema(0, 1, fig);
		assertEquals("0", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABCDBEFGIFJ() {
		int[] fig = { 1 };
		Problemas.segundoProblema(1, 1, fig);
		assertEquals("0", outContent.toString().replaceAll("\\s+", ""));
	}
}