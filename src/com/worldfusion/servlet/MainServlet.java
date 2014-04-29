package com.worldfusion.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worldfusion.dao.LastUpdateFinder;
import com.worldfusion.dao.ReferenceCountFinder;
import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.MysqlDatabaseConnection;
import com.worldfusion.models.ReferenceCount;
import com.worldfusion.models.ReferenceCountType;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseConnection databaseConnection;
    
    @Override
    public void init() throws ServletException {
        super.init();
        databaseConnection = new MysqlDatabaseConnection("localhost", "programming_assignment", "root");
    }
    
    @Override
    public void destroy() {
        super.destroy();
        databaseConnection.close();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReferenceCount> cancerReferences = new ReferenceCountFinder(databaseConnection, ReferenceCountType.CANCER).getAllReferenceCounts();
        request.setAttribute("cancerReferences", cancerReferences);

        List<ReferenceCount> anticancerReferences = new ReferenceCountFinder(databaseConnection, ReferenceCountType.ANTI_CANCER).getAllReferenceCounts();
        request.setAttribute("anticancerReferences", anticancerReferences);
        
        Date lastUpdate = new LastUpdateFinder(databaseConnection).getLastUpdate();
        request.setAttribute("lastUpdateDate", lastUpdate);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");
        requestDispatcher.forward(request, response);
    }


}
