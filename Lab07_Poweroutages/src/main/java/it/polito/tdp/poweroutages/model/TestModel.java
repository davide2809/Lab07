package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		Nerc nerc=new Nerc(3,"MAAC");
		System.out.println(model.worstCase(4, 200, nerc));

	}

}
