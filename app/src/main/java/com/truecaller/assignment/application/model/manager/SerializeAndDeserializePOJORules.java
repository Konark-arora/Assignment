package com.truecaller.assignment.application.model.manager;

import android.content.Context;

import com.truecaller.assignment.application.model.data.AppData;

import java.io.IOException;

/**
 * Interface for Serializing and deserializing POJO rules
 */
public interface SerializeAndDeserializePOJORules {
    /**
     * This method returns de-serialized object. user should cast it into appropriate type.
     * @param context
     * @param serializedFileName key to retrieve particular object.
     * @return user object in the form of Object.class, user should cast it.
     * @throws IOException
     */
    public AppData getDeSerializedBeanObject(Context context, String serializedFileName) throws IOException;
    /**
     * This method save user object to internal memory (in the form of File system) of application.
     * @param context
     * @param serializedFileName Key to save a Object into file system.
     * @param beanObject Object-value to save in file syetem.
     * @throws IOException
     */
    public void saveSerializedBeanObject(Context context, String serializedFileName, AppData beanObject) throws IOException;
    /**
     *
     * @param context
     * @param serializedFileName
     */
    public boolean cleanSerializedFile(Context context, String serializedFileName);
}
