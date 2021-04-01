package com.thenogicode.appoint.appuser.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RoleTypeEnum {
	
	SCHEDULER(1, "Scheduler"), 
	DOCTOR(2, "Doctor");
	
	private static final Map<Integer, RoleTypeEnum> intToEnumMap;
	
	static {
		intToEnumMap = Arrays.stream(values())
				.collect(Collectors.toMap(RoleTypeEnum::getValue, Function.identity()));
	}
	
	private final Integer value;
    private final String displayText;
	
    public static RoleTypeEnum fromInt(final Integer val) {
        return intToEnumMap.get(val);
    }

    public static Object[] integerValues() {
        return intToEnumMap.keySet().toArray();
    }

}
