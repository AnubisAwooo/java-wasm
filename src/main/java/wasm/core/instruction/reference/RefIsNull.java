package wasm.core.instruction.reference;

import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;

public class RefIsNull implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

}
