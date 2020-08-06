package code.generation;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author sen
 * @ClassName: RapidGenerater
 * @Description:javaPath
 * @date 2016-10-14 15:11
 */
public class RapidGenerator {


    //代码根路径
    public static final String TEMPLATE_ROOT_DIR = "/src/main/resources/code/generate/template";
    //java源代码路径
    public static final String JAVA_PATH = "/src/main/java/com/clubfactory/item/center/core";
    //mybatis配置文件路径
    public static final String MYBATIS_XML_PATH = "/src/main/resources/mapper";
    //
    public static final String BASE_PACKAGE = "com.clubfactory.item.center.core";


    /**
     * 生成代码
     *
     * @param projectPath 项目路径地址
     * @param author      作者 用系统变量中的USER
     * @param tableName   表名称
     * @throws Exception
     */
    public static void generatorOneTable(String projectPath, String author, String tableName)
            throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        g.getGenerator().setTemplateRootDir(projectPath + TEMPLATE_ROOT_DIR);
        g.getGenerator().setOutRootDir(projectPath);
        GeneratorProperties.setProperty("javaPath", projectPath + JAVA_PATH);
        GeneratorProperties.setProperty("mybatisXMLPath", projectPath + MYBATIS_XML_PATH);
        GeneratorProperties.setProperty("basepackage", BASE_PACKAGE);
        //设置自己的名字
        GeneratorProperties.setProperty("author", author);
        GeneratorProperties.setProperty("createTime", DateUtil.nowFormat(DateUtil.YMD));
        g.generateByTable(tableName);
    }

    public static void main(String[] args) throws Exception {
        String projectPath = System.getenv().get("PWD");
        String author = System.getenv().get("USER");
        //表名称
        List<String> tableNames = Lists.newArrayList("item", "item_images", "item_sku", "item_sku_price");

        for (String tableName : tableNames) {
            generatorOneTable(projectPath, author, tableName);
        }
    }


}
