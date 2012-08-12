package acceptance.com.translateReferencePlugin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.tonyx.TranslateReferenceContentFilter;
import org.tonyx.TranslateReferenceProvider;
import org.tonyxzt.language.core.GenericDictionary;
import org.tonyxzt.language.core.Translator;
import org.tonyxzt.language.io.OutStream;
import org.tonyxzt.language.util.BrowserActivator;
import org.tonyxzt.language.util.CommandLineToStatusClassWrapper;
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
    //    translator = new Translator(mapDictionaries,browserActivator,ios);
    }

    @Test
    public void forUnsupportedLanguageShouldGetAWarningMessage() throws Exception {
        ArgumentMatcher<String> dovere = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("unresolved dictionary");
            }
        };
        // given
        String[] command = new String[] {"--dic=gUnsupported"};
        CommandLineToStatusClassWrapper commandLineToStatusClassWrapper  = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineToStatusClassWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(dovere));
        //Assert.assertTrue(getContent().contains("unresolved dictionary"));
    }

    @Test
    public void testContainsShould() throws Exception {
        // given
        ArgumentMatcher<String> dovere = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("dovere");
            }
        };
        String[] command = new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","should"};
        CommandLineToStatusClassWrapper commandLineWrapper = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(dovere));
    }

    @Test
    public void testContaintContainsMultipleLines() throws Exception {
        // given
        ArgumentMatcher<String> corsa = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("corsa");
            }
        };
        //translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","run"});
        String[] command = new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","run"};

        CommandLineToStatusClassWrapper commandLineWrapper = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(corsa));
    }

    @Test
    public void testContaintContainsShould() throws Exception {
        // given
        ArgumentMatcher<String> salve = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("salve, salute, ciao");
            }
        };
        String[] command =  new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","hello"};
        //translator.setCommand(new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","hello"});
        CommandLineToStatusClassWrapper commandLineWrapper = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(salve));
    }

    @Test
    public void shouldRemoveTags() throws Exception {
        // given
        ArgumentMatcher<String> htmlTags = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return !((String)object).contains("<")&&(!((String)object).contains(">"));
            }
        };
        String[] command = new String[]{"--dic=translateDic","--oriLang=en","--targetLang=it","hello"};
        CommandLineToStatusClassWrapper commandLineWrapper = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(htmlTags));
    }


    @Test
    public void shouldListSupportedLanguages() throws Exception {
        // given
        ArgumentMatcher<String> english = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("English");
            }
        };
       // translator.setCommand(new String[]{"--dic=translateDic","--languages"});
        String[] command = new String[]{"--dic=translateDic","--languages"};
        CommandLineToStatusClassWrapper commandLineToStatusClassWrapper  = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineToStatusClassWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(english));
    }

    @Test
    @Ignore // ignored because of utf issues
    public void retrieveWordsNoUsingTheDictionary() throws Exception {
        // given
        ArgumentMatcher<String> english = new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object object) {
                return ((String)object).contains("Браво")&&!((String)object).contains("<")&&!((String)object).contains(">");
            }
        };
        String[] command = new String[]{"--dic=translateDic","--oriLang=it","--targetLang=ru","bravo"};
        CommandLineToStatusClassWrapper commandLineToStatusClassWrapper  = new CommandLineToStatusClassWrapper(command,mapDictionaries,ios);
        Translator translator = new Translator(mapDictionaries,browserActivator,ios,commandLineToStatusClassWrapper);

        // when
        translator.doAction();

        // then
        verify(ios).output(argThat(english));
    }
}

