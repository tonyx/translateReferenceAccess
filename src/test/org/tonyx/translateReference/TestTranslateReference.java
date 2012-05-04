package test.org.tonyx.translateReference;

import junit.framework.Assert;
import org.junit.Test;
import org.tonyx.TranslateReferenceContentFilter;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 30/03/12
 * Time: 10.44
 * To change this template use File | Settings | File Templates.
 */
public class TestTranslateReference {

    @Test
    public void testContainsTheWords() {
        // given
        StubTemplateOriginalPage originalPage = new StubTemplateOriginalPage(" salute, ciao; ehi; (Tel) pronto; ohibò\n");

        // when            Браво
        String html = originalPage.getHtml();
        String filteredContent = new TranslateReferenceContentFilter().filter(html);

        // then
        //Assert.assertEquals("",filteredContent);
        Assert.assertTrue(filteredContent.contains("salute, ciao"));
    }
}
