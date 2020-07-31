package kr.or.watermelon.show.factory;

import kr.or.watermelon.show.entity.CustomBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Factory {

    public <T> Object makeItem(Function<T, CustomBuilder> func, T arg) {
        return func.apply(arg).build();
    }

    public <T> List<?> makeItems(Function<T, CustomBuilder> func, List<T> args) {
        return args.stream().map(arg -> func.apply(arg).build()).collect(Collectors.toList());
    }
}