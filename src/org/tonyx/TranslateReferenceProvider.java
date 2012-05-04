package org.tonyx;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.tonyxzt.language.core.ContentFilter;
import org.tonyxzt.language.core.ContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 29/03/12
 * Time: 21.51
 * To change this template use File | Settings | File Templates.
 */
public class TranslateReferenceProvider implements ContentProvider {
    public String retrieve(String word, String langIn, String langOut) throws Exception {
        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("query",word),
                new NameValuePair("src",langIn),
                new NameValuePair("dst",langOut)
        };

        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod("http://translate.reference.com/translate");
        method.setQueryString(params);

        client.executeMethod(method) ;
        String toReturn =  method.getResponseBodyAsString();
        return toReturn;
    }

    public String supportedLanguges() {
        return "Afrikaans \t af\n" +
                "Albanian \t sq\n" +
                "Arabic \t ar\n" +
                "Belarusian \t be\n" +
                "Bulgarian \t bg\n" +
                "Catalan \t ca\n" +
                "Chinese \t zh-CN\n" +
                "Croatian \t hr\n" +
                "Czech \t cs\n" +
                "Danish \t da\n" +
                "Dutch \t nl\n" +
                "English \t en\n" +
                "Estonian \t et\n" +
                "Filipino \t tl\n" +
                "Finnish \t fi\n" +
                "French \t fr\n" +
                "Galician \t gl\n" +
                "German \t de\n" +
                "Greek \t el\n" +
                "Haitian \t ht\n" +
                "Hebrew \t iw\n" +
                "Hindi \t hi\n" +
                "Hungarian \t hu\n" +
                "Icelandic \t is\n" +
                "Indonesian \t id\n" +
                "Irish \t ga\n" +
                "Italian \t it\n" +
                "Japanese \t ja\n" +
                "Korean \t ko\n" +
                "Latvian \t lv\n" +
                "Lithuanian \t lt\n" +
                "Macedonian \t mk\n" +
                "Malay \t ms\n" +
                "Maltese \t mt\n" +
                "Norwegian \t no\n" +
                "Persian \t fa\n" +
                "Polish \t pl\n" +
                "Portuguese \t pt\n" +
                "Portuguese(Portugal) \t pt-PT\n" +
                "Romanian \t ro\n" +
                "Russian \t ru\n" +
                "Serbian \t sr\n" +
                "Slovak \t sk\n" +
                "Slovenian \t sl\n" +
                "Spanish \t es\n" +
                "Swahili \t sw\n" +
                "Swedish \t sv\n" +
                "Thai \t th\n" +
                "Turkish \t tr\n" +
                "Ukrainian \t uk\n" +
                "Vietnamese \t vi\n" +
                "Welsh \t cy\n" +
                "Yiddish \t yi\n";
    }

    public String getInfoUrl() {
        return "http://dictionary.reference.com/help/terms.html";
    }
}

