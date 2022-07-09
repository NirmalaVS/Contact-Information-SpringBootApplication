package com.vaudience.contactinformation.contactIdGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerator implements IdentifierGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(IdentifierGenerator.class);

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String prefix = "CO";
		String suffix = "";

		try {
			Connection connection = session.connection();
			Statement statement = connection.createStatement();

			String query = "Select count(Contact_Id) as Id from Contact_Basic_Info";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				Integer id = resultSet.getInt(1) + 1;
				suffix = id.toString();
			}
			return prefix + suffix;

		} catch (SQLException ex) {
			System.out.println("Exception has occured while generating ContactId " + ex.toString());
			LOGGER.error("Exception has occured while generating ContactId {} ", ex.toString());
		}

		return null;

	}
}
