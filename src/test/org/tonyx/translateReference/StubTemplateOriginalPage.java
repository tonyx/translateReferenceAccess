package test.org.tonyx.translateReference;

/**
 * Created with IntelliJ IDEA.
 * User: tonyx
 * Date: 31/03/12
 * Time: 16.52
 * To change this template use File | Settings | File Templates.
 */
public class StubTemplateOriginalPage {
    String _replace;

    public String getHtml() {
        return this.templateContent.replaceAll("XXXXX",_replace);
    }

    public StubTemplateOriginalPage(String replace) {
        this._replace = replace;
    }

    private String templateContent = ""+
            "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:fb=\"http://ogp.me/ns/fb#\" xmlns:og=\"http://opengraphprotocol.org/schema/\">\n" +
            "<span class=\"BAB_CPPOSStyle\">inter.</span>\n" +
            "     XXXXX\n" +
            "    <br style=\"clear : both; font-size : 1px;\">\n" +
            "</div>\n" +
            "<div class=\"DIV_AS_HR\">&nbsp;</div>\n" +
            "<div class=\"definition\" dir=\"\">\n" +
            "<span class=\"BAB_CPPOSStyle\">s.</span>\n" +
            "     saluto\n" +
            "    <br style=\"clear : both; font-size : 1px;\">\n" +
            "</div>\n" +
            "                                \n" +
            "                            \n" +
            "                            \n" +
            "                           \n";
}
