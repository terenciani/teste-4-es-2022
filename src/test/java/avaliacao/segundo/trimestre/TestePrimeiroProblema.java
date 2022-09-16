package avaliacao.segundo.trimestre;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestePrimeiroProblema {
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
	public void caminhoABG() {
		Problemas.primeiroProblema(1);
		assertEquals("0", outContent.toString());
	}

	@Test
	public void caminhoABCDG() {
		Problemas.primeiroProblema(2);
		assertEquals("01", outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void caminhoABCDEFG() {
		Problemas.primeiroProblema(5);
		assertEquals("01123", outContent.toString().replaceAll("\\s+", ""));
	}
}