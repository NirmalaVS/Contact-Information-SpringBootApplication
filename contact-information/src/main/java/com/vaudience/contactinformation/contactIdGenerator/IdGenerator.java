package com.vaudience.contactinformation.contactIdGenerator;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.HibernateException;
import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class IdGenerator implements IdentifierGenerator {

	private String prefix;

	@SuppressWarnings("unchecked")
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		try {
			String query = String.format("select %s from %s",
					session.getEntityPersister(object.getClass().getName(), object).getIdentifierPropertyName(),
					object.getClass().getSimpleName());

			Stream<String> contactInfoIds = session.createQuery(query).stream();

			Long lastContactId = contactInfoIds.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max()
					.orElse(0L);

			return prefix + (lastContactId + 1);
		} catch (Exception ex) {
			System.out.println("Exception while generating the contactInfoId " + ex.toString());
		}

		return null;
	}

	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
		prefix = properties.getProperty("prefix");
	}

}
