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
package com.microsoftopentechnologies.intellij.runnable;

import com.intellij.openapi.project.Project;
import com.microsoftopentechnologies.intellij.AzureSettings;

import static com.microsoftopentechnologies.intellij.AzurePlugin.log;
import static com.microsoftopentechnologies.intellij.ui.messages.AzureBundle.message;

public class LoadAccountWithProgressBar extends AccountActionRunnable {
    private Project myProject;

    public LoadAccountWithProgressBar(Project myProject) {
        super(null);
        this.myProject = myProject;
    }

    @Override
    public void doTask() {
        try {
            AzureSettings.getSafeInstance(myProject).loadPublishDatas(this);
        } catch (Exception e) {
            log(message("error"), e);
        }
    }
}
