package idwall.desafio.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    public IdwallFormatter(){

    }

    public IdwallFormatter(Integer limit){
        this.limit = limit != null ? limit : this.limit;
    }


    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text, boolean justify) {
        text = text.replace("\n", "\\n");
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] array = text.split("\\s+");
        LinkedList<String> list = new LinkedList(Arrays.asList(array));
        list = this.filter(list);
        list = list.stream().map(e -> e + " ").collect(Collectors.toCollection(LinkedList::new));

        StringBuilder str = new StringBuilder();
        for (String s : list) {
            count += this.count(s);
            if (count <= limit) {
                str.append(s);
            } else if (count - limit == 1 && s.endsWith(" ")) {
                str.append(s.trim());
            } else {
                if (justify && (count- this.count(s)) < count) {
                    String st = str.toString();
                    LinkedList<String> l = new LinkedList(Arrays.asList(st.split(" ")));
                    l = this.filter(l);
                    stringBuilder.append(this.justify(l));
                } else {
                    stringBuilder.append(str.toString());
                }
                count = 0;
                stringBuilder.append("\n");
                str = new StringBuilder();
                str.append(s);
                count += this.count(s);
            }
        }
        stringBuilder.append(str.toString());
        return stringBuilder.toString().replace("\\n", "\n");
    }

    private long count(String s) {
        Matcher m = Pattern.compile("\n").matcher(s.replace("\\n", "\n"));
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count > 0 ? s.length() - count : s.length();
    }

    private LinkedList<String> filter(LinkedList<String> list) {
        LinkedList<String> l = list.stream().map(e -> e.trim()).collect(Collectors.toCollection(LinkedList::new));
        return l;
    }

    private String justify(LinkedList<String> l) {
        int count = 0;
        do {
            if(count == l.size()-1){
                count = 0;
            }else {
                l.set(count, l.get(count) + " ");
                count++;
            }
        } while (l.stream().collect(Collectors.joining("")).length() < limit);
        return l.stream().collect(Collectors.joining(""));
    }
}
