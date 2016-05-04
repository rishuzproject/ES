package com.elecnor.ecosystem.dao;

import java.util.List;

import com.elecnor.ecosystem.bean.RepresentativeDirectory;

public interface RepresentativeDirectoryDAO {

	public List<RepresentativeDirectory> getRepresentative(Integer id,Class<?>  className);

	public RepresentativeDirectory saveOrUpdateRepresentative(RepresentativeDirectory representative);

	public Integer deleteRepresentative(Integer id);
}
