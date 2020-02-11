package org.folio.modusers.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "value"})
public class Parameter {
	@JsonProperty("key")
	private String key;
	@JsonProperty("value")
	private String value;
	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap();

	public Parameter() {
	}

	@JsonProperty("key")
	public String getKey() {
		return this.key;
	}

	@JsonProperty("key")
	public void setKey(String var1) {
		this.key = var1;
	}

	public Parameter withKey(String var1) {
		this.key = var1;
		return this;
	}

	@JsonProperty("value")
	public String getValue() {
		return this.value;
	}

	@JsonProperty("value")
	public void setValue(String var1) {
		this.value = var1;
	}

	public Parameter withValue(String var1) {
		this.value = var1;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String var1, Object var2) {
		this.additionalProperties.put(var1, var2);
	}

	public Parameter withAdditionalProperty(String var1, Object var2) {
		this.additionalProperties.put(var1, var2);
		return this;
	}
}
