package org.raf3k.shared;

import org.raf3k.shared.logging.Evaluator;

public class SharedReferences {
    private static Helpers _Hlpr;

    public static Helpers hlpr() {

        if (_Hlpr == null) _Hlpr = new Helpers();
        return _Hlpr;
    }

    private static Evaluator _Eval;

    public static Evaluator eval() {
        if (_Eval == null) _Eval = new Evaluator();
        if (_Eval.driver == null) _Eval = new Evaluator();
        return _Eval;
    }
}
