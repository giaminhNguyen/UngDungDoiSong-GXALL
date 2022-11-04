package com.ming6464.minhngph25430_assignmnet;

import com.ming6464.minhngph25430_assignmnet.DTO.ItemWeb;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderWeb {
    private List<ItemWeb> list;
    private ItemWeb obj;
    private boolean check;
    private String textContent;
    public List<ItemWeb> getListItemWeb(InputStream inputStream){
        try {
            XmlPullParserFactory xml = XmlPullParserFactory.newInstance();
            list = new ArrayList<>();
            xml.setNamespaceAware(true);
            XmlPullParser parser = xml.newPullParser();
            parser.setInput(inputStream,null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nameTag = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if(nameTag.equalsIgnoreCase("item")){
                            obj = new ItemWeb();
                            check = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(check)
                            textContent = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(check){
                            if(nameTag.equalsIgnoreCase("item")){
                                list.add(obj);
                                check = false;
                            }
                            else if(nameTag.equalsIgnoreCase("title"))
                                obj.setTitle(textContent);
                            else if (nameTag.equalsIgnoreCase("link"))
                                obj.setLink(textContent);
                        }
                        break;
                }
                parser.next();
                eventType = parser.getEventType();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
