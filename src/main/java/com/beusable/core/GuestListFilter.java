package com.beusable.core;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class GuestListFilter {


    public List<Float> filter(List<Float> guestPayments, Predicate<Float> predicate){
        return guestPayments.stream()
                .sorted(Comparator.reverseOrder())
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
