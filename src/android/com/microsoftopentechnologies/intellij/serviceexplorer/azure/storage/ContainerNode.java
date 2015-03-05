/**
 * Copyright 2014 Microsoft Open Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.microsoftopentechnologies.intellij.serviceexplorer.azure.storage;


import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;
import com.microsoftopentechnologies.intellij.helpers.azure.AzureCmdException;
import com.microsoftopentechnologies.intellij.helpers.azure.sdk.AzureSDKManagerImpl;
import com.microsoftopentechnologies.intellij.helpers.storage.BlobExplorerFileEditorProvider;
import com.microsoftopentechnologies.intellij.model.storage.BlobContainer;
import com.microsoftopentechnologies.intellij.model.storage.BlobDirectory;
import com.microsoftopentechnologies.intellij.model.storage.BlobItem;
import com.microsoftopentechnologies.intellij.model.storage.StorageAccount;
import com.microsoftopentechnologies.intellij.serviceexplorer.Node;
import com.microsoftopentechnologies.intellij.serviceexplorer.NodeActionEvent;
import com.microsoftopentechnologies.intellij.serviceexplorer.NodeActionListener;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.*;

public class ContainerNode extends Node {

    private static final String CONTAINER_MODULE_ID = ContainerNode.class.getName();
    private static final String ICON_PATH = "container.png";

    private final BlobContainer blobContainer;
    private final StorageAccount storageAccount;

    public ContainerNode(final Node parent, StorageAccount sa, BlobContainer bc) {
        super(CONTAINER_MODULE_ID, bc.getName(), parent, ICON_PATH, true);

        blobContainer = bc;
        storageAccount = sa;

        addClickActionListener(new NodeActionListener() {
            @Override
            public void actionPerformed(NodeActionEvent e) {
                /*
                try {
                    BlobDirectory rootDirectory = AzureSDKManagerImpl.getManager().getRootDirectory(storageAccount, blobContainer);

                    for(BlobItem bi :  AzureSDKManagerImpl.getManager().getBlobItems(storageAccount, rootDirectory)){
                        if(bi instanceof BlobDirectory) {


                            for(BlobItem bidir :  AzureSDKManagerImpl.getManager().getBlobItems(storageAccount, (BlobDirectory) bi)){
                                bidir.getName();
                            }

                        } else {
                            bi.getName();
                        }


                    }

                } catch (AzureCmdException e1) {
                    e1.printStackTrace();
                }
                */

                LightVirtualFile containerVirtualFile = new LightVirtualFile(blobContainer.getName());
                containerVirtualFile.putUserData(BlobExplorerFileEditorProvider.CONTAINER_KEY, blobContainer);
                containerVirtualFile.putUserData(BlobExplorerFileEditorProvider.STORAGE_KEY, storageAccount);

                FileEditorManager.getInstance(getProject()).openEditor(new OpenFileDescriptor(getProject(), containerVirtualFile), true);
            }
        });
    }

}
