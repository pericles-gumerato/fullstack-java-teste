package com.contabilizei.app.util;

/**
 * @author Péricles P. Gumerato (pericles.gumerato@gmail.com)
 */
public class ResponseWrapper<V> {

    private V result;

    public ResponseWrapper(V object) {
        this.result = object;
    }

    public V getResult() {
        return result;
    }

    public void setResult(V result) {
        this.result = result;
    }
}
