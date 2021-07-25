package wasm.core3.nav.function;

import wasm.core.numeric.U64;
import wasm.core.numeric.USize;
import wasm.core2.model.section.FunctionType;
import wasm.core2.model.type.ValueType;
import wasm.core3.model.tag.FunctionTypeTag;
import wasm.core3.structure.Function;

class Assert {

    static class AssertTrue implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64 }, new ValueType[]{});
        }
        @Override
        public USize[] call(USize... args) {
            if (args[0].longValue() == 0) {
                // 0 则为 false
                throw new RuntimeException("not equals: " + args[0] + " == 1");
            }
            return new USize[0];
        }
    }

    static class AssertFalse implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64 }, new ValueType[]{});
        }
        @Override
        public USize[] call(USize... args) {
            if (args[0].longValue() != 0) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == 0");
            }
            return new USize[0];
        }
    }

    static class AssertEqualInt implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64, ValueType.I64 }, new ValueType[]{});
        }
        @Override
        public USize[] call(USize... args) {
            if (args[0].uBidInteger().compareTo(args[1].uBidInteger()) != 0) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == " + args[1]);
            }
            return new USize[0];
        }
    }

    static class AssertEqualLong implements Function {
        @Override
        public FunctionType type() {
            return new FunctionType(FunctionTypeTag.FUNCTION_TYPE, new ValueType[]{ ValueType.I64, ValueType.I64 }, new ValueType[]{});
        }
        @Override
        public USize[] call(USize... args) {
            if (args[0].uBidInteger().compareTo(args[1].uBidInteger()) != 0) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == " + args[1]);
            }
            return new U64[0];
        }
    }

}
