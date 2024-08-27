package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.ContasDAO;
import model.ContasJavaBeans;
import model.DizimosOferta;
import model.DizimosOfertaDAO;

public class JSONGenerators {

	public JSONGenerators() {
	}

	DizimosOfertaDAO dao = new DizimosOfertaDAO();
	ContasDAO daoConta = new ContasDAO();

	public void JSONDizimos(String tipo) {

		ArrayList<DizimosOferta> contribuicoes = dao.listarDizimos(tipo);

		FileWriter writefile = null;
		JSONArray dizimosOfertas = new JSONArray();

		for (DizimosOferta dizimoOferta : contribuicoes) {
			JSONObject objeto = new JSONObject();
			objeto.put("valor", dizimoOferta.getDzovalor());
			objeto.put("data", dizimoOferta.getDzodtcontribuicao().toString());
			objeto.put("filial", dizimoOferta.getDzoFilialNome());

			dizimosOfertas.add(objeto);
		}

		try {

			writefile = new FileWriter("C:/Users/user/eclipse-workspace/SGI/src/main/webapp/JSON/" + tipo + ".json");
			writefile.write(dizimosOfertas.toJSONString());
			writefile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void JSONListarTotalDizimoOferta(String tipo) {
		JSONArray dizimosOfertasArray = new JSONArray();
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		LocalDate dataAtual = LocalDate.now();
		int ano = dataAtual.getYear();

		for (int i = 1; i <= 12; i++) {
			JSONObject objeto = new JSONObject();
			objeto.put("mes", meses[i - 1]);
			objeto.put("valor", Float.toString(dao.totalDizimoOfertaMes(i, ano, tipo)));
			dizimosOfertasArray.add(objeto);
		}

		JSONObject dizimosOfertasObject = new JSONObject();
		dizimosOfertasObject.put("dizimosOfertas", dizimosOfertasArray);

		String caminhoArquivo = "C:/Users/user/eclipse-workspace/SGI/src/main/webapp/JSON/chart" + tipo + ".json";
		try (FileWriter writefile = new FileWriter(caminhoArquivo)) {
			writefile.write(dizimosOfertasObject.toJSONString());
			System.out.println("Arquivo JSON atualizado: " + caminhoArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public void JSONListarTotalcontas() {

	
		LocalDate dataAtual = LocalDate.now();
		Date date = java.sql.Date.valueOf(dataAtual);

		// Supondo que daoConta.listarContas() retorna uma lista de ContasJavaBeans
		ArrayList<ContasJavaBeans> contas = daoConta.listarContas();

		float totalPago = 0;
		float totalAtrasado = 0;
		float totalPendente = 0;

		for (ContasJavaBeans conta : contas) {
			// Converter Date para LocalDate
		

			// Verificar se a data de vencimento é igual ou posterior à data atual
			if (conta.getCntstatuspagamento().equals("Pago")) {

				totalPago += conta.getCntvalor();
			} else if (conta.getCntdtvencimento().before(date) && conta.getCntstatuspagamento().equals("Pendente")) {
				totalAtrasado += conta.getCntvalor();
			} else if (conta.getCntdtvencimento().after(date) && conta.getCntstatuspagamento().equals("Pendente")) {
				totalPendente += conta.getCntvalor();
			}
		}

		JSONObject objeto = new JSONObject();

		objeto.put("Pago",Float.toString(totalPago));
		objeto.put("Atrasado", Float.toString(totalAtrasado));
		objeto.put("Pendente", Float.toString(totalPendente));

		JSONObject valores = new JSONObject();
		valores.put("valores", objeto);

		String caminhoArquivo = "C:/Users/user/eclipse-workspace/SGI/src/main/webapp/JSON/valoresContas.json";
		try (FileWriter writefile = new FileWriter(caminhoArquivo)) {
			writefile.write(valores.toJSONString());
			System.out.println("Arquivo JSON atualizado: " + caminhoArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
