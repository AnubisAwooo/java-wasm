package wasm.core;

import wasm.model.*;
import wasm.model.index.DataCountIndex;
import wasm.model.index.FunctionIndex;
import wasm.model.index.TypeIndex;
import wasm.model.tag.PortTag;

import java.util.stream.Stream;


public class Module {

    public Magic magic;      // 魔数
    public Version version;    // 版本

    public Custom[] customSections; // 0 自定义段
    public FunctionType[] typeSections = new FunctionType[0];     // 1 类型段 函数签名
    public Import[] importSections = new Import[0];         // 2 导入 导入函数部分指向类型段的函数签名
    public TypeIndex[] functionSections = new TypeIndex[0];    // 3 函数段 指向类型段函数索引
    public TableType[] tableSections = new TableType[0];       // 4 表
    public MemoryType[] memorySections = new MemoryType[0];     // 5 内存
    public Global[] globalSections = new Global[0];     // 6 全局
    public Export[] exportSections = new Export[0];     // 7 导出
    public FunctionIndex startFunctionIndex;     // 8 起始函数索引 uint32 应当是函数段的索引

    public Element[] elementSections = new Element[0];     // 9 元素

    public Code[] codeSections = new Code[0];     // 10 代码

    public Data[] dataSections = new Data[0];     // 11 数据

    public DataCountIndex dataCountIndex;     // 12 数据计数段 由于引入 memory.init data.drop 需要数据


    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("Magic: ").append(magic.value()).append("\n");
        sb.append("Version: ").append(version.value()).append("\n");

        sb.append("Type[").append(typeSections.length).append("]:").append("\n");
        for (int i = 0; i < typeSections.length; i++) {
            sb.append("  ").append(typeSections[i].dump(i)).append("\n");
        }

        sb.append("Import[").append(importSections.length).append("]:").append("\n");
        for (int i = 0; i < importSections.length; i++) {
            sb.append("  ").append(importSections[i].dump(i)).append("\n");
        }

        sb.append("Function[").append(functionSections.length).append("]:").append("\n");
        for (int i = 0; i < functionSections.length; i++) {
            sb.append("  ").append(functionSections[i].dump(i)).append("\n");
        }

        sb.append("Table[").append(tableSections.length).append("]:").append("\n");
        for (int i = 0; i < tableSections.length; i++) {
            sb.append("  ").append(tableSections[i].dump(i)).append("\n");
        }

        sb.append("Memory[").append(memorySections.length).append("]:").append("\n");
        for (int i = 0; i < memorySections.length; i++) {
            sb.append("  ").append(memorySections[i].dump(i)).append("\n");
        }

        sb.append("Global[").append(globalSections.length).append("]:").append("\n");
        for (int i = 0; i < globalSections.length; i++) {
            sb.append("  ").append(globalSections[i].dump(i)).append("\n");
        }

        sb.append("Export[").append(exportSections.length).append("]:").append("\n");
        for (Export exportSection : exportSections) {
            sb.append("  ").append(exportSection.dump()).append("\n");
        }

        sb.append("Start: ").append(null == startFunctionIndex ? "none" : startFunctionIndex).append("\n");

        sb.append("Element[").append(elementSections.length).append("]:").append("\n");
        for (int i = 0; i < elementSections.length; i++) {
            sb.append("  ").append(elementSections[i].dump(i).replace("\n", "\n  ")).append("\n");
        }

        int importFunctionCount = (int) Stream.of(importSections).filter(i -> i.describe.tag == PortTag.FUNCTION).count();
        sb.append("Code[").append(codeSections.length).append("]:").append("\n");
        for (int i = 0; i < codeSections.length; i++) {
            sb.append("  ").append(codeSections[i].dump(importFunctionCount + i)).append("\n");
        }

        sb.append("Data[").append(dataSections.length).append("]:").append("\n");
        for (int i = 0; i < dataSections.length; i++) {
            sb.append("  ").append(dataSections[i].dump(i)).append("\n");
        }

        sb.append("Custom[").append(customSections.length).append("]:").append("\n");
        for (int i = 0; i < customSections.length; i++) {
            sb.append("  ").append(customSections[i].dump(i)).append("\n");
        }

        sb.append("DataCount: ").append(null == dataCountIndex ? "none" : dataCountIndex).append("\n");

        return sb.toString();
    }


}