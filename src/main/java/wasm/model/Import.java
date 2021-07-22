package wasm.model;

import wasm.model.describe.ImportDescribe;

public class Import {

    public String module; // 导入模块名
    public String name; // 导入成员名
    public ImportDescribe describe; // 描述信息

    public Import(String module, String name, ImportDescribe describe) {
        this.module = module;
        this.name = name;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Import{" +
                "module='" + module + '\'' +
                ", name='" + name + '\'' +
                ", describe=" + describe +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        switch (describe.tag.value()) {
            case 0x00: // FUNCTION
                sb.append("func[").append(index).append("]: ")
                        .append(module).append(".").append(name)
                        .append(", sig=").append(describe.value.toString());
                break;
            case 0x01: // TABLE
                sb.append("table[").append(index).append("]: ")
                        .append(module).append(".").append(name)
                        .append(", sig=").append(describe.value.toString());
                break;
            case 0x02: // MEMORY
                sb.append("memory[").append(index).append("]: ")
                        .append(module).append(".").append(name)
                        .append(", sig=").append(describe.value.toString());
                break;
            case 0x03: // GLOBAL
                sb.append("global[").append(index).append("]: ")
                        .append(module).append(".").append(name)
                        .append(", sig=").append(describe.value.toString());
                break;
            default:
                throw new RuntimeException("what type?");
        }

        return sb.toString();
    }

}