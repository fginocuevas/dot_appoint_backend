package com.thenogicode.appoint.template.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thenogicode.appoint.appuser.domain.RoleTypeEnum;
import com.thenogicode.appoint.template.data.RoleTypeData;
import com.thenogicode.appoint.util.EntityAdapterHelper;

@RestController
@RequestMapping("role")
public class RoleApiResource {
	
	@GetMapping(value="/template", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<RoleTypeData> retrieveTemplateRoleTypes() {
		return Arrays.stream(RoleTypeEnum.values())
				.map(EntityAdapterHelper::generateRoleTypeDataFrom)
				.collect(Collectors.toList());
	}

}
