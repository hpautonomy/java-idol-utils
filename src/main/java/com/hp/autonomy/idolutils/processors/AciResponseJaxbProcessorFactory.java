/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.hp.autonomy.idolutils.IdolXmlMarshaller;
import com.hp.autonomy.idolutils.IdolXmlMarshallerImpl;
import com.hp.autonomy.types.idol.QueryResponse;

@SuppressWarnings("WeakerAccess")
public class AciResponseJaxbProcessorFactory {
    protected final IdolXmlMarshaller marshaller;

    public AciResponseJaxbProcessorFactory() {
        marshaller = new IdolXmlMarshallerImpl();
    }

    public <T> Processor<T> createAciResponseProcessor(final Class<T> responseDataType) {
        return new AciResponseJaxbProcessor<>(marshaller, responseDataType);
    }

    public <T extends QueryResponse> Processor<T> createQueryAciResponseProcessor(final Class<T> responseDataType, final Class<?> contentType) {
        return new QueryAciResponseJaxbProcessor<>(marshaller, responseDataType, contentType);
    }

    public Processor<Void> createEmptyAciResponseProcessor() {
        return new EmptyAciResponseJaxbProcessor(marshaller);
    }

    public IdolXmlMarshaller getMarshaller() {
        return marshaller;
    }
}
