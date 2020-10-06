package com.example.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "error", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			Thread.sleep(300);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		res.setStatus(500);
		throw new RuntimeException("System Error!");
	}
}
