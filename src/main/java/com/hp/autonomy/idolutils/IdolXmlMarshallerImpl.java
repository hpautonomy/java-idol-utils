/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils;

import com.autonomy.aci.client.services.AciErrorException;
import com.autonomy.aci.client.services.ProcessorException;
import com.hp.autonomy.idolutils.processors.AciErrorExceptionBuilder;
import com.hp.autonomy.types.idol.Error;
import com.hp.autonomy.types.idol.IdolJaxbMarshaller;
import com.hp.autonomy.types.idol.IdolJaxbMarshallerImpl;

public class IdolXmlMarshallerImpl extends IdolJaxbMarshallerImpl<AciErrorException, ProcessorException> implements IdolXmlMarshaller {
    public IdolXmlMarshallerImpl() {
        super(new IdolJaxbMarshaller.Function<Error, AciErrorException>() {
            @Override
            public AciErrorException apply(final Error error) {
                return new AciErrorExceptionBuilder(error).build();
            }
        }, new IdolJaxbMarshaller.BiFunction<String, Exception, ProcessorException>() {
            @Override
            public ProcessorException apply(final String message, final Exception cause) {
                return new ProcessorException(message, cause);
            }
        });
    }
}
