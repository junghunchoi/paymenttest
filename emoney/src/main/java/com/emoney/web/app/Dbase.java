package com.emoney.web.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.emoney.web.vo.StockInfo;

public class Dbase {
	private Hashtable<String, StockInfo> ht;
	
	private static final Logger logger = LoggerFactory.getLogger(Dbase.class);
	
	public StockInfo getStock(String code) {
		if( ht.get(code) != null) {
			return ht.get(code);
		}
		return new StockInfo();
	}

	public void update() {
		BufferedReader buff = null;
		ht = new Hashtable<String, StockInfo>();
		
		try {
			String path = System.getProperty("catalina.home") + "/data";
			FileReader file = new FileReader(path+"/sise.txt");
			buff = new BufferedReader(file);
			
			String data = "";
			String dataAll = "";
			boolean eof = false;
			
			while( !eof ) {
				data = buff.readLine();
				if( data == null ) {
					eof = true;
				} else {
					dataAll += data;
				}
			}
			
			StringTokenizer equityToken = new StringTokenizer(dataAll, "|");
			while( equityToken.hasMoreTokens() ) {
				StockInfo sInfo = new StockInfo();
				String code = equityToken.nextToken();
				
				if( code.length() != 7 || code.charAt(0) != 'A' ) {
					logger.error("code[{}]", code);
					continue;
				}
				sInfo.setCode(code.substring(1, 7));
				sInfo.setClose(Integer.parseInt(equityToken.nextToken()));
				sInfo.setChange_price(Integer.parseInt(equityToken.nextToken()));
				sInfo.setRate(Double.parseDouble(equityToken.nextToken()));
				sInfo.setOpen(Integer.parseInt(equityToken.nextToken()));
				sInfo.setHigh(Integer.parseInt(equityToken.nextToken()));
				sInfo.setLow(Integer.parseInt(equityToken.nextToken()));
				equityToken.nextToken();
				
				ht.put(sInfo.getCode(), sInfo);
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if( buff != null ) {
				try {
					buff.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}