package com.globant.aem.training.core;

import java.io.IOException;

import javax.jcr.Repository;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;

import org.apache.felix.scr.annotations.sling.SlingServlet;

import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.commons.json.JSONException;

import org.apache.sling.commons.json.JSONObject;

//@SlingServlet(resourceTypes = "geometrixx-outdoors/components/activities_page", selectors = "data", methods = "GET")
@SlingServlet(
    resourceTypes = "geometrixx-outdoors/components/page_home", 
    methods = "GET",
    selectors = "data")
public class MySafeMethodServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = -3960692666512058118L;

    @Reference
    private Repository repository;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
        throws ServletException, IOException {
        response.setHeader("Content-Type", "application/json");
        String[] keys = repository.getDescriptorKeys();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < keys.length; i++) {
            try {
                jsonObject.put(keys[i], repository.getDescriptor(keys[i]));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        response.getWriter().print(jsonObject.toString());
    }
}
