package com.thenogicode.appoint.doctor.api.request;

import lombok.Data;

@Data
public class UpdateDoctorStatusRequest {
	
	private Long doctorId;
	private Integer statusType;

}
