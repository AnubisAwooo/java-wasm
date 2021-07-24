package wasm.core3.model.index;

import wasm.core3.numeric.U32;

public class DataCountIndex extends U32 {

    private DataCountIndex(U32 u32) {
        super(u32);
    }

    public static DataCountIndex of(U32 value) { return new DataCountIndex(value); }

    public String dump(int index) {
        return "DataCountIndex[" + index + "]: " + "value=" + super.toString();
    }

}