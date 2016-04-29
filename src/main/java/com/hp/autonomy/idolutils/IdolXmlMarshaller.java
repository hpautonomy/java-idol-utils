/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.idolutils;

import com.autonomy.aci.client.services.AciErrorException;
import com.autonomy.aci.client.services.ProcessorException;
import com.hp.autonomy.types.idol.IdolJaxbMarshaller;

public interface IdolXmlMarshaller extends IdolJaxbMarshaller<AciErrorException, ProcessorException> {
}
