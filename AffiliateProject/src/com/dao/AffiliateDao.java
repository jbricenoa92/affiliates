package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.config.DatabaseConnection;
import com.domain.Afiliado;
import com.domain.TipoIdentific;

public class AffiliateDao {
	private static final Logger LOGGER = Logger.getLogger(AffiliateDao.class.getName());
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NOMBRES = "nombres";
	private static final String COLUMN_APELLIDOS = "apellidos";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_GENERO = "genero";
	private static final String COLUMN_NUMERO = "numero";
	private static final String COLUMN_DIRECCION = "direccion";
	private static final String COLUMN_TELEFONO = "telefono";
	private static final String COLUMN_DESCRIPCION = "descripcion";
	private static final String SELECT_ALL_AFILIADO = "SELECT * FROM Afiliado";
	private static final String SELECT_ALL_TIPOIDENTI = "SELECT * FROM Tipoidentific";
	private static final String SELECT_ALL_AFILIADO_DESCRIPTION_TIPOIDENTI_FOR_ID = "SELECT a.*, t.descripcion FROM Afiliado a INNER JOIN Tipoidentific t ON a.idtipoiden = t.id";
	
	public List<Afiliado> getAllAffiliate() throws SQLException, ClassNotFoundException {
		return executeQuery(SELECT_ALL_AFILIADO, rs -> mapAfiliado(rs));
	}

	public List<TipoIdentific> getAllTypeDocument() throws SQLException, ClassNotFoundException {
		return executeQuery(SELECT_ALL_TIPOIDENTI, rs -> mapTipoIdentific(rs));
	}

	public List<Afiliado> getListAll() throws SQLException, ClassNotFoundException {
		return executeQuery(SELECT_ALL_AFILIADO_DESCRIPTION_TIPOIDENTI_FOR_ID, rs -> {
			Afiliado afiliado = mapAfiliado(rs);
			TipoIdentific tipo = mapTipoIdentific(rs);
			afiliado.setTipoIdentific(tipo);
			return afiliado;
		});
	}

	public List<Afiliado> getByApellido(String apellido) throws SQLException, ClassNotFoundException {
		String query = "SELECT a.*, t.descripcion FROM Afiliado a INNER JOIN Tipoidentific t ON a.idtipoiden = t.id WHERE a.apellidos = '" + apellido + "'";
		return executeQuery(query, rs -> {
			Afiliado afiliado = mapAfiliado(rs);
			TipoIdentific tipo = mapTipoIdentific(rs);
			afiliado.setTipoIdentific(tipo);
			return afiliado;
		});
	}
	
	public List<Afiliado> findByLetteByApellido(String letter) throws SQLException, ClassNotFoundException {
		String query = "SELECT a.*, t.descripcion FROM Afiliado a INNER JOIN Tipoidentific t ON a.idtipoiden = t.id WHERE a.apellidos LIKE '" + letter + "%'";
		System.out.println(query);
		return executeQuery(query, rs -> {
			Afiliado afiliado = mapAfiliado(rs);
			TipoIdentific tipo = mapTipoIdentific(rs);
			afiliado.setTipoIdentific(tipo);
			return afiliado;
		});
	}
	
	public List<Afiliado> getByDocument(String tipo, String numero) throws SQLException, ClassNotFoundException {
		String query = "SELECT a.*, t.descripcion FROM Afiliado a INNER JOIN Tipoidentific t ON a.idtipoiden = t.id WHERE a.numero = '"
				+ numero + "' AND t.descripcion = '" + tipo + "'";

		return executeQuery(query, rs -> {
			Afiliado afiliado = mapAfiliado(rs);
			TipoIdentific tipoDocument = mapTipoIdentific(rs);
			afiliado.setTipoIdentific(tipoDocument);
			return afiliado;
		});
	}

	private <T> List<T> executeQuery(String query, ResultSetMapper<T> mapper)
			throws SQLException, ClassNotFoundException {
		List<T> lista = new ArrayList<>();
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			System.out.println(rs);
			while (rs.next()) {
				System.out.println(rs);
				lista.add(mapper.map(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQL error while executing query: " + query, e);
			throw e;
		}
		return lista;
	}

	private Afiliado mapAfiliado(ResultSet rs) throws SQLException {
		Afiliado entidad = new Afiliado();
		entidad.setId(rs.getInt(COLUMN_ID));
		entidad.setNombres(rs.getString(COLUMN_NOMBRES));
		entidad.setApellidos(rs.getString(COLUMN_APELLIDOS));
		entidad.setEmail(rs.getString(COLUMN_EMAIL));
		entidad.setGenero(rs.getString(COLUMN_GENERO));
		entidad.setNumero(rs.getString(COLUMN_NUMERO));
		entidad.setDireccion(rs.getString(COLUMN_DIRECCION));
		entidad.setTelefono(rs.getString(COLUMN_TELEFONO));
		return entidad;
	}

	private TipoIdentific mapTipoIdentific(ResultSet rs) throws SQLException {
		TipoIdentific entidad = new TipoIdentific();
		entidad.setId(rs.getInt(COLUMN_ID));
		entidad.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
		return entidad;
	}

	@FunctionalInterface
	private interface ResultSetMapper<T> {
		T map(ResultSet rs) throws SQLException;
	}
}
