/*
 * File   : $Source: /alkacon/cvs/alkacon/com.alkacon.opencms.formgenerator/src/com/alkacon/opencms/formgenerator/database/Messages.java,v $
 * Date   : $Date: 2007/12/21 14:34:01 $
 * Version: $Revision: 1.1 $
 *
 * This file is part of the Alkacon OpenCms Add-On Module Package
 *
 * Copyright (c) 2007 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * The Alkacon OpenCms Add-On Module Package is free software: 
 * you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The Alkacon OpenCms Add-On Module Package is distributed 
 * in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the Alkacon OpenCms Add-On Module Package.  
 * If not, see http://www.gnu.org/licenses/.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com.
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org.
 */
package com.alkacon.opencms.formgenerator.database;

import org.opencms.i18n.A_CmsMessageBundle;
import org.opencms.i18n.I_CmsMessageBundle;

/**
 * Convenience class to access the localized messages of this OpenCms package.<p> 
 * 
 * @author Achim Westermann 
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 7.0.4 
 */
public final class Messages extends A_CmsMessageBundle {
    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATAACCESS_UPLOADFILE_LOST_1 = "LOG_ERR_DATAACCESS_UPLOADFILE_LOST_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_CREATE_1 = "ERR_FILE_ARG_CREATE_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_EXISTS_1 = "ERR_FILE_ARG_EXISTS_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_IS_FILE_1 = "ERR_FILE_ARG_IS_FILE_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_NOT_READ_1 = "ERR_FILE_ARG_NOT_READ_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_NOT_FOUND_1 = "ERR_FILE_ARG_NOT_FOUND_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_IS_FOLDER_1 = "ERR_FILE_ARG_IS_FOLDER_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_NULL_0 = "ERR_FILE_ARG_NULL_0";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_ACCESS_1 = "ERR_FILE_ARG_ACCESS_1";

    /** Message constant for key in the resource bundle. */
    public static final String ERR_FILE_ARG_NOT_WRITE_1 = "ERR_FILE_ARG_NOT_WRITE_1";

    /** Name of the used resource bundle. */
    private static final String BUNDLE_NAME = "com.alkacon.opencms.formgenerator.database.messages";

    /** Static instance member. */
    private static final I_CmsMessageBundle INSTANCE = new Messages();

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATAACCESS_MODULE_MISSING_1 = "LOG_ERR_DATAACCESS_MODULE_MISSING_1";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATAACCESS_MODULE_PARAM_MISSING_2 = "LOG_ERR_DATAACCESS_MODULE_PARAM_MISSING_2";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATAACCESS_SQL_WRITE_FIELD_3 = "LOG_ERR_DATAACCESS_SQL_WRITE_FIELD_3";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATAACCESS_SQL_WRITE_SUBMISSION_1 = "LOG_ERR_DATAACCESS_SQL_WRITE_SUBMISSION_1";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATACCESS_SQL_CREATE_TABLE_RETURNCODE_2 = "LOG_ERR_DATACCESS_SQL_CREATE_TABLE_RETURNCODE_2";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_ERR_DATACCESS_SQL_READ_SUBMISSION_ID_1 = "LOG_ERR_DATACCESS_SQL_READ_SUBMISSION_ID_1";

    /** Message constant for key in the resource bundle. */
    public static final String LOG_INFO_DATAACESS_SQL_TABLE_NOTEXISTS_0 = "LOG_INFO_DATAACESS_SQL_TABLE_NOTEXISTS_0";

    /**
     * Hides the public constructor for this utility class.<p>
     */
    private Messages() {

        // hide the constructor
    }

    /**
     * Returns an instance of this localized message accessor.<p>
     * 
     * @return an instance of this localized message accessor
     */
    public static I_CmsMessageBundle get() {

        return INSTANCE;
    }

    /**
     * Returns the bundle name for this OpenCms package.<p>
     * 
     * @return the bundle name for this OpenCms package
     */
    public String getBundleName() {

        return BUNDLE_NAME;
    }
}