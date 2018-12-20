import com.generate.MybatiCodeGeneratePlugin;

public class GenerateCodeApplication {
    public static void main(String[] args) {
        try {
            MybatiCodeGeneratePlugin generatorSqlmap = new MybatiCodeGeneratePlugin();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
