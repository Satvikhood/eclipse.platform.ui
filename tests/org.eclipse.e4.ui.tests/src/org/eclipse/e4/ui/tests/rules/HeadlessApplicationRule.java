/*******************************************************************************
 * Copyright (c) 2018 vogella GmbH and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Lars Vogel <Lars.Vogel@vogella.com> - initial API and implementation
 ******************************************************************************/

package org.eclipse.e4.ui.tests.extensions;

import org.eclipse.e4.core.commands.CommandServiceAddon;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.e4.ui.services.ContextServiceAddon;
import org.junit.jupiter.api.extension.*;

public class HeadlessApplicationExtension
        implements BeforeEachCallback, AfterEachCallback {

    private IEclipseContext applicationContext;

    public IEclipseContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        applicationContext = createApplicationContext();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (applicationContext != null) {
            applicationContext.dispose();
        }
    }

    protected IEclipseContext createApplicationContext() {
        final IEclipseContext appContext = E4Application.createDefaultContext();
        ContextInjectionFactory.make(CommandServiceAddon.class, appContext);
        ContextInjectionFactory.make(ContextServiceAddon.class, appContext);
        return appContext;
    }
}
