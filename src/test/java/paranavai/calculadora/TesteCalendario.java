package paranavai.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import paranavai.calendario.Calendario;

public class TesteCalendario {

	@Test
	public void imprimeJaneiro2025() throws IOException {
		Path path = Paths.get("src\\test\\resources", "janeiro2025.txt");
		String saidaEsperada = Files.readString(path);
		saidaEsperada = saidaEsperada.replace("\r\n", "\n");

		Calendario calendario = new Calendario();
		String janeiro2025 = calendario.getCalendario("1", "2025");

		assertEquals(saidaEsperada, janeiro2025);
	}

	@Test
	// Será que este teste está correto?
	public void verificaSeMesAtualFoiImpresso() throws IOException {
		Path path = Paths.get("src\\test\\resources", "maio2025.txt");
		String saidaEsperada = Files.readString(path);
		saidaEsperada = saidaEsperada.replace("\r\n", "\n");

		Calendario calendario = new Calendario();
		String maio2025 = calendario.getCalendario();

		assertEquals(saidaEsperada, maio2025);
	}

	@Test
	public void imprimeSetembro1752() throws IOException {
		Path path = Paths.get("src\\test\\resources", "setembro1752.txt");
		String saidaEsperada = Files.readString(path);
		saidaEsperada = saidaEsperada.replace("\r\n", "\n");

		Calendario calendario = new Calendario();
		String maio2025 = calendario.getCalendario("9", "1752");

		assertEquals(saidaEsperada, maio2025);
	}

	@Test
	public void testaLetraNoParametroAno() {
		assertThrows(NumberFormatException.class, () -> {
			Calendario calendario = new Calendario();
			calendario.getCalendario("e");
		});
	}

	@Test
	public void testaLetraNoParametroMes() {
		assertThrows(NumberFormatException.class,
				() -> {
					Calendario calendario = new Calendario();
					calendario.getCalendario("e", "2022");
				});
	}
}
