package com.example.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.micrometer.core.instrument.MeterRegistry;

import static com.example.hello.MeterRegistryServletContextListener.METER_REGISTRY_NAME;

@WebServlet(name = "hello", urlPatterns = "/")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		final MeterRegistry meterRegistry = (MeterRegistry) this.getServletContext().getAttribute(METER_REGISTRY_NAME);
		res.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.print("Hello World!!");
		meterRegistry.counter("hello").increment();
		pw.flush();
	}
}
