package org.yuval.requestsAnalyzer.handlers;

public interface ResourceHandler {
    // region Public Methods
    void printRequestsStatistics(String logContent) throws Exception;

    ResourceType getType();
    // endregion
}
