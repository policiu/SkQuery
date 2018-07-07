package com.w00tmast3r.skquery.elements.events.lang;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import com.w00tmast3r.skquery.util.Collect;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CustomExpressionEvent extends Event implements MethodEvent, Returnable, Pullable {

    private static HandlerList handlers = new HandlerList();
    private final String match;
    private final Expression<?>[] args;
    private final Class<?> expectedOutput;
    private final Event superEvent;
    private Object[] returnValue = null;

    public CustomExpressionEvent(String match, Expression<?>[] args, Class<?> expectedOutput, Event superEvent) {
        this.match = match;
        this.args = args;
        this.expectedOutput = expectedOutput;
        this.superEvent = superEvent;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    @Override
    public String getMatch() {
        return match;
    }

    @Override
    public Expression<?>[] getArgs(){
        return args;
    }

    @Override
    public Class<?> getExpectedOutput() {
        return expectedOutput;
    }

    @Override
    public Event getSuperEvent() {
        return superEvent;
    }

    public Object[] getReturn() {
        return returnValue;
    }

    @Override
    public void setReturn(Object[] returnValue) {
        if (returnValue.getClass().getComponentType().isAssignableFrom(expectedOutput)) this.returnValue = returnValue;
        else Skript.error(Collect.toString(returnValue) + " is not of the expected type, " + expectedOutput.getSimpleName());
    }
}
