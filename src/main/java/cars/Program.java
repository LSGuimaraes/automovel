package cars;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Program {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cars");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
			Marca toyota	= new Marca(null, "TOYOTA");
			Marca honda		= new Marca(null, "HONDA");
			
			Modelo corolla	= new Modelo(null, "COROLLA", 154, toyota);
			Modelo pryos	= new Modelo(null, "PRYOS", 122, toyota);
			Modelo civic	= new Modelo(null, "CIVIC", 155, honda);
			Modelo fit		= new Modelo(null, "FIT", 116, honda);

			Automovel carroLaecio	= new Automovel(null, 2016, 2017, "COR CINZA", 70000.00, 75700, corolla);
			Automovel carroTatiana	= new Automovel(null, 2010, 2011, "COR PRATA", 35000.00, 95450, fit);
			
			em.persist(toyota);
			em.persist(honda);
			em.persist(corolla);
			em.persist(pryos);
			em.persist(civic);
			em.persist(fit);
			em.persist(carroLaecio);
			em.persist(carroTatiana);

		em.getTransaction().commit();
		
		Query qryMarca = em.createQuery("select m from Marca m");
		List<Marca> marcas = qryMarca.getResultList();
		System.out.println("***** MARCAS CADASTRADAS *****");
		for (Marca m : marcas) {
			System.out.println("Id: " + m.getId() + " Marca: " + m.getNome());
		}
		
		System.out.println("----------------------------------------");
		
		
		Query qryModelo = em.createQuery("select m from Modelo m");
		List<Modelo> modelos = qryModelo.getResultList();
		System.out.println("***** MODELOS CADASTRADOS *****");
		for (Modelo m : modelos) {
			System.out.println("Id: " + m.getId() + " Descrição: " + m.getDescricao() + " CV: " + m.getPotencia() +
								" Marca: " + m.getMarca().getNome());
		}

		System.out.println("----------------------------------------");		
		
		Query qryAutomovel = em.createQuery("select a from Automovel a");
		List<Automovel> autos = qryAutomovel.getResultList();
		System.out.println("***** MODELOS CADASTRADOS *****");
		for (Automovel a : autos) {
			System.out.println("Id: " + a.getId() + " Ano/Modelo: " + a.getAnoFabricacao() + "/" + a.getAnoModelo() + 
								" Obs: " + a.getObservacoes() + " Preço: " + a.getPreco() + 
								" Km: " + a.getKilometragem() + " Modelo: " + a.getModelo().getDescricao());
		}
		
		em.close();
		emf.close();
	}
}

