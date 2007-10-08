/*
 * File   : $Source: /alkacon/cvs/alkacon/com.alkacon.opencms.newsletter/src/com/alkacon/opencms/newsletter/admin/CmsEditSubscriberDialog.java,v $
 * Date   : $Date: 2007/10/08 15:38:46 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (c) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.alkacon.opencms.newsletter.admin;

import org.opencms.file.CmsUser;
import org.opencms.jsp.CmsJspActionElement;
import org.opencms.main.CmsException;
import org.opencms.workplace.tools.accounts.A_CmsEditUserDialog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * Dialog to edit new or existing subscriber in the administration view.<p>
 * 
 * @author Michael Moossen 
 * 
 * @version $Revision: 1.1 $ 
 * 
 * @since 6.0.0 
 */
public class CmsEditSubscriberDialog extends A_CmsEditUserDialog {

    /**
     * Public constructor with JSP action element.<p>
     * 
     * @param jsp an initialized JSP action element
     */
    public CmsEditSubscriberDialog(CmsJspActionElement jsp) {

        super(jsp);
    }

    /**
     * Public constructor with JSP variables.<p>
     * 
     * @param context the JSP page context
     * @param req the JSP request
     * @param res the JSP response
     */
    public CmsEditSubscriberDialog(PageContext context, HttpServletRequest req, HttpServletResponse res) {

        this(new CmsJspActionElement(context, req, res));
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditUserDialog#createUser(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
     */
    protected CmsUser createUser(String name, String pwd, String desc, Map info) throws CmsException {

        return getCms().createUser(name, pwd, desc, info);
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditUserDialog#getListClass()
     */
    protected String getListClass() {

        return CmsSubscribersList.class.getName();
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditUserDialog#getListRootPath()
     */
    protected String getListRootPath() {

        return "/accounts/orgunit/subscribers";
    }

    /**
     * @see org.opencms.workplace.CmsWorkplace#initMessages()
     */
    protected void initMessages() {

        // add specific dialog resource bundle
        addMessages(Messages.get().getBundleName());
        // add default resource bundles
        super.initMessages();
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditUserDialog#isEditable(CmsUser)
     */
    protected boolean isEditable(CmsUser user) {

        return true;
    }

    /**
     * @see org.opencms.workplace.tools.accounts.A_CmsEditUserDialog#writeUser(org.opencms.file.CmsUser)
     */
    protected void writeUser(CmsUser user) throws CmsException {

        getCms().writeUser(user);
    }
}