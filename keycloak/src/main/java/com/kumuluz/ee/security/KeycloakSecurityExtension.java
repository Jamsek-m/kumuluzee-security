/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.security;

import com.kumuluz.ee.common.Extension;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.dependencies.*;
import com.kumuluz.ee.common.wrapper.KumuluzServerWrapper;

import java.util.logging.Logger;

/**
 * KumuluzEE framework extension for Keycloak based security
 *
 * @author Benjamin Kastelic
 */
@EeExtensionDef(name = "keycloak", group = EeExtensionGroup.SECURITY)
@EeComponentDependencies({
        @EeComponentDependency(EeComponentType.SERVLET),
        @EeComponentDependency(EeComponentType.CDI)
})
public class KeycloakSecurityExtension implements Extension {

    private static final Logger log = Logger.getLogger(KeycloakSecurityExtension.class.getName());

    @Override
    public void init(KumuluzServerWrapper kumuluzServerWrapper, EeConfig eeConfig) {
        log.info("Initialising security implemented by Keycloak.");

        try {
            Class.forName("org.keycloak.adapters.jetty.KeycloakJettyAuthenticator");
        } catch (ClassNotFoundException e) {
            log.severe("Keycloak Jetty Adapter not found, please include it as a dependency. " +
                    "The extension may not work as expected.");
        }
    }

    @Override
    public void load() {
        log.info("Initialised security implemented by Keycloak.");
    }
}
