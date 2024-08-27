import controller.JSONGenerators;
import model.DizimosOfertaDAO;
import model.MembroDAO;
import model.PatrimonioDAO;

public class testes {

	public static void main(String[] args) {
		MembroDAO dao = new MembroDAO();
		DizimosOfertaDAO daoDZO = new DizimosOfertaDAO();
		PatrimonioDAO daoPTR =  new PatrimonioDAO();
	
		System.out.println(daoPTR.totalPatrimonio());
		
		JSONGenerators json = new JSONGenerators();
		
		json.JSONListarTotalDizimoOferta("Oferta");
		json.JSONListarTotalcontas();

		System.out.println("Total: " + dao.contarMembrosGrupo("Comum"));
	}
}
