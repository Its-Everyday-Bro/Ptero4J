package com.stanjg.ptero4j;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class PteroAPIBuilder {

    private String publicToken;
    private String privateToken;
    private String baseUrl;
    private boolean withCache;
    private int cacheSize;

    public PteroAPIBuilder() {
        withCache = true;
        cacheSize = 10;
    }

    public PteroAPIBuilder withPublicToken(String publicToken) {
        this.publicToken = publicToken;
        return this;
    }

    public PteroAPIBuilder withPrivateToken(String privateToken) {
        this.privateToken = privateToken;
        return this;
    }

    public PteroAPIBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public PteroAPIBuilder withCaching(boolean withCache) {
        this.withCache = withCache;
        return this;
    }

    public PteroAPIBuilder withCacheSize(int size) {
        this.cacheSize = size;
        return this;
    }

    public PteroAPI build() {
        if (publicToken == null || publicToken.length() == 0 ||privateToken == null || privateToken.length() ==0)
            throw new IllegalArgumentException("Invalid public/private tokens given");
        if (baseUrl == null)
            throw new IllegalArgumentException("No base url given");
        if (!baseUrl.endsWith("/"))
            baseUrl += "/";

        return new PteroAPI(publicToken, privateToken, baseUrl, withCache, cacheSize);
    }

}
