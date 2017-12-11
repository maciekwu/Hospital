package app.Model;

public class Logowanie {
	private String login;
	private String haslo;
	private String rola;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	public String getRola() {
		return rola;
	}
	public void setRola(String rola) {
		this.rola = rola;
	}
	public Logowanie(String login, String haslo, String rola) {
		super();
		this.login = login;
		this.haslo = haslo;
		this.rola = rola;
	}
	
	
}
