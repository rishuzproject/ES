package com.elecnor.ecosystem.querybuilder;

import org.springframework.stereotype.Service;

@Service
public class JobsDetailQueryBuilder {

	private StringBuffer activeJobSearchQuery = new StringBuffer("from JobDetail ");

	public String appendUserRoleAndId(String userId, String userRole) {

		return activeJobSearchQuery.toString();
	}

	public String appendStatusActiveClause(StringBuilder jobSearchQuery) {
		jobSearchQuery.append(" STATUS = 'ACTIVE'");
		return jobSearchQuery.toString();
	}

	public String appendStatusInActiveClause(StringBuilder jobSearchQuery) {
		jobSearchQuery.append(" STATUS = 'INACTIVE'");
		return jobSearchQuery.toString();
	}

	public String appendStatusClosedClause(StringBuilder jobSearchQuery) {
		jobSearchQuery.append(" STATUS = 'CLOSED'");
		return jobSearchQuery.toString();
	}

	public String appendStatusDupicateClause(StringBuilder jobSearchQuery) {
		jobSearchQuery.append(" STATUS = 'DUPLICATE'");
		return jobSearchQuery.toString();
	}

}
