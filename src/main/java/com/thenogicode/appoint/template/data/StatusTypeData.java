package com.thenogicode.appoint.template.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusTypeData {
	
	private final Integer value;
    private final String displayText;

}
