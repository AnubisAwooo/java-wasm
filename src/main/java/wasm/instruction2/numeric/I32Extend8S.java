package wasm.instruction2.numeric;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core.numeric.U32;

public class I32Extend8S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        byte[] bytes = mi.popU32().getBytes();

        mi.pushS32(U32.valueOfS(new byte[]{
            bytes[3]
        }).intValue());
    }

}
