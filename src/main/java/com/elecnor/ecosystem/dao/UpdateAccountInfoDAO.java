package com.elecnor.ecosystem.dao;

public interface UpdateAccountInfoDAO {

	String updatePassword(String email, String encPassword) throws Exception;

}
