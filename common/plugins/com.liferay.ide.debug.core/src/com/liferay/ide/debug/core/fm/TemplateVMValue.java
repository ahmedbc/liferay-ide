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
package com.liferay.ide.debug.core.fm;

import freemarker.debug.DebugModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;


/**
 * @author Gregory Amerson
 */
public class TemplateVMValue extends FMValue
{

    private IVariable[] variables;

    public TemplateVMValue( FMStackFrame stackFrame, DebugModel debugModel )
    {
        super( stackFrame, debugModel );
    }

    @Override
    public IVariable[] getVariables() throws DebugException
    {
        if( this.variables == null )
        {
            List<IVariable> vars = new ArrayList<IVariable>();

            try
            {
                vars.add( new FMVariable( this.stackFrame, "name", this.debugModel.get( "name" ) ) );
                vars.add
                (
                    new FMVariable( this.stackFrame, "configuration", this.debugModel.get( "configuration" ) )
                    {
                        public IValue getValue() throws DebugException
                        {
                            return new ConfigurationVMValue( this.stackFrame, this.debugModel );
                        };
                    }
                );
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }

            Collections.addAll( vars, super.getVariables() );

            this.variables = vars.toArray( new IVariable[ vars.size() ] );
        }

        return this.variables;
    }
}
