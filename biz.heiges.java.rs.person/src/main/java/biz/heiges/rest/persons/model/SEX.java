package biz.heiges.rest.persons.model;

public enum SEX {

	FEMALE(1, "female"),
	MALE(2, "male");

	private int id;
	
	private String text;
	
	private SEX(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}
}
