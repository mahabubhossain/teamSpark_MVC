package edu.mum.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.mum.dao.UserCredentialsDao;
import edu.mum.domain.Authority;
import edu.mum.domain.UserCredentials;

@Repository
public class UserCredentialsDaoImpl extends GenericDaoImpl<UserCredentials> implements UserCredentialsDao {

	public UserCredentialsDaoImpl() {
		super.setDaoType(UserCredentials.class);
	}

	public UserCredentials findByUserName(String userName) {

		Query query = entityManager.createQuery("select m from Authentication m  where m.userName =:userName");
		return (UserCredentials) query.setParameter("userName", userName).getSingleResult();

	}

	public Authority findAuthority(String userName) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select a from Authority a  where a.username =:userName");
		return (Authority) query.setParameter("userName", userName).getSingleResult();
		// return null;
	}
}