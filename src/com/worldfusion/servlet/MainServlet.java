package com.worldfusion.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.MysqlDatabaseConnection;

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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");
        requestDispatcher.forward(request, response);
	}


}
