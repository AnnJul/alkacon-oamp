/*
 * File   : $Source: /alkacon/cvs/alkacon/com.alkacon.opencms.formgenerator/src/com/alkacon/opencms/formgenerator/CmsFieldFactory.java,v $
 * Date   : $Date: 2007/12/21 14:34:00 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) 2002 - 2007 Alkacon Software GmbH (http://www.alkacon.com)
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
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.alkacon.opencms.formgenerator;

import org.opencms.main.CmsLog;
import org.opencms.main.OpenCms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.logging.Log;

/**
 * A factory to create form field instances of a specified type.<p>
 * 
 * Additional <code>{@link A_CmsField}</code> implementations may be specified in a 
 * file: "custom_form_field.properties" under "WEB-INF/classes/" of your 
 * web application. The format has to be as follows: 
 * 
 * <pre>
 * FIELDS= &lt;fieldtypename&gt;: &lt;fieldtypeclass&gt; [, &lt;fieldtypename&gt;:&lt;fieldtypeclass&gt;]*
 * </pre>
 * 
 * where &lt;fieldtypename&gt; is the visible name of the field that will 
 * be offered in the XML content editor and &gt;fieldtypeclass&gt; has to be 
 * a fully qualified classame of a class that implemets 
 * <code>{@link A_CmsField}</code>.
 * <p>
 * 
 * @author Thomas Weckert 
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 7.0.4 
 */
public final class CmsFieldFactory {

    /** Filename of the optional custom form field properties. */
    public static final String CUSTOM_FORM_FIELD_PROPERTIES = OpenCms.getSystemInfo().getAbsoluteRfsPathRelativeToWebInf(
        "classes" + File.separatorChar + "custom_form_field.properties");

    /** The log object for this class. */
    private static final Log LOG = CmsLog.getLog(CmsFieldFactory.class);

    /** The shared instance of the field factory. */
    private static CmsFieldFactory sharedInstance = null;

    /** The registered field types keyed by their type name. */
    private Map m_registeredFieldTypes;

    /**
     * Default constructor.<p>
     */
    private CmsFieldFactory() {

        super();

        m_registeredFieldTypes = new HashMap();

        // register all the standard OpenCms field types
        registerFieldType(CmsCheckboxField.getStaticType(), CmsCheckboxField.class.getName());
        registerFieldType(CmsEmailField.getStaticType(), CmsEmailField.class.getName());
        registerFieldType(CmsFileUploadField.getStaticType(), CmsFileUploadField.class.getName());
        registerFieldType(CmsHiddenField.getStaticType(), CmsHiddenField.class.getName());
        registerFieldType(CmsRadioButtonField.getStaticType(), CmsRadioButtonField.class.getName());
        registerFieldType(CmsSelectionField.getStaticType(), CmsSelectionField.class.getName());
        registerFieldType(CmsTextField.getStaticType(), CmsTextField.class.getName());
        registerFieldType(CmsTextareaField.getStaticType(), CmsTextareaField.class.getName());
        registerFieldType(CmsEmptyField.getStaticType(), CmsEmptyField.class.getName());
        registerFieldType(CmsPrivacyField.getStaticType(), CmsPrivacyField.class.getName());

        File propertyFile = null;
        try {

            // register all custom field types declared in a property file.
            // since custom fields are optional, the property file doesn't have to exist necessarily in the file system.
            // this file should contain a mapping of field type names to a Java classes separated by a colo ":", e.g.:
            // FIELDS=<fieldtype>:<java class>,...,<fieldtype>:<java class>

            propertyFile = new File(CUSTOM_FORM_FIELD_PROPERTIES);
            if (propertyFile.exists()) {

                ExtendedProperties fieldProperties = new ExtendedProperties();
                fieldProperties.load(new FileInputStream(propertyFile));

                Iterator i = fieldProperties.keySet().iterator();
                while (i.hasNext()) {

                    String key = (String)i.next();
                    if (!"FIELDS".equalsIgnoreCase(key)) {
                        continue;
                    }

                    String[] values = fieldProperties.getStringArray(key);
                    if ((values == null) || (values.length == 0)) {
                        continue;
                    }

                    for (int j = 0, n = values.length; j < n; j++) {

                        String field = values[j];
                        int index = field.indexOf(":");
                        if (index == -1) {
                            continue;
                        }

                        String fieldType = field.substring(0, index);
                        String fieldClass = field.substring(index + 1, field.length());
                        registerFieldType(fieldType, fieldClass);
                    }
                }
            }
        } catch (IOException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(Messages.get().getBundle().key(
                    Messages.LOG_ERR_READING_CUSTOM_FORM_FIELD_PROPERTIES_1,
                    propertyFile == null ? CUSTOM_FORM_FIELD_PROPERTIES : propertyFile.getAbsolutePath()), e);
            }
        }
    }

    /**
     * Returns the shared instance of the field factory.<p>
     * 
     * @return the shared instance of the field factory
     */
    public static synchronized CmsFieldFactory getSharedInstance() {

        if (sharedInstance == null) {
            sharedInstance = new CmsFieldFactory();
        }

        return sharedInstance;
    }

    /**
     * @see java.lang.Object#finalize()
     */
    protected void finalize() throws Throwable {

        try {
            if (m_registeredFieldTypes != null) {
                m_registeredFieldTypes.clear();
            }
        } catch (Throwable t) {
            // ignore
        }
        super.finalize();
    }

    /**
     * Returns an instance of a form field of the specified type.<p>
     * 
     * @param type the desired type of the form field.
     * @return the instance of a form field, or null if creating an instance of the class failed
     */
    protected A_CmsField getField(String type) {

        A_CmsField field = null;

        try {

            String className = (String)m_registeredFieldTypes.get(type);
            field = (A_CmsField)Class.forName(className).newInstance();
        } catch (Throwable t) {

            if (LOG.isErrorEnabled()) {
                LOG.error(Messages.get().getBundle().key(Messages.LOG_ERR_FIELD_INSTANTIATION_1, type), t);
            }
        }

        return field;
    }

    /**
     * Registers a class as a field type in the factory.<p>
     * 
     * @param type the type of the field
     * @param className the name of the field class
     * @return the previous class associated with this type, or null if there was no mapping before
     */
    private Object registerFieldType(String type, String className) {

        return m_registeredFieldTypes.put(type, className);
    }
}