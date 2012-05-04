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
            String translation  = splitted[i].replace("</span>","");
            translation = translation.replaceAll("<br.*\n","").replaceAll("<div.*\n","");
            translation =  translation.replace("</span>","");
            translation = translation.replaceAll("<br.*\n","");
            translation = translation.replaceAll("<div.*\n","");
            translation = translation.replaceAll("</div>.*\n","");
            translation = translation.replaceAll("\n"," - ");

            translation = translation.split("<script")[0];

            toReturn+=translation;
            toReturn+=" - ";

        }

        toReturn =  toReturn.split("</div>")[0]+"\n";

        if (!toReturn.replaceAll(" ","" ).replaceAll("\n","").replaceAll("\r","").equals(""))
            return toReturn;

        return filterByTranslateTxtTag(s);
    }

    public String filterByTranslateTxtTag(String content) {
        return content.split("<div class=\"translateTxt\" >")[1].split("</div>")[0].replaceAll("\n","").replaceAll("\r","").replaceAll("^ *","").replaceAll(" *$","");
    }
}
