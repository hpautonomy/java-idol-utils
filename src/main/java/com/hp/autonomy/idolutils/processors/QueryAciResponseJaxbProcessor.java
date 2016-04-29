/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import com.hp.autonomy.idolutils.IdolXmlMarshaller;
import com.hp.autonomy.types.idol.QueryResponse;

@SuppressWarnings("WeakerAccess")
public class QueryAciResponseJaxbProcessor<T extends QueryResponse> implements Processor<T> {
    private static final long serialVersionUID = -1983490659468698548L;

    private final IdolXmlMarshaller idolXmlMarshaller;
    private final Class<T> responseDataType;
    private final Class<?> contentType;

    public QueryAciResponseJaxbProcessor(final IdolXmlMarshaller idolXmlMarshaller,
                                         final Class<T> responseDataType,
                                         final Class<?> contentType) {
        this.idolXmlMarshaller = idolXmlMarshaller;
        this.responseDataType = responseDataType;
        this.contentType = contentType;
    }

    @Override
    public T process(final AciResponseInputStream aciResponseInputStream) {
        return idolXmlMarshaller.parseIdolQueryResponseData(aciResponseInputStream, responseDataType, contentType);
    }
}
