package com.thenogicode.appoint.template.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.domain.StatusTypeEnum;
import com.thenogicode.appoint.template.data.StatusTypeData;
import com.thenogicode.appoint.util.EntityAdapterHelper;

@RestController
@RequestMapping("status")
public class StatusApiResource {
	
	@GetMapping(value="/template", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<StatusTypeData> retrieveTemplateRoleTypes() {
		return Arrays.stream(StatusTypeEnum.values())
				.map(EntityAdapterHelper::generateStatusTypeDataFrom)
				.collect(Collectors.toList());
	}

}
