package wasm.core.instruction.memory;

import wasm.core.exception.Check;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.model.index.MemoryIndex;
import wasm.core.instruction.dump.DumpMemory;

public class I64Load16U implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);

        DumpMemory a = (DumpMemory) args;

        // System.err.println("So, which memory ?");
        byte[] bytes = mi.readBytes(MemoryIndex.of(U32.valueOf(0)), a, 2);

        mi.pushU64(U64.valueOfU(new byte[]{bytes[1], bytes[0]}));
    }

}