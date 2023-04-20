package hu.szte.inf.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Functional {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    public static <T> String beanToString(T bean) {
        String className = bean.getClass().getSimpleName();
        var names = Arrays.stream(bean.getClass().getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
        var values = Arrays.stream(bean.getClass().getDeclaredFields()).map(el -> {
            try {
                return el.get(bean);
            } catch (IllegalAccessException e) {
                el.trySetAccessible();
                try {
                    return el.get(bean);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(className).append("(\n");
        for (int i = 0; i < names.size(); ++i) {
            stringBuilder
                    .append(names.get(i))
                    .append("=")
                    .append(values.get(i).toString())
                    .append("\n");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
