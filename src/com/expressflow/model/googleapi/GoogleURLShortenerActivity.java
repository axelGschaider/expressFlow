package com.expressflow.model.googleapi;

import java.util.Iterator;

import org.jdom.Element;
import org.jdom.Text;

import com.expressflow.engine.xml.ModelSingleton;
import com.expressflow.model.Activity;
import com.expressflow.model.Variable;
import com.expressflow.utils.NameUtil;

public class GoogleURLShortenerActivity extends Activity {
	
	private String longUrl;
	private Variable longUrlVar;
	private String shortUrl;
	private Variable shortUrlVar;
	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	public Variable getLongUrlVar() {
		return longUrlVar;
	}
	public void setLongUrlVar(Variable longUrlVar) {
		this.longUrlVar = longUrlVar;
	}
	public Variable getShortUrlVar() {
		return shortUrlVar;
	}
	public void setShortUrlVar(Variable shortUrlVar) {
		this.shortUrlVar = shortUrlVar;
	}
	public static GoogleURLShortenerActivity fromXML(Element element){
		GoogleURLShortenerActivity gusa = new GoogleURLShortenerActivity();
		Iterator<Element> iter = element.getChildren().iterator();
		while(iter.hasNext()){
			Element current = iter.next();
			String name = current.getAttributeValue("name");
			if(name.toUpperCase().equalsIgnoreCase("SHORTURL")){
				if(element.getContent().size() > 0){
					Element shortVar = (Element)current.getContent().get(0);
					gusa.setShortUrl(shortVar.getValue());
				}
			}
			if(name.toUpperCase().equalsIgnoreCase("LONGURL")){
				if(element.getContent().size() > 0){
					Element longVar = (Element)current.getContent().get(0);
					gusa.setLongUrl(longVar.getValue());
				}
			}
		}

		return gusa;
	}
}
