package wasm.instruction2.control;

import wasm.core.instruction.Instruction;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.FunctionIndex;
import wasm.core.model.section.CodeSection;
import wasm.core.numeric.U64;
import wasm.core.structure.Function;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;

public class Call implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof FunctionIndex;

        FunctionIndex index = ((FunctionIndex) args);

        Function function = mi.getFunction(index);

        callFunction(mi, function);
    }

    public void callFunction(ModuleInstance mi, Function function) {
        if (!function.isInternal()) {
            // 有本地函数内容，对于模块来说是外部的
            callExternalFunction(mi, function);
        } else {
            callInternalFunction(mi, function);
        }
    }

    private void callInternalFunction(ModuleInstance mi, Function function) {
        CodeSection code = function.getCodeSection();
        mi.enterBlock(Instruction.CALL, function.type(), code.expression);

        // 分配本地变量
        long length = code.localCount();
        for (int i = 0; i < length; i++) { mi.pushU64(new U64(0)); }
    }

    private void callExternalFunction(ModuleInstance mi, Function function) {
        U64[] args = mi.popU64s(function.type().parameters.length);
        U64[] results = function.call(args);
        mi.pushU64s(results);
    }

}