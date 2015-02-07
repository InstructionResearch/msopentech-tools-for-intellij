/**
 * Copyright 2014 Microsoft Open Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	 http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.microsoftopentechnologies.intellij.ui.libraries;

import javax.swing.*;


import com.intellij.ui.wizard.WizardNavigationState;
import com.intellij.ui.wizard.WizardStep;

import static com.microsoftopentechnologies.intellij.ui.messages.AzureBundle.message;

public class LibraryPropertiesStep extends WizardStep<AddLibraryWizardModel> {

    private LibraryPropertiesPanel libraryPropertiesPanel;
    private final AddLibraryWizardModel myModel;

    public LibraryPropertiesStep(String title, final AddLibraryWizardModel model) {
        super(title, message("libraryPropertiesDesc"));
        myModel = model;
        libraryPropertiesPanel = new LibraryPropertiesPanel(model);
    }

    @Override
    public JComponent prepare(final WizardNavigationState state) {
        state.FINISH.setEnabled(true);
        return libraryPropertiesPanel.prepare(state);
    }

    @Override
    public boolean onFinish() {
        return  (libraryPropertiesPanel.onFinish() && super.onFinish());
    }
}