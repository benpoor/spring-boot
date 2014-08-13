import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * Created by benpoor2008 on 2014/8/13.
 */
public class Dom4jTest {
    public static void main(String[] args){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "wwww");
        param.put("age", "15");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> p1 = new HashMap<String, Object>();
        p1.put("a1","1111");
        p1.put("a2","2222");
        Map<String, Object> p2 = new HashMap<String, Object>();
        p2.put("a1","1111");
        p2.put("a2","2222");
        list.add(p1);
        list.add(p2);
        param.put("list", list);
        Document doc = createDoc(param);
        System.out.println(doc.asXML());
    }

    public static Document createDoc(Map<String, Object> param){
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");

        Set<String> keys = param.keySet();
        for(String key:keys){
            Object obj = param.get(key);
            if(obj instanceof String){
                root.addElement(key).addText(obj.toString());
            }
            if(obj instanceof List){
                Element el = root.addElement(key);
                for(Map<String, Object> map:(ArrayList<Map<String, Object>>)obj){
                    Element tel = el.addElement("tag");
                    Set<String> tkeys = map.keySet();
                    for(String tkey:tkeys){
                        tel.addElement(tkey).addText(map.get(tkey).toString());
                    }
                }
            }

        }


        return doc;
    }
}
