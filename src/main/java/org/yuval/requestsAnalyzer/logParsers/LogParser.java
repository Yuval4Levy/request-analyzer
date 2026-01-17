package org.yuval.requestsAnalyzer.logParsers;

import org.yuval.requestsAnalyzer.objects.RequestData;

import java.util.ArrayList;

public interface LogParser {
    // region Methods
    ArrayList<RequestData> getRequestDataList(String logContent) throws Exception;

    LogParserType getType();
    // endregion
}
