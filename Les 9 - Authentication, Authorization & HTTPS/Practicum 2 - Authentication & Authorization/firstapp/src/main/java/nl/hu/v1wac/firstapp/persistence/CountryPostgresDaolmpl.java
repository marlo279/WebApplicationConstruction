package nl.hu.v1wac.firstapp.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.persistence.interfaces.CountryDao;

public class CountryPostgresDaolmpl extends PostgresBaseDao implements CountryDao {
	public boolean save(Country country) {
		System.out.println("-x-x-x-x-x-");
		
		try (Connection con = super.getConnection()) {
			String q = "insert into country(code, name, capital) " +
					"values ('" + country.getCode() + "', '" + country.getName() + "', '" + country.getCapital() + "')";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	private List<Country> getCountries(String query) {
		List<Country> results = new ArrayList<Country>();
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String nm = dbResultSet.getString("name"); 
				String cap = dbResultSet.getString("capital");
				String ct = dbResultSet.getString("continent");
				String reg = dbResultSet.getString("region");
				int sur = dbResultSet.getInt("surfacearea"); 
				int pop = dbResultSet.getInt("population");
				String gov = dbResultSet.getString("governmentform");
				double lat = dbResultSet.getDouble("latitude");
				double lng = dbResultSet.getDouble("longitude");
				
				Country c = new Country(code, iso3, nm, cap, ct, reg, sur, pop, gov, lat, lng);
				results.add(c);
				
			}
		} catch (SQLException sqle) { 
			sqle.printStackTrace(); 
			}
		
		System.out.println("Er zijn " + results.size() + " recors geladen uit het database!");
		
		return results;
	}
	
	public List<Country> findAll() {
		return this.getCountries("select * from country order by code limit 20");
	}
	
	public Country findByCode(String codeSearch) {
		Country result = null;
		System.out.println("xx");
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("select * from country where code = '" + codeSearch + "'");
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String nm = dbResultSet.getString("name"); 
				String cap = dbResultSet.getString("capital");
				String ct = dbResultSet.getString("continent");
				String reg = dbResultSet.getString("region");
				int sur = dbResultSet.getInt("surfacearea"); 
				int pop = dbResultSet.getInt("population");
				String gov = dbResultSet.getString("governmentform");
				double lat = dbResultSet.getDouble("latitude");
				double lng = dbResultSet.getDouble("longitude");
				
				result = new Country(code, iso3, nm, cap, ct, reg, sur, pop, gov, lat, lng);
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		
		return result;
	}
	
	public List<Country> find10LargestPopulations() {
		return this.getCountries("select * from country order by population DESC limit 10");
	}
	
	public List<Country> find10LargestSurfaces() {
		return this.getCountries("select * from country order by surfacearea DESC limit 10");
	}
	
	public boolean update(Country country) {
		try (Connection con = super.getConnection()) {
			
			String q = "Update country SET "
					+ "name = '" + country.getName() + "', "
					+ "capital = '" + country.getCapital() + "', "
					+ "surfacearea = " + country.getSurface() + ", "
					+ "population = " + country.getPopulation() + " "
					+ "where code = '" + country.getCode() + "'";
			System.out.println(q);
			PreparedStatement pstmt = con.prepareStatement(q);
			int dbResultSet = pstmt.executeUpdate();	
			System.out.println("Land met country code: " + country.getCode() + " is in het database aangepast!");

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return true;
	}
	
	public boolean delete(Country country) {
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("delete from country where code = '" + country.getCode() + "'");
			int dbResultSet = pstmt.executeUpdate();
			System.out.println("Land met country code: " + country.getCode() + " is verwijderd uit het database!");
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
}
