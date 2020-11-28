package biz.heiges.rest.persons.model;

public enum GENDER {
	
	FEMALE(1, "female"),
	MALE(2, "male"),
	DIVERSE(3, "diverse");
	
	private int id;
	
	private String text;
	
	private GENDER(int id, String text) {
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
