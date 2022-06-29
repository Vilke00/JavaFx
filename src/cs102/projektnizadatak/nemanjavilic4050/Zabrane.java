/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projektnizadatak.nemanjavilic4050;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author User
 */
public class Zabrane {
    
        //integer value
        private Pattern patternInteger = Pattern.compile("\\d*|\\d+\\,\\d*");
        private TextFormatter formatterInteger = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInteger.matcher(change.getControlNewText()).matches() ? change : null;
        });
        
        //double
        private Pattern patternDouble = Pattern.compile("^\\d*\\.?\\d+$");
        private TextFormatter formatterDouble = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternDouble.matcher(change.getControlNewText()).matches() ? change : null;
        });
        
        //String
        private Pattern patternString = Pattern.compile("[a-zA-Z]*");
        private UnaryOperator<TextFormatter.Change> filter = c -> {
            if (patternString.matcher(c.getControlNewText()).matches()) {
                return c;
            } else {
                return null;
            }
        };
        private TextFormatter<String> formatterString = new TextFormatter<>(filter);

    public Zabrane() {
    }

    public Pattern getPatternInteger() {
        return patternInteger;
    }

    public void setPatternInteger(Pattern patternInteger) {
        this.patternInteger = patternInteger;
    }

    public TextFormatter getFormatterInteger() {
        return formatterInteger;
    }

    public void setFormatterInteger(TextFormatter formatterInteger) {
        this.formatterInteger = formatterInteger;
    }

    public Pattern getPatternDouble() {
        return patternDouble;
    }

    public void setPatternDouble(Pattern patternDouble) {
        this.patternDouble = patternDouble;
    }

    public TextFormatter getFormatterDouble() {
        return formatterDouble;
    }

    public void setFormatterDouble(TextFormatter formatterDouble) {
        this.formatterDouble = formatterDouble;
    }

    public Pattern getPatternString() {
        return patternString;
    }

    public void setPatternString(Pattern patternString) {
        this.patternString = patternString;
    }

    public UnaryOperator<TextFormatter.Change> getFilter() {
        return filter;
    }

    public void setFilter(UnaryOperator<TextFormatter.Change> filter) {
        this.filter = filter;
    }

    public TextFormatter<String> getFormatterString() {
        return formatterString;
    }

    public void setFormatterString(TextFormatter<String> formatterString) {
        this.formatterString = formatterString;
    }

    
        
}
