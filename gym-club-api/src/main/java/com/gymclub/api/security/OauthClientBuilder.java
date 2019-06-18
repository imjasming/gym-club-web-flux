package com.gymclub.api.security;

/**
 * @author Xiaoming.
 * Created on 2019/06/18 20:40.
 */
public final class OauthClientBuilder {
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String autoapprove;
    private String additionalInformation;

    private OauthClientBuilder() {
    }

    public static OauthClientBuilder anOauthClient() {
        return new OauthClientBuilder();
    }

    public OauthClientBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public OauthClientBuilder withResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    public OauthClientBuilder withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public OauthClientBuilder withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public OauthClientBuilder withAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public OauthClientBuilder withWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
        return this;
    }

    public OauthClientBuilder withAuthorities(String authorities) {
        this.authorities = authorities;
        return this;
    }

    public OauthClientBuilder withAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        return this;
    }

    public OauthClientBuilder withRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
        return this;
    }

    public OauthClientBuilder withAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
        return this;
    }

    public OauthClientBuilder withAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public OauthClient build() {
        OauthClient oauthClient = new OauthClient();
        oauthClient.setClientId(clientId);
        oauthClient.setResourceIds(resourceIds);
        oauthClient.setClientSecret(clientSecret);
        oauthClient.setScope(scope);
        oauthClient.setAuthorizedGrantTypes(authorizedGrantTypes);
        oauthClient.setWebServerRedirectUri(webServerRedirectUri);
        oauthClient.setAuthorities(authorities);
        oauthClient.setAccessTokenValidity(accessTokenValidity);
        oauthClient.setRefreshTokenValidity(refreshTokenValidity);
        oauthClient.setAutoapprove(autoapprove);
        oauthClient.setAdditionalInformation(additionalInformation);
        return oauthClient;
    }
}
