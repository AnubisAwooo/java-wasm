package wasm.model;

import wasm.instruction.Expressions;
import wasm.model.index.MemoryIndex;

import static wasm.util.NumberUtil.toHex;

public class Data {

    public byte tag;

    public Value value;

    public static abstract class Value implements Dump {}

    public static class Value0 extends Value {
        // 𝟶𝚡𝟶𝟶  𝑒:𝚎𝚡𝚙𝚛  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗆𝖾𝗆𝗈𝗋𝗒 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public Expressions expressions;
        public byte[] bytes;

        public Value0(Expressions expressions, byte[] bytes) {
            this.expressions = expressions;
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x00 " + expressions.dump() + " [" +  toHex(bytes) + "]";
        }
    }
    public static class Value1 extends Value {
        // 𝟶𝚡𝟶𝟷  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝗉𝖺𝗌𝗌𝗂𝗏𝖾}
        public byte[] bytes;

        public Value1(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x01 [" +  toHex(bytes) + "]";
        }
    }
    public static class Value2 extends Value {
        // 𝟶𝚡𝟶𝟸  𝑥:𝚖𝚎𝚖𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗆𝖾𝗆𝗈𝗋𝗒 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public MemoryIndex memoryIndex;
        public Expressions expressions;
        public byte[] bytes;

        public Value2(MemoryIndex memoryIndex, Expressions expressions, byte[] bytes) {
            this.memoryIndex = memoryIndex;
            this.expressions = expressions;
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x02 " + memoryIndex.toString() + " " + expressions.dump() + " [" +  toHex(bytes) + "]";
        }
    }


    public Data(byte tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    public String dump(int index) {
        return "data[" + index + "]: " + value.dump();
    }

}
