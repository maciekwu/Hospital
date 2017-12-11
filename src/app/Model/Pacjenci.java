package app.Model;

public class Pacjenci {
	private Integer id;
	private String imie;
	private String nazwisko;
	private String login;
	
	public Pacjenci(String imie, String nazwisko, String login) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.login = login;
	}
		
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
