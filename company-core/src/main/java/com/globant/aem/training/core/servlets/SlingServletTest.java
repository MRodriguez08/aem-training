package com.globant.aem.training.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@SlingServlet(
    paths = {"/bin/training/slingservlettest1","/bin/training/slingservlettest2"},
    resourceTypes = "company/components/structure/page",
    selectors = "slinghello",
    extensions = "html",
    methods = "GET"
)
public class SlingServletTest extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("SlingServletTest saying hello!!");
    }

}
