package com.sml.t1r.taskapp.Models;

public class ArrayInit {

    public static float[] getListPrimFloats(int cnt) {
        float[] items = new float[cnt];
        for (int i = 0; i < cnt; i++) {
            items[i] = 0f;
        }
        return items;
    }

}
