package com.emoney.web.app;

import java.util.Calendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppSise implements ServletContextListener, Runnable {
	private static final Logger logger = LoggerFactory.getLogger(AppSise.class);
	
	private static final int MAX_SIZE = 3;
	private static final int STIME = 600000;
	
	public static AppSise This;
	
	private Thread thread;
	
	private Dbase[] dbase;
	private int wIdx;
	private int lIdx;
	private boolean is_first = true;
	
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == thread) {
			Calendar c = Calendar.getInstance();
			logger.info("START - HOUR: " + c.get(Calendar.HOUR_OF_DAY) + " / wIdx: " + wIdx + " / lIdx: " + lIdx + " / is_first: " + is_first);
			if( c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 16 || is_first ) {
				dbase[wIdx].update();
				lIdx = wIdx;
				wIdx = (wIdx+1)%MAX_SIZE;
				is_first = false;
			}
			logger.info("END - HOUR: " + c.get(Calendar.HOUR_OF_DAY) + " / wIdx: " + wIdx + " / lIdx: " + lIdx + " / is_first: " + is_first);
			try {
				Thread.sleep(STIME);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public AppSise() {
		wIdx = 0;
		lIdx = -1;
		dbase = new Dbase[MAX_SIZE];
		
		for( int i = 0; i < MAX_SIZE; i++ ) {
			dbase[i] = new Dbase();
		}
		
		This = this;
	}
	
	public Dbase getDbase() {
		if( lIdx < 0 ) {
			return null;
		} else {
			return dbase[lIdx];
		}
	}
	
	public void startDaemon() {
		if( thread == null ) {
	        thread = new Thread(this);
	        thread.setDaemon(true);
	    }
	    
	    if( !thread.isAlive() ) {
	        thread.start();
	    }
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		thread = null;
	}

	public void contextInitialized(ServletContextEvent arg0) {
		 startDaemon();
	}
}