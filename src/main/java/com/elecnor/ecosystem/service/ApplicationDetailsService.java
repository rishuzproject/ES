package com.elecnor.ecosystem.service;

import com.elecnor.ecosystem.bean.ApplicationDirectory;
import com.elecnor.ecosystem.bean.UserDetail;

public interface ApplicationDetailsService {
public String addOrUpdateAppDetail(ApplicationDirectory appDetailBean, UserDetail userDetail);
}
