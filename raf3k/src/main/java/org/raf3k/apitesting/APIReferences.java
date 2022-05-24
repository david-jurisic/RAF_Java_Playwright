package org.raf3k.apitesting;

import org.raf3k.apitesting.basetypes.APITestCase;
import org.raf3k.shared.Helpers;
import org.raf3k.shared.logging.Evaluator;

public class APIReferences {
    public static String currentPageContext;
    private static Evaluator _Eval;

    public static Evaluator get_Eval() {
        if (_Eval == null)
            _Eval = new Evaluator();
        if (_Eval.driver == null)
            _Eval = new Evaluator();
        return _Eval;
    }

    private static Helpers _Hlpr;

    public static Helpers get_Hlpr() {
        if (_Hlpr == null)
            _Hlpr = new Helpers();
        return _Hlpr;
    }

    public static APITestCase APITestCase;
}
