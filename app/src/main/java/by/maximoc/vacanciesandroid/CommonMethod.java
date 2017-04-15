package by.maximoc.vacanciesandroid;

import android.text.Html;
import android.text.Spanned;

public class CommonMethod {
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html2) {
        String wrapping = html2.replaceAll("\\<li> <p\\>", "<li>");
        String wrapping2 = wrapping.replaceAll("\\</li> <li\\>", "<p>● ");
        String html = wrapping2.replaceAll("\\<ul> <li\\>", "● ");
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
