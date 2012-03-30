package org.tonyx;

import org.tonyxzt.language.core.ContentFilter;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 29/03/12
 * Time: 20.58
 * To change this template use File | Settings | File Templates.
 */
public class TranslateReferenceContentFilter implements ContentFilter {
    @Override
    public String filter(String s) {
        String[] splitted = s.split("<span class=\"BAB_CPPOSStyle\">");
        String toReturn = "";
        for (int i=1;i<splitted.length;i++) {
            toReturn+= splitted[i].replace("</span>","").replaceAll("<br.*\n","").replaceAll("<div.*\n","").replaceAll("</div>.*\n","").replaceAll("\n","");
            toReturn+="    -     ";
        }
        return toReturn.split("</div>")[0]+"\n";
    }
}
