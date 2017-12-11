package app.Model;

public class Lekarze {
	private Integer id;
	private String imie;
	private String nazwisko;
	private String login;
	private String specjalizacja;
	
	public Lekarze(String imie, String nazwisko, String login, String specjalizacja) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.login = login;
		this.specjalizacja = specjalizacja;
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
	public String getSpecjalizacja() {
		return specjalizacja;
	}
	public void setSpecjalizacja(String specjalizacja) {
		this.specjalizacja = specjalizacja;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
