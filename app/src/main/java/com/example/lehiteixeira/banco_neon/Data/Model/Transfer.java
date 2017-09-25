package com.example.lehiteixeira.banco_neon.Data.Model;

/**
 * Created by Lehiteixeira on 04/09/17.
 */

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "ClienteId",
        "Valor",
        "Token",
        "Data"
})
public class Transfer {

    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("ClienteId")
    private Integer clienteId;
    @JsonProperty("Valor")
    private Double valor;
    @JsonProperty("Token")
    private String token;
    @JsonProperty("Data")
    private String data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("ClienteId")
    public Integer getClienteId() {
        return clienteId;
    }

    @JsonProperty("ClienteId")
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @JsonProperty("Valor")
    public Double getValor() {
        return valor;
    }

    @JsonProperty("Valor")
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @JsonProperty("Token")
    public String getToken() {
        return token;
    }

    @JsonProperty("Token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("Data")
    public String getData() {
        return data;
    }

    @JsonProperty("Data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


