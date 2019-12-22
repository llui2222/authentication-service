package com.tpam.service.authentication.configuration.properties;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Validated
public class JWTProperties {

    @NotNull
    private SignatureAlgorithm signatureAlgorithm;
    @NotBlank
    private String signatureKey;
    @NotNull
    private Integer expiration;

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(final SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(final String signatureKey) {
        this.signatureKey = signatureKey;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(final Integer expiration) {
        this.expiration = expiration;
    }
}