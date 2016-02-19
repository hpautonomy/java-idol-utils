/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils.processors;

import com.autonomy.aci.client.services.AciErrorException;
import com.autonomy.aci.client.services.Processor;
import com.autonomy.aci.client.services.ProcessorException;
import com.autonomy.aci.client.transport.AciResponseInputStream;
import com.hp.autonomy.types.idol.IdolResponseParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Generic processor for handling Idol responses.
 * Note that this uses DOM processing behind the scenes so should not be used for very large responses.
 */
public class EmptyAciResponseJaxbProcessor implements Processor<Void> {
    private static final long serialVersionUID = -1983490659468698548L;

    private final IdolResponseParser<AciErrorException, ProcessorException> idolResponseParser;

    public EmptyAciResponseJaxbProcessor(final IdolResponseParser<AciErrorException, ProcessorException> idolResponseParser) {
        this.idolResponseParser = idolResponseParser;
    }

    @Override
    public Void process(final AciResponseInputStream aciResponseInputStream) {
        final String xml;
        try {
            xml = IOUtils.toString(aciResponseInputStream, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            throw new ProcessorException("Error running ACI command", e);
        }

        idolResponseParser.parseIdolResponse(xml);
        return null;
    }
}
