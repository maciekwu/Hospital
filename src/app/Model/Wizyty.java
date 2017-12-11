package app.Model;

import java.sql.Date;
import java.sql.Time;

public class Wizyty {
	private Integer id;
	private Integer id_l;
	private Integer id_p;
	private Date data_w;
	private Time godzina_w;
	private String lekarz;
	private String specjalizacja;
	
	public String getLekarz() {
		return lekarz;
	}
	public void setLekarz(String lekarz) {
		this.lekarz = lekarz;
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
	public Integer getId_l() {
		return id_l;
	}
	public void setId_l(Integer id_l) {
		this.id_l = id_l;
	}
	public Integer getId_p() {
		return id_p;
	}
	public void setId_p(Integer id_p) {
		this.id_p = id_p;
	}
	public Date getData_w() {
		return data_w;
	}
	public void setData_w(Date data_w) {
		this.data_w = data_w;
	}
	public Time getGodzina_w() {
		return godzina_w;
	}
	public void setGodzina_w(Time godzina_w) {
		this.godzina_w = godzina_w;
	}
	public Wizyty(Integer id, Date data_w, Time godzina_w, String lekarz, String specjalizacja) {
		super();
		this.id = id;
		this.data_w = data_w;
		this.godzina_w = godzina_w;
		this.lekarz = lekarz;
		this.specjalizacja = specjalizacja;
	}
	public Wizyty(Date data_w, Time godzina_w, String lekarz, String specjalizacja) {
		super();
		this.data_w = data_w;
		this.godzina_w = godzina_w;
		this.lekarz = lekarz;
		this.specjalizacja = specjalizacja;
	}

	public Wizyty(Integer id, Integer id_l, Integer id_p, Date data_w, Time godzina_w) {
		super();
		this.id = id;
		this.id_l = id_l;
		this.id_p = id_p;
		this.data_w = data_w;
		this.godzina_w = godzina_w;
	}
	public Wizyty(Date data_w, Time godzina_w) {
		super();
		this.data_w = data_w;
		this.godzina_w = godzina_w;
	}
	
	
	
}
