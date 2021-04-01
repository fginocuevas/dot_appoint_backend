package com.thenogicode.appoint.appuser.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTypeEnum {
	
	AVAILABLE(1, "Available"),
	UNAVAILABLE(2, "Unavailable");
	
	private static final Map<Integer, StatusTypeEnum> intToEnumMap;
	
	static {
		intToEnumMap = Arrays.stream(values())
				.collect(Collectors.toMap(StatusTypeEnum::getValue, Function.identity()));
	}
	
	private final Integer value;
    private final String displayText;
    
    public static StatusTypeEnum fromInt(final Integer val) {
        return intToEnumMap.get(val);
    }

    public static Object[] integerValues() {
        return intToEnumMap.keySet().toArray();
    }
}
