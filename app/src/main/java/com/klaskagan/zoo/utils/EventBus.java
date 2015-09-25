package com.klaskagan.zoo.utils;

import com.squareup.otto.Bus;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class EventBus extends Bus {

    private static EventBus bus = new EventBus();

    public static EventBus getInstance() {
        return bus;
    }

    private EventBus() {
    }
}
