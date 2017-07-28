import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class jSONTest {

    @Test
    public void testJSON2Obj() throws IOException {

        String s = "\n" +
                "    [\n" +
                "        {\"fileName\": \"test\",\n" +
                "        \"cols\":[\"col1\",\"col2\",\"col3\"],\n" +
                "        \"maxSize\":20,\n" +
                "        \"className\":\"cn.com.Test\",\n" +
                "        \"queryConfig\":{\n" +
                "        \"querySize\":\"100\",\n" +
                "        \"perPageNum\":1000,\n" +
                "        \"queryParams\":[\"name\", \"condition\"]\n" +
                "         }},\n" +
                "         {\n" +
                "                 \"fileName\": \"test\",\n" +
                "                 \"cols\":[\"col1\",\"col2\",\"col3\"],\n" +
                "                 \"maxSize\":20,\n" +
                "                 \"className\":\"cn.com.Test\",\n" +
                "                 \"queryConfig\":{\n" +
                "                 \"querySize\":\"100\",\n" +
                "                 \"perPageNum\":1000,\n" +
                "                 \"queryParams\":[\"name\", \"condition\"]\n" +
                "                  }}\n" +
                "    ]";

        ObjectMapper mapper = new ObjectMapper();

        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, FileModel.class);

        List<FileModel> fi = new ObjectMapper().readValue(s, type);


        Assert.assertEquals(fi.get(0).getFileName(), "test");

    }
}
