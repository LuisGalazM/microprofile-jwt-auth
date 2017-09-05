/*
 * Copyright (c) 2016-2017 Contributors to the Eclipse Foundation
 *
 *  See the NOTICE file(s) distributed with this work for additional
 *  information regarding copyright ownership.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.eclipse.microprofile.jwt.tck.container.jaxrs;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 * Test that an attempt to inject a raw token value type into an @ApplicationScoped bean
 * generates a DeploymentException
 */
@ApplicationScoped
@RolesAllowed("Tester")
@Path("/endp")
public class AppScopedEndpoint {
    @Inject
    JsonWebToken jwt;
    @Inject
    @Claim(standard = Claims.iss)
    private String issuer;

    @GET
    @Path("/verify")
    public Response verifyInjectedIssuer(@QueryParam("iss") String iss) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
