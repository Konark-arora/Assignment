package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

public class MultiPartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    private static final String FILE_PART_NAME = "user_image";//user_image,profileImage
    //private static final String STRING_PART_NAME = "text";

    private final Response.Listener<String> mListener;
    private final File mFilePart;
    //private final String mStringPart;
    Map<String, String> params;

    public MultiPartRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, File file, Map<String, String> inputParams)
    {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        params = inputParams;
        buildMultiPartEntity();
    }

    private void buildMultiPartEntity()
    {
        if(mFilePart!=null){
            ContentBody cbFile = new FileBody(mFilePart, "image/jpeg");
            entity.addPart(FILE_PART_NAME, cbFile);
        }

        Iterator iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            try
            {
                entity.addPart(mapEntry.getKey().toString(), new StringBody(mapEntry.getValue().toString()));
            }
            catch (UnsupportedEncodingException e)
            {
                VolleyLog.e("UnsupportedEncodingException");
            }
        }

    }

    @Override
    public String getBodyContentType()
    {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            entity.writeTo(bos);
        }
        catch (IOException e)
        {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        //return Response.success("Uploaded", getCacheEntry());
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response)
    {
        mListener.onResponse(response);
    }
}