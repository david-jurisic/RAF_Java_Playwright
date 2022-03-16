package org.raf3k.shared.logging;

import java.time.ZonedDateTime;

public class Substep {
    public String name;
    public ZonedDateTime start;
    public ZonedDateTime finish;
    public Exception ex;
    public String screenshot;
    public String messageAddon;
    public Boolean passed;
}
