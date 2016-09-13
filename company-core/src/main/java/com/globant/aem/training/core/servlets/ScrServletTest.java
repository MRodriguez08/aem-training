package com.globant.aem.training.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
 
@Component(metatype = true)
@Service(value = Servlet.class)
@Properties({
    @Property(name = "sling.servlet.paths", value = "/bin/training/slingservlettest3"),
    @Property(name = "sling.servlet.resourceTypes", value = "company/components/structure/page"),
    @Property(name = "sling.servlet.selectors", value = "scrhello"),
    @Property(name = "sling.servlet.extensions", value = "html"),
    @Property(name = "sling.servlet.methods", value = "GET")
})
public class ScrServletTest extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("ScrServletTest saying hello!!");
    }

}
