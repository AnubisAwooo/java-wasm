package wasm.instruction.numeric.i32.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U32;
import wasm.util.NumberUtil;

public class I32RemU implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U32 v2 = vm.operandStack.popU32();
        U32 v1 = vm.operandStack.popU32();
        vm.operandStack.pushU32(NumberUtil.remU(v1, v2));
    }

}
