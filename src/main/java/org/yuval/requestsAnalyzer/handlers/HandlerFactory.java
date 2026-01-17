package org.yuval.requestsAnalyzer.handlers;

public class HandlerFactory {
    //region Public Methods
    public static ResourceHandler createHandler(ResourceType resourceType) {
        if (resourceType == null) {
            throw new IllegalArgumentException("ResourceType cannot be null");
        }

        if (resourceType == ResourceType.APACHE) {
            return new ApacheHandler();
        }

        throw new IllegalArgumentException("Unsupported ResourceType: " + resourceType);
    }
    // endregion
}
