package acceptance.com.translateReferencePlugin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.tonyx.TranslateReferenceContentFilter;
import org.tonyx.TranslateReferenceProvider;
import org.tonyxzt.language.core.GenericDictionary;
import org.tonyxzt.language.core.Translator;
import org.tonyxzt.language.io.OutStream;
import org.tonyxzt.language.util.BrowserActivator;
import org.tonyxzt.language.util.FakeBrowserActivator;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 29/03/12
 * Time: 21.50
 * To change this template use File | Settings | File Templates.
 */
public class TranslateReferencePluginTest {
    Map<String,GenericDictionary> mapDictionaries;
    private Translator translator;
    OutStream ios;
    BrowserActivator browserActivator;

    @Before
    public void SetUp() {
        browserActivator = mock(FakeBrowserActivator.class);
        ios = mock(OutStream.class);

        mapDictionaries = new HashMap<String,GenericDictionary>(){
            {
                put("translateDic",new GenericDictionary("translateDic",new TranslateReferenceProvider(),new TranslateReferenceContentFilter()));
            }
        };
        translator = new Translator(mapDictionaries,browserActivator,ios);
    }

    @Test
    public void testContainsShould() throws Exception {
        ArgumentMatcher<String> dovere = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("dovere");
            }
        };
        translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","should"});
        translator.doAction();

        verify(ios).output(argThat(dovere));
    }

    @Test
    public void testContaintContainsMultipleLines() throws Exception {
        ArgumentMatcher<String> corsa = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("corsa");
            }
        };
        translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","run"});
        translator.doAction();

        verify(ios).output(argThat(corsa));
    }

    @Test
    public void testContaintContainsShould() throws Exception {
        ArgumentMatcher<String> salve = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("salve, salute, ciao");
            }
        };
        translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","hello"});
        translator.doAction();

        verify(ios).output(argThat(salve));
    }

    @Test
    public void shouldRemoveTags() throws Exception {
        ArgumentMatcher<String> htmlTags = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return !((String)object).contains("<")&&(!((String)object).contains(">"));
            }
        };
        translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","hello"});
        translator.doAction();

        verify(ios).output(argThat(htmlTags));
    }

    @Test
    public void shouldListSupportedLanguages() throws Exception {
        ArgumentMatcher<String> english = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("English");
            }
        };
        translator.setCommand(new String[]{"--dic=translateDic","--languages"});
        translator.doAction();

        verify(ios).output(argThat(english));
    }
}

