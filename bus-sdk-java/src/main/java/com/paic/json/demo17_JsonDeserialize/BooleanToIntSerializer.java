package com.paic.json.demo17_JsonDeserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BooleanToIntSerializer extends JsonSerializer<Boolean> {

	@Override
	public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if(value) {
			gen.writeNumber(1);
		} else {
			gen.writeNumber(0);
		}
	}

}
