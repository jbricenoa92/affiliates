package com.controller;

import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.dao.AffiliateDao;
import com.domain.Afiliado;
import com.domain.TipoIdentific;

@Named("consultaAfiliado")
@RequestScoped
public class CheckAfilliate {

	private TipoIdentific tipoIdent;
	private String queryBy = "0";
	private String tid = "";
	private String tidNumber;
	private String apellidos;
	private Afiliado afiliado = new Afiliado();
	private AffiliateDao entidadDAO = new AffiliateDao();
	private List<Afiliado> afiliados;
	private int condition = 0;
	private boolean optionSelected = false;
	private boolean missingData = false;

	@SuppressWarnings("unused")
	private List<Afiliado> getAllAfiliados() throws SQLException, ClassNotFoundException {
		afiliados = entidadDAO.getListAll();
		condition = afiliados.size();
		return entidadDAO.getListAll();
	}

	private List<Afiliado> getByApellido() throws SQLException, ClassNotFoundException {
		setMissingData(false);
		afiliados = entidadDAO.findByLetteByApellido(apellidos);
		condition = afiliados.size();
		return entidadDAO.getByApellido(apellidos);
	}

	private List<Afiliado> getByDocument() throws SQLException, ClassNotFoundException {
		setMissingData(false);
		afiliados = entidadDAO.getByDocument(tid, tidNumber);
		condition = afiliados.size();
		return entidadDAO.getByDocument(tid, tidNumber);
	}

	public void accionQuery() throws ClassNotFoundException, SQLException {
		switch (queryBy) {
		case "0":
			optionDocument();
			break;
		case "1":
			optionApellido();
			break;
		}
	}

	private void optionApellido() throws ClassNotFoundException, SQLException {
		 System.out.println("Procesando búsqueda : " + apellidos);
	    if (apellidos != null && !apellidos.isEmpty()) {
	        System.out.println("Procesando búsqueda por apellido: " + apellidos);
	        getByApellido();
	    } else {
	        System.out.println("Faltan datos para realizar la búsqueda por apellido.");
	        setMissingData(true);
	    }
	}

	private void optionDocument() throws ClassNotFoundException, SQLException {

		if (tidNumber.length() > 5 && !tid.isEmpty()) {
			getByDocument();
			return;
		}
		setMissingData(true);

	}

	public String getQueryBy() {
		return queryBy;
	}

	public void setQueryBy(String queryBy) {

		this.queryBy = queryBy;
		this.optionSelected = "1".equals(queryBy);
	}

	public void procesarSeleccion() {
		System.out.println("Opción seleccionada: " + queryBy);
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTidNumber() {
		return tidNumber;
	}

	public void setTidNumber(String tidNumber) {
		this.tidNumber = tidNumber;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public TipoIdentific getTipoIdent() {
		return tipoIdent;
	}

	public void setTipoIdent(TipoIdentific tipoIdent) {
		this.tipoIdent = tipoIdent;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public List<Afiliado> getAfiliados() {
		return afiliados;
	}
	
	public void setAfiliados(List<Afiliado> afiliados) {
		this.afiliados = afiliados;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public boolean isOptionSelected() {
		return optionSelected;
	}

	public void setOptionSelected(boolean optionSelected) {
		this.optionSelected = optionSelected;
	}

	public boolean getMissingData() {
		return missingData;
	}

	public void setMissingData(boolean missingData) {
		this.missingData = missingData;
	}

}
