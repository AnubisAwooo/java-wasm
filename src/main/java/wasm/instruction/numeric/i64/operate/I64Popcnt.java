package wasm.instruction.numeric.i64.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;

public class I64Popcnt implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U64 v = vm.operandStack.popU64();
        vm.operandStack.pushU64(v.popcnt());
    }

}
