package paranavai.calendario;

import java.util.Calendar;

public class Calendario {
	private String diaDaSemana = "Do Se Te Qa Qi Se Sa";

	private String meses[] = new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
			"Agosto",
			"Setembro", "Outubro", "Novembro", "Dezembro" };

	private int diasPorMes[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, };

	public String getCalendario() {
		Calendar cl = Calendar.getInstance();
		int mostrarMes = cl.get(Calendar.MONTH) + 1;
		int mostrarAno = cl.get(Calendar.YEAR);

		return getMesPorAno(mostrarMes, mostrarAno);
	}

	public String getCalendario(String ano) {
		int mostrarAno = validaAno(ano);
		if (mostrarAno == -1) {
			throw new NumberFormatException("getCalendario: " + ano + ": ano inválido.");
		}
		String calendario = "";
		for (int mostrarMes = 1; mostrarMes <= 12; mostrarMes++) {
			calendario += getMesPorAno(mostrarMes, mostrarAno) + "\n";
		}
		return calendario;
	}

	public String getCalendario(String mes, String ano) {
		int mostrarMes = validaMes(mes);
		int mostrarAno = validaAno(ano);
		if (mostrarMes == -1) {
			throw new NumberFormatException("getCalendario: " + mes + ": mês inválido.");
		}
		if (mostrarAno == -1) {
			throw new NumberFormatException("getCalendario: " + ano + ": ano inválido.");
		}

		return getMesPorAno(mostrarMes, mostrarAno);
	}

	public String getCalendario(String... variosParametros) {
		return getCalendario(variosParametros[0], variosParametros[1]);
	}

	private String getMesPorAno(int mes, int ano) {
		String mesFormatado = String.format("   %s %d\n", this.meses[mes - 1], ano);
		mesFormatado += String.format("%s\n", this.diaDaSemana);
		String s = calendario(primeiroDiaMes(mes, ano), numeroDeDiasNoMes(mes, ano));
		return mesFormatado + s;
	}

	/***
	 * 
	 * @param mes
	 * @return -1 se mês inválido, senão retorna o parâmetro com tipo int
	 */
	private int validaMes(String mes) {
		int mostrarMes = 0;
		try {
			mostrarMes = Integer.parseInt(mes);
		} catch (Exception e) {
			throw new NumberFormatException("getCalendario: " + mes + ": entrada inváida.");
		}
		if (mostrarMes < 1 || mostrarMes > 12) {
			return -1;
		}
		return mostrarMes;
	}

	/***
	 * 
	 * @param ano
	 * @return -1 se ano inválido, senão retorna o parâmetro com tipo int
	 */
	private int validaAno(String ano) {
		int mostrarAno = 0;
		try {
			mostrarAno = Integer.parseInt(ano);
		} catch (Exception e) {
			throw new NumberFormatException("getCalendario: " + ano + ": entrada inválida.");
		}
		if (mostrarAno < 1 || mostrarAno > 9999) {
			return -1;
		}
		return mostrarAno;
	}

	private int primeiroDiaMes(int mes, int ano) {
		int dia = 0;
		if (ehBissexto(ano) && mes > 2)
			dia++;
		for (int i = 0; i < mes; i++)
			dia += diasPorMes[i];
		if (ano == 1752 && mes > 9)
			dia -= 11;
		dia %= 7;
		dia = (dia + diaDaSemanaDoPrimeiroJaneiro(ano)) % 7;
		return dia;
	}

	private int numeroDeDiasNoMes(int mes, int ano) {
		if (ehBissexto(ano) && mes == 2)
			return 29;
		if (ano == 1752 && mes == 9)
			return 19;
		return diasPorMes[mes];
	}

	private boolean ehBissexto(int ano) {
		if (ano <= 1752) {
			if (ano % 4 == 0)
				return true;
			else
				return false;
		} else {
			if (ano % 400 == 0)
				return true;
			if (ano % 100 == 0)
				return false;
			if (ano % 4 == 0)
				return true;
		}
		return false;
	}

	private String calendario(int deslocamento, int qtdDeDias) {
		int qtdDiasNaSemana = deslocamento + 1;
		if (qtdDeDias == 19)
			return "       1  2 14 15 16\n17 18 19 20 21 22 23\n24 25 26 27 28 29 30";
		String mesImpresso = "";
		for (int k = 0; k < deslocamento; k++)
			mesImpresso += "   ";

		for (int dia = 1; dia <= qtdDeDias; dia++, qtdDiasNaSemana++) {
			if (dia < 10)
				mesImpresso += " ";
			mesImpresso += dia;
			if (qtdDiasNaSemana % 7 == 0)
				mesImpresso += "\n";
			else
				mesImpresso += " ";
		}
		return mesImpresso;
	}

	/**
	 * @param ano
	 * @return dia da semana do primeiro de janeiro dado um determinado ano
	 */
	private int diaDaSemanaDoPrimeiroJaneiro(int ano) {
		int dia;
		// Calendário Gregoriano possui um dia extra a cada 4 anos
		dia = 4 + ano + (ano + 3) / 4;

		// Calendário Juliano tem menos 3 dias a cada 400 anos
		if (ano > 1800) {
			dia -= (ano - 1701) / 100;
			dia += (ano - 1601) / 400;
		}
		// Instante da grande mudança de calendário
		if (ano > 1752)
			dia += 3;

		return (dia % 7);
	}

}
