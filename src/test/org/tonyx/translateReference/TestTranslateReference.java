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
    public void filterShouldContaintTheHello() {
        // given
        String content = new TranslateReferenceStubbedContent().getContent();

        // when
        String filteredContent = new TranslateReferenceContentFilter().filter(content);

        // then
        Assert.assertTrue(filteredContent.contains("salve, salute"));
    }
}
