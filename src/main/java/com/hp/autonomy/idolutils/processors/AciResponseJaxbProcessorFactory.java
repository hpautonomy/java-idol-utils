/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.AciErrorException;
import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.services.ProcessorException;
import com.hp.autonomy.types.idol.Error;
import com.hp.autonomy.types.idol.IdolResponseParser;

public class AciResponseJaxbProcessorFactory {
    protected final IdolResponseParser<AciErrorException, ProcessorException> idolResponseParser;

    public AciResponseJaxbProcessorFactory() {
        idolResponseParser = new IdolResponseParser<>(new IdolResponseParser.Function<Error, AciErrorException>() {
            @Override
            public AciErrorException apply(final Error error) {
                return new AciErrorExceptionBuilder(error).build();
            }
        }, new IdolResponseParser.BiFunction<String, Exception, ProcessorException>() {
            @Override
            public ProcessorException apply(final String message, final Exception cause) {
                return new ProcessorException(message, cause);
            }
        });
    }

    public <T> Processor<T> createAciResponseProcessor(final Class<T> responseDataType) {
        return new AciResponseJaxbProcessor<>(idolResponseParser, responseDataType);
    }

    public Processor<Void> createEmptyAciResponseProcessor() {
        return new EmptyAciResponseJaxbProcessor(idolResponseParser);
    }
}
