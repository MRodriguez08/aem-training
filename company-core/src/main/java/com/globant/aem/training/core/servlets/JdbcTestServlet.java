package com.globant.aem.training.core.servlets;

import com.day.commons.datasource.poolservice.DataSourcePool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(paths = "/bin/training/jdbc", methods = {"GET"})
public class JdbcTestServlet extends SlingSafeMethodsServlet {
    
    private static final String SAMPLE_QUERY = "select * from aem_test_entities";

    private static final String DATASOURCE_NAME = "mysqldbpool";

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTestServlet.class);

    @Reference
    private DataSourcePool dspService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            JSONArray items = new JSONArray();
            DataSource ds = (DataSource) dspService.getDataSource(DATASOURCE_NAME);

            if (ds != null) {
                final Connection connection = ds.getConnection();
                final Statement statement = connection.createStatement();
                final ResultSet resultSet = statement.executeQuery(SAMPLE_QUERY);
                int r = 0;
                while (resultSet.next()) {
                    r = r + 1;
                    JSONObject item = new JSONObject();
                    item.put("id", resultSet.getLong("id"));
                    item.put("name", resultSet.getString("name"));
                    items.put(item);
                }
                resultSet.close();
                connection.close();
                response.setContentType("application/json");
                response.getWriter().write(items.toString());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
