package com.example.hello;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmCompilationMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmHeapPressureMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

@WebListener
public class MeterRegistryServletContextListener implements ServletContextListener {
	public static final String METER_REGISTRY_NAME = "meterRegistry";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		final ServletContext servletContext = event.getServletContext();
		final MeterRegistry meterRegistry = this.initializeMeterRegistry(servletContext);
		servletContext.setAttribute(METER_REGISTRY_NAME, meterRegistry);
	}

	MeterRegistry initializeMeterRegistry(ServletContext servletContext) {
		final MeterRegistry meterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
		// Register Binders
		new JvmGcMetrics().bindTo(meterRegistry);
		new JvmHeapPressureMetrics().bindTo(meterRegistry);
		new JvmMemoryMetrics().bindTo(meterRegistry);
		new JvmThreadMetrics().bindTo(meterRegistry);
		new JvmCompilationMetrics().bindTo(meterRegistry);
		new ClassLoaderMetrics().bindTo(meterRegistry);
		new UptimeMetrics().bindTo(meterRegistry);
		new ProcessorMetrics().bindTo(meterRegistry);
		new FileDescriptorMetrics().bindTo(meterRegistry);
		// If you want to configure common tags
		// meterRegistry.config().commonTags("app", "hello-servlet");
		return meterRegistry;
	}
}
