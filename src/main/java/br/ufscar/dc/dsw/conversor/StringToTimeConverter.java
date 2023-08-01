package br.ufscar.dc.dsw.conversor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Time;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTimeConverter implements Converter<String, Time> {

    private final DateFormat formatter = new SimpleDateFormat("hh:mm");

    @Override
    public Time convert(String source) {
        try {
            return new Time(formatter.parse(source).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
