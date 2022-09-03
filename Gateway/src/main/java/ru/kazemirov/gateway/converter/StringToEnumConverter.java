package ru.kazemirov.gateway.converter;

import org.springframework.core.convert.converter.Converter;
import ru.kazemirov.gateway.domain.Role;

public class StringToEnumConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source.toUpperCase());
    }
}
