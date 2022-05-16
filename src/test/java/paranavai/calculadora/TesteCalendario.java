package paranavai.calculadora;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import paranavai.calendario.Calendario;

public class TesteCalendario {
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
	// Será que este teste está correto?
	public void verificaSeMesAtualFoiImpresso() throws IOException {
		Path path = Paths.get("src\\test\\resources", "maio2022.txt");
		Calendario.mostrarCalendario();
		String maio2022 = Files.readString(path);
		assertEquals(maio2022.replaceAll("\\s+", ""), outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void imprimeSetembro1752() throws IOException {
		Path path = Paths.get("src\\test\\resources", "setembro1752.txt");
		Calendario.mostrarCalendario("9", "1752");
		String setembro1752 = Files.readString(path);
		assertEquals(setembro1752.replaceAll("\\s+", ""), outContent.toString().replaceAll("\\s+", ""));
	}

	@Test
	public void testaLetraNoParametroAno() {
		Throwable exception = assertThrows(NumberFormatException.class, () -> Calendario.mostrarCalendario("e"));
		assertEquals("mostrarCalendario: e: ano inválido.", exception.getMessage());
	}

	@Test
	public void testaLetraNoParametroMes() {
		Throwable exception = assertThrows(NumberFormatException.class,
				() -> Calendario.mostrarCalendario("e", "2022"));
		assertEquals("mostrarCalendario: e: mês inválido.", exception.getMessage());
	}

}
