package com.elecnor.ecosystem.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elecnor.ecosystem.bean.AddressDetails;
import com.elecnor.ecosystem.bean.UserDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AddressDetailsHelper {

	public List<AddressDetails> getAddressDetailFromJson(String json,
			UserDetail userDetails, String action) {
		AddressDetails addressDetail = null;
		List<AddressDetails> addressList = new ArrayList<AddressDetails>();
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(json);
			JsonNode arrNode = jsonNode.get("addressData");
			JsonNode addressArr = arrNode.get("address");
			JsonNode moduleName = arrNode.get("moduleName");
			JsonNode moduleId = arrNode.get("moduleId");
			if ((addressArr!=null)&&(addressArr.isArray())) {
				for (JsonNode tempArr : addressArr) {
					if ((tempArr!=null)&&tempArr.isArray()) {
						for (JsonNode objNode : tempArr) {
							addressDetail = new AddressDetails();
							addressDetail.setAddress_line_1(objNode.get(
									"address_line_1").asText());
							addressDetail.setAddress_line_2(objNode.get(
									"address_line_2").asText());
							addressDetail.setState(objNode.get("state")
									.asText());
							addressDetail.setCity(objNode.get("city").asText());
							addressDetail.setZipCode(objNode.get("zipCode")
									.asText());
                            addressDetail.setAddress_type(objNode.get("addressType").asText());
							addressDetail.setModuleId(moduleId.asLong());
							addressDetail.setModuleName(moduleName.asText()
									.toUpperCase());
							if (null == objNode.get("addressId")
									|| objNode.get("addressId").asLong() == 0) {
								addressDetail.setSubmittedBy(userDetails);
								addressDetail.setSubmittedDate(new Date());
							} else {
								addressDetail.setAddressId(objNode.get(
										"addressId").asLong());
								addressDetail.setUpdatedBy(userDetails);
								addressDetail.setUpdatedDate(new Date());
							}
							addressDetail.setStatus("ACTIVE");
							addressList.add(addressDetail);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return addressList;
	}
}
