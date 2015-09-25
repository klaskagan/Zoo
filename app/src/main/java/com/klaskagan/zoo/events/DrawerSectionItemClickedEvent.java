package com.klaskagan.zoo.events;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class DrawerSectionItemClickedEvent {

    private String section;

    public DrawerSectionItemClickedEvent(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }
}
