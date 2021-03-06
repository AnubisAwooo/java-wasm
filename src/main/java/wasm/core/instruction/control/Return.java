package wasm.core.instruction.control;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Instruction;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.LabelIndex;
import wasm.core.numeric.U32;

public class Return implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        int[] index = new int[1];

        mi.topCallFrame(index);

        Instruction.BR.operate(mi, LabelIndex.of(U32.valueOf(index[0])));
    }
}
