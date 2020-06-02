package ru.amir.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.amir.Entities.SecurityCamera;

import javax.persistence.criteria.CriteriaBuilder;


public class StringToCamera implements Converter<String, SecurityCamera> {
    @Override
    public SecurityCamera convert(String s) {
        if (s.length() < 2)
            return null;
        SecurityCamera securityCamera = new SecurityCamera();
        String id = s.substring((s.indexOf("=") + 1), s.indexOf(", p"));
        securityCamera.setId(Integer.parseInt(id));
        Boolean power = Boolean.valueOf(s.substring((s.lastIndexOf("=") + 1), s.indexOf("}")));
        securityCamera.setPower(power);
        return securityCamera;
    }
}
