/*******************************************************************************
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/
package com.liferay.ide.server.core;

import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;
import org.eclipse.jdt.internal.launching.JavaSourceLookupDirector;


/**
 * @author Gregory Amerson
 */
@SuppressWarnings( "restriction" )
public class PortalSourceLookupDirector extends JavaSourceLookupDirector
{

    @Override
    public void initializeParticipants()
    {
        super.initializeParticipants();

        addParticipants( new ISourceLookupParticipant[] { new PortalSourceLookupParticipant() } );
    }

    @Override
    public boolean isFindDuplicates()
    {
        return true;
    }

}
