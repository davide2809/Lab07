package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private int X;
	private int Y;
	private int bestPersone;
	private List<PowerOutages> bestSoluzione;
	private int totCustomers;
	private int totHours;
	
	public Model() {
		podao = new PowerOutageDAO();
		this.X=0;
		this.Y=0;
		this.bestPersone=0;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<String> worstCase(int X, int Y, Nerc nerc) {
		int nercId=nerc.getId();
		List<PowerOutages> powerOutages=new ArrayList<PowerOutages>(podao.getPowerOutagesListNerc(nercId));
		this.X=X;
		this.Y=Y;
		List<PowerOutages> soluzione=new ArrayList<PowerOutages>();
		ricorsione(soluzione,0,powerOutages);
		List<String> daPassare=new LinkedList<String>();
//		Converto Bestsoluzione in String 
		for(PowerOutages po:bestSoluzione) {
//			System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
			String s= po.getDateEventFinished().getYear()+" "+po.getDateEventBegan()+" "+po.getDateEventFinished()+" "+po.getDurata()+" "+po.getCustomersAffected()+"\n";
			daPassare.add(s);
		}
		this.totCustomers=this.calcolaPersone(bestSoluzione);
		this.totHours=this.calcolaNumeroOre(bestSoluzione);
		return daPassare;
	}
	
	public void ricorsione(List<PowerOutages> parziale,int L, List<PowerOutages> disponibili) {
//		Casi terminali
		if((controllo(parziale))) {
		int persone=calcolaPersone(parziale);
		if(persone>bestPersone) {
			bestPersone=persone;
			bestSoluzione= new ArrayList<PowerOutages>(parziale);
		}
		}
		
		if(L==disponibili.size()) {
			return;
		}
//		Casi Intermedi
		
//		Aggiungo
		parziale.add(disponibili.get(L));
		ricorsione(parziale,L+1,disponibili);
		parziale.remove(disponibili.get(L));
		
//		Non aggiungo
		ricorsione(parziale,L+1,disponibili);
	}

	private int calcolaPersone(List<PowerOutages> parziale) {
		int somma=0;
		for(PowerOutages po:parziale) {
			somma+=po.getCustomersAffected();
		}
		return somma;
	}

	private boolean controllo(List<PowerOutages> parziale) {
		int SommaOre=0;
		int maxYear=0;
		int minYear=3000;
		for(PowerOutages po:parziale) {
			SommaOre+=po.getDurata();
			if(po.getDateEventFinished().getYear()>maxYear)
				maxYear=po.getDateEventFinished().getYear();
			if(po.getDateEventFinished().getYear()<minYear)
				minYear=po.getDateEventFinished().getYear();
		}
		if(SommaOre>Y)
			return false;
		if((maxYear-minYear)> X)
			return false;
		return true;
	}

	public int getTotCustomers() {
		return totCustomers;
	}

	public int getTotHours() {
		return totHours;
	}
	
	public int calcolaNumeroOre(List<PowerOutages> lista) {
		int sommaOre=0;
		for(PowerOutages po:lista) {
			sommaOre+=po.getDurata();
		}
		return sommaOre;
	}
	
	
	

}
