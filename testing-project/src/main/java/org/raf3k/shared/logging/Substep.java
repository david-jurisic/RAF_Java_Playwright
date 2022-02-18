package org.raf3k.shared.logging;

import java.time.LocalDateTime;

public class Substep {
    public String name;
    public LocalDateTime start;
    public LocalDateTime finish;
    public Exception ex;
    public String screenshot;
    public String messageAddon;
    public Boolean passed;
}
