package com.elecnor.ecosystem.dao;

import java.util.ArrayList;

import com.elecnor.ecosystem.bean.SovCommentsTable;

public interface SovCommentsTableDAO {

	public String addSOVComment(SovCommentsTable sovComment) throws Exception;

	public ArrayList<SovCommentsTable> getAllSOVComments(Long sovId) throws Exception;

}
