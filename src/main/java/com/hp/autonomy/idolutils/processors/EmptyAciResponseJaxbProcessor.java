/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import com.hp.autonomy.idolutils.IdolXmlMarshaller;

/**
 * Generic processor for handling Idol responses.
 * Note that this uses DOM processing behind the scenes so should not be used for very large responses.
 */
@SuppressWarnings("WeakerAccess")
public class EmptyAciResponseJaxbProcessor implements Processor<Void> {
    private static final long serialVersionUID = -1983490659468698548L;

    private final IdolXmlMarshaller idolXmlMarshaller;

    public EmptyAciResponseJaxbProcessor(final IdolXmlMarshaller idolXmlMarshaller) {
        this.idolXmlMarshaller = idolXmlMarshaller;
    }

    @Override
    public Void process(final AciResponseInputStream aciResponseInputStream) {
        idolXmlMarshaller.parseIdolResponse(aciResponseInputStream);
        return null;
    }
}
